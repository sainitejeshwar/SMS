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

/*
 * CLASS DESCRIPTION
 * 
 * Class contains professor operations which will be called by professor client and which will call DAO operations
 * Rpofessor operations includes
 * 			- get student in a course
 * 			- upload grades for a course
 * 			- add course for teaching 
 * 			- get all professors
 */

public class ProfessorOperations implements operationHelper{
	private static  Logger logger = Logger.getLogger(ProfessorOperations.class);
	
	
	public void getStudentINCourse(Scanner input){			//checks using course code
		logger.info("Enter Course Code");
		int courseCode = input.nextInt();
		getAllStudents()																			//get all student details
			.stream()
			.filter(student -> student.getStudentCourses().contains((courseCode))) 					//filter that check if student course contains that course
			.forEach((student) -> logger.info(student.getName() +"\t"+student.getStudentID()));		//print the data
	}
	
	//professor can only upload grades for his own course
	public void uploadGrades(Scanner input , int ProfID) {
		logger.info("Your Courses");
		ArrayList<Course> myCourses = new ArrayList<Course>();					
		myCourses.addAll(returnCourseCatalog()
			.stream()
			.filter(course -> course.getProf() == ProfID)					//filtering course professor's course
			.collect(Collectors.toList()));									//collecting to a list
		
		//Printing the list
		for(Course course : myCourses) {
			logger.info(course.getCourseCode()+"\t"+course.getName());
		}
		
		logger.info("Enter Course Code to Upload Marks");
		int courseCode = input.nextInt();
		try {
			if(isCourseContained(courseCode, myCourses) != null) {}							//Checking if the course added is valid or not
		}
		catch (InvalidCourseException e) {
			logger.info("Course with Course code : "+e.Message()+" invalid for you");		//else throws exception
			return;
		}
		int index = 0;
		logger.info("Enter Marks for : ");
		for (Student student : getAllStudents()
				.stream()
				.filter(student -> student.getStudentCourses().contains((courseCode)))		//from list all students getting student registered for that course
				.collect(Collectors.toList())) {											
			logger.info(student.getName()+"\t"+student.getStudentID()+" :");
			index = student.getStudentCourses().indexOf(courseCode);						//getting the course code index to determine which course is that
			int grade = input.nextInt();
			student.setMarks(index, grade);													//setting up marks
			studentDAO.setGrades(index,student);											//updating the grades in student table
		}
	}
	
	//getting professor details by ID
	public Professor getProfessor(String emailid) {
		return professorDAO.listByID(emailid);												//listing professor by ID
	}
	
	//Adds course for teaching
	public String addCourseforTeaching(Scanner input , int profID) {
		logger.info("Avaliable Courses for you");
		returnCourseCatalog()
			.stream()
			.filter(course -> (course.getProf() == -1))										//from list of all courses getting course that doenot have professor assigned
			.forEach(course -> logger.info(course.getCourseCode()+"\t"+course.getName()));	//printing the list to slect from
		int courseCode1 = input.nextInt();
		try {
			isCourseContained(courseCode1, (ArrayList<Course>) returnCourseCatalog()
					.stream()
					.filter(course -> (course.getProf() == -1))
					.collect(Collectors.toList()));
			courseDAO.addCourseProf(courseCode1,profID);									//adding the course if the course is not taken and is valid course code
			return "Course Added";
		} catch (InvalidCourseException e) {												//else throws exxception
			return (getCourseName(e.Message()) + " Or this course is not valid for you");
		}
	}
	
}
