package com.flipkart.service;

import org.apache.log4j.Logger;

import com.flipkart.bean.Payment;
import com.flipkart.bean.Student;
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
	
	//For viewing the grades of the student
	public String viewReportCard(String emailID) {
		student = studentDAO.getGrade(emailID);
		String grades = "";
		int ind = 0;
		
		for(Integer itr : student.getMarks()) {
			grades = grades + (getCourseName(student.getStudentCourses().get(ind)) +"\t"+itr+"\n");
			ind++;
		}
		//if no grades are available
		if(grades.equals("")) {
			grades = "Not Available";
		}
		return grades;
	}
	
	//helper funtion to print details
	public String showDetails(Student student) {
		return "ID :"+student.getStudentID()+"\nName:"+student.getName()+"\nBranch:"+student.getBranch()
		+"\nSemester:"+student.getSemester();
	}
}

