package com.flipkart.service;


import java.util.ArrayList;

import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;


import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.InvalidCourseException;
import com.flipkart.helper.operationHelper;

public class ProfessorOperations implements operationHelper{
	private static  Logger logger = Logger.getLogger(ProfessorOperations.class);
	
	
	public void getStudentINCourse(int courseCode){
		StudentList
		.stream()
		.filter(student -> student.getStudentCourses().contains((courseCode)))
		.forEach((student) -> logger.info(student.getName() +"\t"+student.getStudentID()));
		
	}
	public void uploadGrades(int courseCode , Scanner input) {
		ArrayList<Integer> marks = new ArrayList<Integer>();
		int ind = 0;
		logger.info("Enter Marks for : ");
		for (Student student : StudentList
				.stream()
				.filter(student -> student.getStudentCourses().contains((courseCode)))
				.collect(Collectors.toList())) {
			logger.info(student.getName()+"\t"+student.getStudentID()+" :");
			int grade = input.nextInt();
			student.setMarks(ind, grade);
			studentDAO.setGrades(ind,student);
		}
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
