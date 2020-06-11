package com.flipkart.service;

import org.apache.log4j.Logger;

import com.flipkart.DAO.CourseCatalogDAO;
import com.flipkart.DAO.CourseDAO;
import com.flipkart.DAO.StudentDAO;
import com.flipkart.bean.Student;

public class StudentOperations {
	private static  Logger logger = Logger.getLogger(StudentOperations.class);
	private CourseCatalogDAO courseCatalogDAO = new CourseCatalogDAO();
	private CourseDAO courseDAO = new CourseDAO();
	private StudentDAO studentDAO = new StudentDAO();
	
	public String generateReportCard(int id) {
		return null;
	}
	
	public String viewCourseCatalog() {
		return courseCatalogDAO.listAll();
	}
	
	public String viewMyCourse(int ID) {
		return studentDAO.getMyCourses(ID);
	}
	
	public boolean doRegistration(int ID) {
		return false;
	}
	
	public String payFees(int ID) {
		
		return null;
	}
	public String showDetails(int id) {
		return null;
	}

}
