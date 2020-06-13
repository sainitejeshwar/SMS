package com.flipkart.helper;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.flipkart.DAO.CourseDAO;
import com.flipkart.bean.Course;
import com.flipkart.exception.InvalidCourseException;

public interface operationHelper {
	static  Logger logger = Logger.getLogger(operationHelper.class);
	CourseDAO courseDAO = new CourseDAO();
	
	default public  void viewCourseCatalog(){
		ArrayList<Course> allCourses = new ArrayList<Course>();
		allCourses.addAll(courseDAO.listAll());
		if(allCourses != null)
			allCourses
			.forEach((course) -> logger.info(course.getCourseCode()+"\t"+course.getName()+"\t"
					+ course.getProf()+"\t"+course.getNumberofStudents()));
		
	}
	
	default public  ArrayList<Course> returnCourseCatalog(){
		return courseDAO.listAll();
	}
	default public String viewReportCard(String string) {
		return null;
	}

		
	default public boolean isCourseContained(int courseCode , ArrayList<Course> courseList) throws InvalidCourseException{
		for (Course course : courseList) {
			if(course.getCourseCode() == courseCode)
				return true;
		}
		throw new InvalidCourseException(courseCode);
	}
	
}
