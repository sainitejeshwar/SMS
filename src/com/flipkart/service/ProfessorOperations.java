package com.flipkart.service;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.flipkart.DAO.CourseDAO;
import com.flipkart.DAO.ProfessorDAO;
import com.flipkart.DAO.StudentDAO;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.exception.InvalidCourseException;
import com.flipkart.helper.operationHelper;

public class ProfessorOperations implements operationHelper{
	private static  Logger logger = Logger.getLogger(ProfessorOperations.class);
	
	
	public void getStudentINCourse(int courseCode){
		//Student list
		studentDAO.listAll()
		.stream()
		.filter(student -> student.getStudentCourses().contains(String.valueOf(courseCode)))
		.forEach((student) -> logger.info(student.getName() +"\t"+student.getStudentID()));

		
	}
	public String uploadGrades(int id) {
		return null;  // list all updates made
	}
	public Professor getProfessor(String emailid) {
		return professorDAO.listByID(emailid);
	}
	
	public String addCourse(int courseCode1 , int profID) {
		try {
			isCourseContained(courseCode1, (ArrayList<Course>) returnCourseCatalog()
					.stream()
					.filter(course -> (course.getProf() == -1))
					.collect(Collectors.toList()));
			courseDAO.addCourseProf(courseCode1,profID);
			return "Course Added";
		} catch (InvalidCourseException e) {
			return e.getMessage();
		}
		
		
	}
	
}
