package com.flipkart.client;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.bean.Professor;
import com.flipkart.bean.User;
import com.flipkart.helper.clientHelper;
import com.flipkart.service.ProfessorOperations;
import com.flipkart.utils.DateTimeUtil;


public class ProfessorClient implements clientHelper{
	private static  Logger logger = Logger.getLogger(ProfessorClient.class);
	private static DateTimeUtil  DTUtils= new DateTimeUtil();
	
	public void landingPage(User user , Scanner input) {
		final ProfessorOperations profOperations = new ProfessorOperations();
		final Professor prof =  profOperations.getProfessor(user.getEmailID());
		logger.debug("Logged in as Professor : "+getSalutation(user.getGender())+prof.getName());
		
		int choice  = 4;
		do {

			// view catalog
			logger.info("\nChoices\n1.View Students in a Course\n2.View My Courses\n3.Upload Grades\n4."
					+ "Add courses to teach\n5.Logout");
			choice = input.nextInt();
			switch (choice) {
			case 1:
				logger.info("Enter Course Code");
				int courseCode = input.nextInt();
				profOperations.getStudentINCourse(courseCode);
				break;
			case 2:
				profOperations.returnCourseCatalog()
					.stream()
					.filter(course -> course.getProf() == prof.getProfessorID())
					.forEach((course) -> logger.info(course.getCourseCode()+"\t"+course.getName()));
				break;
			case 3:
				logger.info("Your Courses");
				profOperations.returnCourseCatalog()
				.stream()
				.filter(course -> course.getProf() == prof.getProfessorID())
				.forEach((course) -> logger.info(course.getCourseCode()+"\t"+course.getName()));
				logger.info("Enter Course Code to Upload Marks");
				int courseCode1 = input.nextInt();
				profOperations.uploadGrades(courseCode1,input);
				break;
			case 4:
				profOperations.returnCourseCatalog()
				.stream()
				.filter(course -> (course.getProf() == -1))
				.forEach(course -> logger.info(course.getCourseCode()+"\t"+course.getName()));
				int courseCode11 = input.nextInt();
				logger.info(profOperations.addCourse(courseCode11 , prof.getProfessorID()));
				break;
			default:
				break;
			}
		}
		while(choice == 1 || choice == 2|| choice == 3|| choice == 4);
		logger.info("Logging Out at "+ DTUtils.currDateandTime());		
	}
}
