package com.flipkart.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.Registration;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseFilledException;
import com.flipkart.exception.InvalidCourseException;
import com.flipkart.exception.NotificationMessage;
import com.flipkart.exception.RegistrationEndedException;
import com.flipkart.helper.operationHelper;

/*
 * CLASS DESCRIPTION
 * Has all registration operations that will be needed while student registration 
 * 
 */

public class RegistrationOperations implements operationHelper {
	//LOGGER OBJECT(S)
	private static  Logger logger = Logger.getLogger(RegistrationOperations.class);

	//Start Registration for the student
	public String doRegistration(Student student , Scanner input) throws NotificationMessage , RegistrationEndedException {
		
		//if registration is done or not
		if(student.getRegistrationNumber() != 0) {
			return "Registration Already Done \n Pay fees (if not paid yet)";
		}
		//checks if registration ended or not
		if(isAfterDateRegistrationDate()) {
			throw new RegistrationEndedException(RegistrationEndDate);
		}
		
		//Fetching the added courses
		ArrayList<Integer> tempCourses = new ArrayList<Integer>();
		tempCourses = studentCourseDAO.getCourse(student.getStudentID());
		 
		//checking whether courses are valid or not based on number of student and valid course code
		boolean flag = false;
		int ind = 0;
		Set<Course> final_courses = new HashSet<Course>();
		for(ind = 0 ; ind < tempCourses.size() ; ind++) {
			if(final_courses.size()==4) {
				break;
			}
			try {
				checkCourseConstraints(tempCourses.get(ind));
				final_courses.add(getCourseDetails(tempCourses.get(ind)));
				}
				catch (CourseFilledException e) {
					logger.error(getCourseName(e.Message()) +" is already filled");
				} catch (InvalidCourseException e) {
					logger.info(getCourseName(e.Message())+" - "+e.Message()+ " - or it is not available for you");
				}
		}
		
		// primary courses not get selected for final registration
		if(ind>=4) {
			flag = true;
		}
		
		//if 4 courses cannot be finalized 
		if(final_courses.size()<4) {
			throw new NotificationMessage();
		}
		//showing final courses and  calculating fees
		int TotalFees = 0;
		if(flag == true) {
			logger.info("There is a change in your Primary Course \n Final Courses are :");
			for(Course fCourse:final_courses) {
				logger.info(fCourse.getName());
				TotalFees = TotalFees + fCourse.getFees();
			}
		}
		
		ArrayList<Course> final_course = new ArrayList<Course>();
		final_course.addAll(final_courses);
		
		//crating a new registrations with the final courses for the student 
		Registration newRegistration = new Registration(final_course , student.getStudentID());
		student.setRegistrationNumber(newRegistration.getRegistrationNumber());
		newRegistration.setAmount(TotalFees);
		
		//updating in  database
		registrationDAO.addRegistration(newRegistration, student);
		studentDAO.UpdateStudentRegistration(final_course, student); //studentCourseDao
		courseDAO.updateStudents(final_course);   
		return "Registration Completed";
	}
	
	//helper function to checker whetther course is filled or not
	public void checkCourseConstraints(int courseCode) throws CourseFilledException , InvalidCourseException{
		try{
			int no_of_students = (
			returnCourseCatalog()
			.stream()
			.filter(course -> course.getCourseCode() == courseCode)		// filter that particular course
			.collect(Collectors.toList())
			.get(0))													//get the first course and number of student in that
			.getNumberofStudents();
		if(no_of_students>=10)
			throw new CourseFilledException(courseCode);
		}
		catch (IndexOutOfBoundsException e) {
			throw new InvalidCourseException(courseCode);
		}
	}
}


