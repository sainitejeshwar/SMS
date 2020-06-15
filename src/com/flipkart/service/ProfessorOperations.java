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
	
	
	public void getStudentINCourse(Scanner input){
		logger.info("Enter Course Code");
		int courseCode = input.nextInt();
		getAllStudents()
			.stream()
			.filter(student -> student.getStudentCourses().contains((courseCode)))
			.forEach((student) -> logger.info(student.getName() +"\t"+student.getStudentID()));
	}
	
	public void uploadGrades(Scanner input , int ProfID) {
		logger.info("Your Courses");
		returnCourseCatalog()
			.stream()
			.filter(course -> course.getProf() == ProfID)
			.forEach((course) -> logger.info(course.getCourseCode()+"\t"+course.getName()));
		logger.info("Enter Course Code to Upload Marks");
		int courseCode = input.nextInt();
		int index = 0;
		logger.info("Enter Marks for : ");
		for (Student student : getAllStudents()
				.stream()
				.filter(student -> student.getStudentCourses().contains((courseCode)))
				.collect(Collectors.toList())) {
			logger.info(student.getName()+"\t"+student.getStudentID()+" :");
			index = student.getStudentCourses().indexOf(courseCode);
			int grade = input.nextInt();
			student.setMarks(index, grade);
			studentDAO.setGrades(index,student);
		}
	}
	
	
	public Professor getProfessor(String emailid) {
		return professorDAO.listByID(emailid);
	}
	
	public String addCourseforTeaching(int courseCode1 , int profID) {
		try {
			isCourseContained(courseCode1, (ArrayList<Course>) returnCourseCatalog()
					.stream()
					.filter(course -> (course.getProf() == -1))
					.collect(Collectors.toList()));
			courseDAO.addCourseProf(courseCode1,profID);
			return "Course Added";
		} catch (InvalidCourseException e) {
			return (getCourseName(e.Message()) + "  is not valid for you");
		}
	}
	
}
