package com.flipkart.service;

import org.apache.log4j.Logger;

import com.flipkart.DAO.CourseCatalogDAO;
import com.flipkart.DAO.CourseDAO;
import com.flipkart.DAO.StudentDAO;
import com.flipkart.bean.Student;

public class StudentOperations {
	private static  Logger logger = Logger.getLogger(StudentOperations.class);
	private CourseDAO courseDAO = new CourseDAO();
	private StudentDAO studentDAO = new StudentDAO();
	Student student = new Student();
	
	public String generateReportCard(String emailid) {
		return null;
	}
	
	public String viewCourseCatalog(int sem) {
		return courseDAO.listAll(sem);
	}
	
	public boolean doRegistration(String emailid) {
		return false;
	}
	
	public String payFees(String emailid) {
		
		return null;
	}
	public Student getStudent(String emailid) {
		return studentDAO.listByID(emailid);
	}

	public boolean addCourse() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean dropCourse() {
		// TODO Auto-generated method stub
		return false;
	}

}
