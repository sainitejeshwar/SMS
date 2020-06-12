package com.flipkart.client;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.service.ProfessorOperations;


public class ProfessorClient {
	private static  Logger logger = Logger.getLogger(ProfessorClient.class);
	public void landingPage(String emailID) {
		
		Scanner input = new Scanner(System.in);
		ProfessorOperations profOperations = new ProfessorOperations();
		logger.debug("Logged in as Professor : "+emailID);
		
		int choice  = 4;
		do {
			int id = 0;
			// view catalog
			logger.info("\nChoices\n1.View Students in a Course\n2.View My Courses\n3.Upload Grades\n4.Logout");
			choice = input.nextInt();
			switch (choice) {
			case 1:
				logger.info("Enter Course Code");
				int courseCode = input.nextInt();
				logger.info(profOperations.getStudentINCourse(courseCode));
				break;
			case 2:
				logger.info(profOperations.viewMyCourse(id));
				break;
			case 3:
				logger.info(profOperations.uploadGrades(id));
				break;
			default:
				break;
			}
			}
			while(choice == 1 || choice == 2|| choice == 3);
	logger.info("Logging Out");		
	input.close();
	}
}
