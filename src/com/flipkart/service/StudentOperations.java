package com.flipkart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.Payment;
import com.flipkart.bean.Student;
import com.flipkart.exception.InvalidCourseException;
import com.flipkart.helper.operationHelper;
import com.flipkart.utils.DateTimeUtil;

/*
 * CLASS DESCRIPTION
 * 
 * contains all the operations that the student can perform
 * 
 * Includes:   
 * 		- pay fees feature
 * 		- get student detials
 * 		- show Course catalog
 * 		- view report card
 * 		- helper funtion to print details
 * 
 */
public class StudentOperations implements operationHelper{
	
	//LOGGER OBJECT
	private static  Logger logger = Logger.getLogger(StudentOperations.class);
	
	//REQUIRED OBJECT(S)
	private static Student student = new Student();
	private static DateTimeUtil dTimeUtil = new DateTimeUtil();
	
	//Pay registration fees
	public String payFees(int registrationID) {
		//First checks whether registration is done or not by checking fees paid status
		try {
			if(registrationDAO.getPaymentStatus(registrationID).isFeespaid()) {
				return "Fees Already Paid on :" +dTimeUtil.systemDateTime(paymentDAO.getPaymentStatus(registrationID).getTimeStamp());
			}
		}
		catch (Exception e) {
			return ("Do Registration First");
		}
		//If registration is done then fees is fetched and payment gateway opens
		int fees = registrationDAO.getRegistrationFees(registrationID);
		/*
		 * PAYMENT GATEWAY STARTS
		 */
		logger.info("Amount to be Paid : " + fees);
		logger.info("Paying Fees for Registration ID : "+registrationID);
		Payment payment = new Payment();
		payment.setTimeStamp(dTimeUtil.SQLdatetime());
		payment.setStatus("Completed");   						//Assuming True from payment Gateway
		payment.setTransactionID(registrationID);				
		payment.setRegNO(registrationID);
		payment.setAmount(fees);
		/*
		 * PAYMENT GATE WAY ENDS
		 */
		
		//Updating the payment status
		paymentDAO.updatePayment(payment);
		registrationDAO.updatePayment(payment);
		return "Fees paid on : "+dTimeUtil.systemDateTime(payment.getTimeStamp())+" with Transacction ID : "+ payment.getTransactionID();
	}
	
	//Getting student details by ID
	public Student getStudent(String emailid) {
		return studentDAO.listByID(emailid);
	}
	
	//Showing courses in a particular catalog
	public void showCourse(int CatalogID) {
		
		returnCourseCatalog()
		.stream()
		.filter(course -> course.getCatalogID() == CatalogID)
		.forEach(course -> logger.info(course.getCourseCode()+"\t"+course.getName()));
	}
	//returning courses in a particular catalog
	public ArrayList<Course> getCourse(int CatalogID) {
		return (ArrayList<Course>) returnCourseCatalog()
		.stream()
		.filter(course -> course.getCatalogID() == CatalogID)
		.collect(Collectors.toList());
	}
	
	//For viewing the grades of the student
	public void viewReportCard(String emailID) {
		student = studentDAO.listByID(emailID);
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.putAll(marksDAO.fetchGrades(student.getStudentID()));
		for (Map.Entry<Integer,Integer> entry : map.entrySet())  
            logger.info(getCourseName(entry.getKey()) + 
                             "\t" + entry.getValue());		
	}
	
	//helper function to print details
	public String showDetails(Student student) {
		return "ID :"+student.getStudentID()+"\nName:"+student.getName()+"\nBranch:"+student.getBranch()
		+"\nSemester:"+student.getSemester();
	}
	
	//add student course to student course table
	public String addCourse(Student student , Scanner input) {
		//cannot add after registration
		if(student.getRegistrationNumber() != 0) {
			return "Registration Already Done \n Cannot perform this operations now";
		}
		//displaying already added courses
		logger.info("Already Added Courses  :");
		ArrayList<Integer> addedCourse = new ArrayList<Integer>();
		addedCourse.addAll(studentCourseDAO.getCourse(student.getStudentID()));
		for(Integer itr: addedCourse) {
			logger.info(getCourseName(itr) + "  "+itr);
		}
		//list of all available courses
		logger.info("Courses Available for you : ");
		ArrayList<Course> avaiableCourses = new ArrayList<Course>();
		avaiableCourses.addAll((returnCourseCatalog()
				.stream()
				.filter(course -> ((course.getCatalogID() == student.getSemester()) &&  !addedCourse.contains(course.getCourseCode())))
				.collect(Collectors.toList())));
		
		//displaying all course
		for(Course course : avaiableCourses) {
				logger.info(course.getCourseCode()+"\t"+course.getName()+"\t"+course.getFees());
		}
		
		//adding new course
		logger.info("\nEnter Course Code to add :");
		int courseCode = input.nextInt();
		//check if valid course is added or not
		try {
			if(isCourseContained(courseCode,avaiableCourses) != null){
				studentCourseDAO.addCourse(student.getStudentID(), courseCode);
			}
		} catch (InvalidCourseException e) {
			return (e.Message()+" not valid for you");
		}
		return "Course " +getCourseName(courseCode) + "  added";
	}
	
	//dropping course from student course table before 
	public String  dropCourse(Student student , Scanner input) {
		//cannot drop after registration
		if(student.getRegistrationNumber() != 0) {
			return "Registration Already Done \n Cannot perform this operations now";
		}
		//displaying added course that can be dropped
		logger.info("Added Courses");
		for(Integer itr: studentCourseDAO.getCourse(student.getStudentID())) {
			logger.info(getCourseName(itr) + "  "+itr);
		}
		//getting course and check whether it is valid or not
		logger.info("Enter Course Code to drop course");
		int courseCode = input.nextInt();
		if(studentCourseDAO.getCourse(student.getStudentID()).contains(courseCode))
		{
			studentCourseDAO.dropCourse(student.getStudentID(),courseCode);
		}
		else {
			return "Invalid CourseCode";
		}
		return "Course " + getCourseName(courseCode) + " deleted";
	}
}

