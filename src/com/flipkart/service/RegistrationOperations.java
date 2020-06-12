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
	CourseDAO courseDAO = new CourseDAO();
	RegistrationDAO registrationDAO = new RegistrationDAO();
	StudentDAO studentDAO = new StudentDAO();
	
	
	public String doRegistration(Student student , Scanner input) throws NotificationMessage{
		ArrayList<Course> allCourses = new ArrayList<Course>();
		allCourses.addAll(viewCourseCatalog());
		ArrayList<Course> studentCourses = new ArrayList<Course>();
		if(allCourses!=null) {
			studentCourses.addAll(allCourses
					.stream()
					.filter(course -> course.getSemester() == student.getSemester())
					.collect(Collectors.toList()));
		}
		logger.info("Courses Available for you :");
		for(Course course : studentCourses) {
			logger.info(course.getCourseCode()+"\t"+course.getName());
		}
		
		ArrayList<Integer> primaryCourses = new ArrayList<Integer>();
		ArrayList<Integer> secondaryCourses = new ArrayList<Integer>();
		
		logger.info("Enter 4 Primary Course");
		for(int i = 1 ;i < 5 ; i++) {
			logger.info("Enter Course Code for course "+i);
			primaryCourses.add(input.nextInt());
		}
		logger.info("Enter 2 Secondary Course");
		for(int i = 1 ;i < 3 ; i++) {
			logger.info("Enter Course Code for course "+i);
			secondaryCourses.add(input.nextInt());
		}
		
		ArrayList<Course> final_courses = new ArrayList<Course>();
		for(int ind = 0 ; ind < 4 ; ind++) {
			for(Course course : studentCourses) {
				if(course.getCourseCode() == primaryCourses.get(ind)) {
					try {
						checkCourseConstraints(primaryCourses.get(ind));
						final_courses.add(course);
					}
					catch (CourseFilledException e) {
						logger.error(e.getMessage());
					}
				}
		}
		}
		
		boolean flag = false;
		int j=0;
		for(Course secCourse: studentCourses) {
			if(final_courses.size()==4 || j==2) {
				break;
			}
			if(secCourse.getCourseCode() == secondaryCourses.get(j)) {
				try {
					checkCourseConstraints(secondaryCourses.get(j));
					final_courses.add(secCourse);
					flag = true;
					j++;
					break;
				}
				catch (CourseFilledException e) {
					logger.error(e.getMessage());
				}
			}
		}
		for(Course fCourse:final_courses) {
			logger.info(fCourse.getName());
		}
		if(final_courses.size()<4) {
			throw new NotificationMessage();
		}
		
		// TODO Give Message of change
		Registration newRegistration = new Registration(final_courses);
		String regDetails=  "";
		newRegistration.setRegistrationTime();
		newRegistration.setFeespaid(false);
		newRegistration.setRegistrationTime();
		regDetails = regDetails + "Registration number : " + newRegistration.getRegistrationNumber()
							+"\n Registration Time : "+newRegistration.getRegistrationNumber();
		
		
		registrationDAO.addRegistration(newRegistration, student);
		student.setRegistrationNumber(newRegistration.getRegistrationNumber());
		studentDAO.UpdateStudentRegistration(final_courses, student);
		return regDetails;
	}
	
	public void checkCourseConstraints(int courseCode) throws CourseFilledException{
		ArrayList<Course> allCourses = new ArrayList<Course>();
		allCourses.addAll(viewCourseCatalog());
		int no_of_students = 0;
		if(allCourses != null)
			no_of_students = (allCourses
			.stream()
			.filter(course -> course.getCourseCode() == courseCode)
			.collect(Collectors.toList()).get(0)).getNumberofStudents();
		if(no_of_students>=10) {
			throw new CourseFilledException(courseCode);
		}
		
	}

}
