package com.flipkart.helper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.flipkart.DAO.AdminDAO;
import com.flipkart.DAO.AuthorCredentialDAO;
import com.flipkart.DAO.CourseDAO;
import com.flipkart.DAO.MarksDAO;
import com.flipkart.DAO.PaymentDAO;
import com.flipkart.DAO.ProfessorDAO;
import com.flipkart.DAO.RegistrationDAO;
import com.flipkart.DAO.StudentCourseDAO;
import com.flipkart.DAO.StudentDAO;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.InvalidCourseException;
import com.flipkart.exception.InvalidUserException;

/*
 * CLASS DESCRIPTION
 * 
 * Helper Interface for all Service classes 
 * 
 * 		- Instantiate object of all DAO classes that will be used across all operation classes
 * 		- contains many functions that will be used in operations classes 
 */


public interface operationHelper {
	//LOGGER OBJECT(S)
	static  Logger logger = Logger.getLogger(operationHelper.class);
	
	//DAO OBJECT(S)
	static AuthorCredentialDAO authorCredentialDAO = new AuthorCredentialDAO();
	static CourseDAO courseDAO = new CourseDAO();
	static AdminDAO adminDAO = new AdminDAO();
	static StudentDAO studentDAO = new StudentDAO();
	static ProfessorDAO professorDAO = new ProfessorDAO();
	static RegistrationDAO registrationDAO = new RegistrationDAO();
	static MarksDAO marksDAO = new MarksDAO();
	static PaymentDAO paymentDAO = new PaymentDAO();
	static StudentCourseDAO studentCourseDAO = new StudentCourseDAO();
	
	//REGISTRATION END DATE
	static LocalDate RegistrationEndDate = LocalDate.of(2020, 7, 1);
	
	//Prints all the courses in  a catalog
	default public  void viewCourseCatalog(){
		ArrayList<Course> allCourses = new ArrayList<Course>();
		allCourses.addAll(courseDAO.listAll());
		if(allCourses != null)
			allCourses
			.forEach((course) -> logger.info(course.getCourseCode()+"\t"+course.getName()+"\t"
					+ course.getProf()+"\t"+course.getNumberofStudents()));	
	}
	
	//Returns the complete catalog as Arraylist of courses
	default public  ArrayList<Course> returnCourseCatalog(){
		return courseDAO.listAll();
	}
	
	//Returns all student list as ArrayList of student
	default public ArrayList<Student> getAllStudents(){
		return studentDAO.listAll();
	}
	
	//Returns all professors as ArrayList of professor
	default public ArrayList<Professor> getAllProfessors(){
		return professorDAO.listAll();
	}
	
	//Returns all admins as ArrayList of admin
	default public ArrayList<Admin> getAllAdmins(){
		return adminDAO.listAll();
	}
	
	//Checker wether a course is contained in given course list
	//returns course if present else throws exception
	default public Course isCourseContained(int courseCode , ArrayList<Course> courseList) throws InvalidCourseException{
		for (Course course : courseList) {
			if(course.getCourseCode() == courseCode)
				return course;
		}
		throw new InvalidCourseException(courseCode);
	}
	
	//Returns course name corresponding to given course code
	default public String getCourseName(int courseCode) {
		String name = "";
		try {
			name = returnCourseCatalog()
			.stream()
			.filter((course) -> course.getCourseCode() == courseCode)		//Filter only that course from list of all courses
			.collect(Collectors.toList()).get(0).getName();					//Collected the result into list and this will contian single entry so
																			//first element is our answer
		}
		catch (IndexOutOfBoundsException e) {								// if now such course the throws exception
			name = "No Such Course";
		}
		return name;
	}
	default public Course getCourseDetails(int courseCode) {
		Course course1 = new Course();
		try {
			 course1 = returnCourseCatalog()
			.stream()
			.filter((course) -> course.getCourseCode() == courseCode)		//Filter only that course from list of all courses
			.collect(Collectors.toList()).get(0);					//Collected the result into list and this will contian single entry so
																			//first element is our answer
		}
		catch (IndexOutOfBoundsException e) {								// if now such course the throws exception
			return null;
		}
		return course1;
	}
	
	//Add user into User table
	default public User addNewUser(Scanner input) {
		User user = new User();
		logger.info("Enter Name : ");
		user.setName(input.next());
		String emailID = "";
		boolean flag = true;
		
		//checks wether its a unique email id or not else throws different exceptions and prompt to again input different EmailId
		while(flag) {
			logger.info("Enter EmailID : ");
			emailID = input.next();
				try {
					authorCredentialDAO.checkIdentity(emailID, "dummy");
				} catch (InvalidUserException e) {
					logger.info("Email ID already Exists");
				} catch (NullPointerException e) {
					logger.info("Email ID available");
					flag = false;
				}
		}
		//Entering other user details
		user.setEmailID(emailID);
		logger.info("Enter Password");
		user.setPassword(input.next());
		logger.info("Enter Type \nChoices\t 1 = admin\t2 = student\t3 = professor");
		user.setType(input.nextInt());
		logger.info("Enter Gender (M/F)");
		user.setGender(input.next());
		return user;
	}
	
	//Check wether registrations has ended or not
	default public boolean isAfterDateRegistrationDate() {
		LocalDate localDate1 = LocalDate.now();
		LocalDate localDate2 = RegistrationEndDate;
		if (localDate1.isAfter(localDate2)) {
			return true;
		} else {
			return false;
		}
	}
	
}
