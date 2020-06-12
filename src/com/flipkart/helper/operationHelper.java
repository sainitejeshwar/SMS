package com.flipkart.helper;

import java.util.ArrayList;

import com.flipkart.DAO.CourseDAO;
import com.flipkart.bean.Course;

public interface operationHelper {
	CourseDAO courseDAO = new CourseDAO();
	default public ArrayList<Course> viewCourseCatalog(){
		return courseDAO.listAll();
	}
	
	default public String viewReportCard(String string) {
		return null;
	}

	
}
