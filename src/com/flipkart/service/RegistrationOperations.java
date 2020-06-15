package com.flipkart.service;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.flipkart.DAO.CourseDAO;
import com.flipkart.DAO.RegistrationDAO;
import com.flipkart.DAO.StudentDAO;
import com.flipkart.bean.Course;
import com.flipkart.bean.Registration;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseFilledException;
import com.flipkart.exception.InvalidCourseException;
import com.flipkart.exception.NotificationMessage;
import com.flipkart.exception.RegistrationEndedException;
import com.flipkart.helper.operationHelper;
import com.mysql.cj.util.StringUtils;

public class RegistrationOperations implements operationHelper {
	private static  Logger logger = Logger.getLogger(RegistrationOperations.class);

	public String doRegistration(Student student , Scanner input) throws NotificationMessage , RegistrationEndedException {
		if(student.getRegistrationNumber() != 0) {
			return "Registration Already Done \n Pay fees (if not paid yet)";
		}
		if(isAfterDateRegistrationDate()) {
			throw new RegistrationEndedException(RegistrationEndDate);
		}
		logger.info("Courses Available for you : ");
		
		ArrayList<Course> avaiableCourses = new ArrayList<Course>();
		avaiableCourses.addAll((returnCourseCatalog()
				.stream()
				.filter(course -> course.getSemester() == student.getSemester())
				.collect(Collectors.toList())));
		
		for(Course course : avaiableCourses) {
				logger.info(course.getCourseCode()+"\t"+course.getName()+"\t"+course.getFees());
		}
		
		ArrayList<Integer> tempCourses = new ArrayList<Integer>();
		logger.info("Enter 4 Primary Course and 2 Secondary Course");
		for(int i = 0 ;  i<6 ; i ++){
			String str = input.next();
			tempCourses.add(Integer.parseInt(str));
		
		}
		
		boolean flag = false;
		int ind = 0;
		ArrayList<Course> final_courses = new ArrayList<Course>();
		for(ind = 0 ; ind < 6 ; ind++) {
			if(final_courses.size()==4) {
				break;
			}
			try {
				checkCourseConstraints(tempCourses.get(ind));
				final_courses.add(isCourseContained(tempCourses.get(ind), avaiableCourses));
				}
				catch (CourseFilledException e) {
					logger.error(getCourseName(e.Message()) +" is already filled");
				} catch (InvalidCourseException e) {
					logger.info(getCourseName(e.Message())+" - "+e.Message()+ "-  is not available for you");
				}
			}
		if(ind>=4) {
			flag = true;
		}
		if(final_courses.size()<4) {
			throw new NotificationMessage();
		}
		int TotalFees = 0;
		if(flag == true) {
			logger.info("There is a change in your Primary Course \n Final Courses are :");
			for(Course fCourse:final_courses) {
				logger.info(fCourse.getName());
				TotalFees = TotalFees + fCourse.getFees();
			}
		}
		
		Registration newRegistration = new Registration(final_courses , student.getStudentID());
		student.setRegistrationNumber(newRegistration.getRegistrationNumber());
		newRegistration.setAmount(TotalFees);

		registrationDAO.addRegistration(newRegistration, student);
		studentDAO.UpdateStudentRegistration(final_courses, student); //studentCourseDao
		courseDAO.updateStudents(final_courses);
		marksDAO.createStudent(student.getStudentID());   //no use ,  studentCourse will store 
		return "Registration Completed";
	}
	
	public void checkCourseConstraints(int courseCode) throws CourseFilledException{
		int no_of_students = (
			returnCourseCatalog()
			.stream()
			.filter(course -> course.getCourseCode() == courseCode)
			.collect(Collectors.toList())
			.get(0))
			.getNumberofStudents();
		if(no_of_students>=10)
			throw new CourseFilledException(courseCode);	
	}
}


