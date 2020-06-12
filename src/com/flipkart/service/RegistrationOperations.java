package com.flipkart.service;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.flipkart.DAO.CourseDAO;
import com.flipkart.bean.Course;
import com.flipkart.bean.Registration;
import com.flipkart.exception.CourseFilledException;
import com.flipkart.helper.operationHelper;

public class RegistrationOperations implements operationHelper {
	private static  Logger logger = Logger.getLogger(RegistrationOperations.class);
	CourseDAO courseDAO = new CourseDAO();
	
	
	
	public boolean doRegistration(String studentId) {
		Scanner input = new Scanner(System.in);
		logger.info("Enter 4 Primary Course");
		
		
		logger.info("Enter 2 Secondary Course");
		
		ArrayList<Course> final_courses = new ArrayList<Course>();
		
		
		
		return false;
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
