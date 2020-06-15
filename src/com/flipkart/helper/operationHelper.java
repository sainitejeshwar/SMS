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
import com.flipkart.DAO.StudentDAO;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.InvalidCourseException;
import com.flipkart.exception.InvalidUserException;

public interface operationHelper {
	static  Logger logger = Logger.getLogger(operationHelper.class);
	
	static AuthorCredentialDAO authorCredentialDAO = new AuthorCredentialDAO();
	static CourseDAO courseDAO = new CourseDAO();
	static AdminDAO adminDAO = new AdminDAO();
	static StudentDAO studentDAO = new StudentDAO();
	static ProfessorDAO professorDAO = new ProfessorDAO();
	static RegistrationDAO registrationDAO = new RegistrationDAO();
	static MarksDAO marksDAO = new MarksDAO();
	static PaymentDAO paymentDAO = new PaymentDAO();
	
	static LocalDate RegistrationEndDate = LocalDate.of(2020, 7, 1);
	static AuthorCredentialDAO checker = new AuthorCredentialDAO();
	
	default public  void viewCourseCatalog(){
		ArrayList<Course> allCourses = new ArrayList<Course>();
		allCourses.addAll(courseDAO.listAll());
		if(allCourses != null)
			allCourses
			.forEach((course) -> logger.info(course.getCourseCode()+"\t"+course.getName()+"\t"
					+ course.getProf()+"\t"+course.getNumberofStudents()));	
	}
	default public  ArrayList<Course> returnCourseCatalog(){
		return courseDAO.listAll();
	}
	default public ArrayList<Student> getAllStudents(){
		return studentDAO.listAll();
	}
	default public ArrayList<Professor> getAllProfessors(){
		return professorDAO.listAll();
	}
	default public ArrayList<Admin> getAllAdmins(){
		return adminDAO.listAll();
	}
	default public Course isCourseContained(int courseCode , ArrayList<Course> courseList) throws InvalidCourseException{
		for (Course course : courseList) {
			if(course.getCourseCode() == courseCode)
				return course;
		}
		throw new InvalidCourseException(courseCode);
	}
	default public String getCourseName(int courseCode) {
		String name = "";
		try {
			name = returnCourseCatalog()
			.stream()
			.filter((course) -> course.getCourseCode() == courseCode)
			.collect(Collectors.toList()).get(0).getName();
		}
		catch (IndexOutOfBoundsException e) {
			name = "Not Available";
		}
		return name;
	}
	default public User addNewUser(Scanner input) {
		User user = new User();
		logger.info("Enter Name : ");
		user.setName(input.next());
		String emailID = "";
		boolean flag = true;
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
		user.setEmailID(emailID);
		logger.info("Enter Password");
		user.setPassword(input.next());
		logger.info("Enter Type \nChoices\t 1.admin\t2.student\t3.professor");
		user.setType(input.nextInt());
		logger.info("Enter Gender (M/F)");
		user.setGender(input.next());
		return user;
	}
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
