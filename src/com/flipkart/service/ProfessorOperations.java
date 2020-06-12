package com.flipkart.service;

import org.apache.log4j.Logger;

import com.flipkart.DAO.CourseDAO;
import com.flipkart.DAO.ProfessorDAO;
import com.flipkart.bean.Professor;
import com.flipkart.helper.operationHelper;

public class ProfessorOperations implements operationHelper{
	private static  Logger logger = Logger.getLogger(ProfessorOperations.class);
	private CourseDAO courseDAO = new CourseDAO();
	private ProfessorDAO professorDAO = new ProfessorDAO();
	
	
	public String getStudentINCourse(int courseCode){
		return courseDAO.getAllStudents(courseCode);
	}
	public String uploadGrades(int id) {
		return null;  // list all updates made
	}
	public String viewMyCourse(int id) {
		return professorDAO.getAllCourse(id);
	}
	public String myDetails(int id) {
		return null;
	}
	
}
