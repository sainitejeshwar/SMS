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
import com.flipkart.exception.NotificationMessage;
import com.flipkart.helper.operationHelper;

public class RegistrationOperations implements operationHelper {
	private static  Logger logger = Logger.getLogger(RegistrationOperations.class);
	private  CourseDAO courseDAO = new CourseDAO();
	private  RegistrationDAO registrationDAO = new RegistrationDAO();
	private  StudentDAO studentDAO = new StudentDAO();
	
	
	public String doRegistration(Student student , Scanner input) throws NotificationMessage{
		if(student.getRegistrationNumber() != 0) {
			return "Registration Already Done \n Pay fees (if not paid yet)";
		}
		logger.info("Courses Available for you : ");
		for(Course course : (returnCourseCatalog()
				.stream()
				.filter(course -> course.getSemester() == student.getSemester())
				.collect(Collectors.toList()))) {
				logger.info(course.getCourseCode()+"\t"+course.getName());
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
				logger.debug("Break");
				break;
			}
			for(Course course : returnCourseCatalog()) {
				if(course.getCourseCode() == tempCourses.get(ind)) {
					try {
						checkCourseConstraints(tempCourses.get(ind));
						final_courses.add(course);
					}
					catch (CourseFilledException e) {
						logger.error(e.getMessage());
					}
				}
			}
		}
		
		if(ind>=4) {
			flag = true;
		}
		if(final_courses.size()<4) {
			throw new NotificationMessage();
		}
		if(flag == true) {
			logger.info("There is a change in your Primary Course \n Final Courses are :");
			for(Course fCourse:final_courses) {
				logger.info(fCourse.getName());
			}
		}
		
		Registration newRegistration = new Registration(final_courses);
		student.setRegistrationNumber(newRegistration.getRegistrationNumber());
		
		registrationDAO.addRegistration(newRegistration, student);
		studentDAO.UpdateStudentRegistration(final_courses, student);
		courseDAO.updateStudents(final_courses);

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


