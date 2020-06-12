package com.flipkart.helper;

import java.util.ArrayList;

import com.flipkart.DAO.CourseDAO;
import com.flipkart.bean.Course;

public interface operationHelper {
	CourseDAO courseDAO = new CourseDAO();
	default public ArrayList<Course> viewCourseCatalog(){
		ArrayList<Course> allCourses = new ArrayList<Course>();
		allCourses.addAll(courseDAO.listAll());
		return allCourses;
	}
	
	default public String viewReportCard(int studentID) {
		return null;
	}

}
