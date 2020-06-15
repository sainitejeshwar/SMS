package com.flipkart.client;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.bean.Professor;
import com.flipkart.bean.User;
import com.flipkart.helper.clientHelper;
import com.flipkart.service.ProfessorOperations;
import com.flipkart.utils.DateTimeUtil;
/*
 * CLASS DESCRIPTIONS
 * 
 * Landing page for the professor
 * Professor can chose from number of functionalities avaliable
 */

public class ProfessorClient implements clientHelper{
	private static  Logger logger = Logger.getLogger(ProfessorClient.class);
	 
	public void landingPage(User user , Scanner input) {
		
		Professor prof =  profOperations.getProfessor(user.getEmailID());								//get professor details from DB
		logger.debug("Logged in as Professor : "+getSalutation(user.getGender())+prof.getName());		// Printing professor name with salutation
		
		//PROFESSOR FUNCTIONALITIES
		int choice;
		do {
			logger.info("\nChoices\n"
							+ "1.View Students in a Course\n"
							+ "2.View My Courses\n"
							+ "3.Upload Grades\n"
							+ "4.Add courses to teach\n"
							+ "5.Logout");
			choice = input.nextInt();
			switch (choice) {
				case 1:
					profOperations.getStudentINCourse(input);
					break;
					
				case 2:
					profOperations.returnCourseCatalog()													// returns list of all courses	
						.stream()																			// converting to stream
						.filter(course -> course.getProf() == prof.getProfessorID())						//getting courses of professor
						.forEach((course) -> logger.info(course.getCourseCode()+"\t"+course.getName()));	//printing courses
					break;
					
				case 3:
					profOperations.uploadGrades(input , prof.getProfessorID());
					break;
					
				case 4:
					logger.info(profOperations.addCourseforTeaching(input , prof.getProfessorID()));
					break;
					
				default:
					break;
				}
		}
		while(choice == 1 || choice == 2|| choice == 3|| choice == 4);
		
		logger.info("Logging Out at "+ DTUtils.currDateandTime());					// prinitng logging out timestamp
	}
}
