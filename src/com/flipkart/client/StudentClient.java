package com.flipkart.client;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.service.StudentOperations;

public class StudentClient {
	private static  Logger logger = Logger.getLogger(StudentClient.class);
	public void landingPage(int id) {
		
		Scanner input = new Scanner(System.in);
		StudentOperations studOperations = new StudentOperations();
		logger.debug("Logged in as Student "+id);
		
		int choice  = 7;
		do {
			
			logger.info("\nChoices\n1.Show My Details\n2.View My Courses\n3.View Catalog\n"
					+ "4.Start Registration\n5.Pay Semester Fees\n6.View Report card\n7.Logout");
			choice = input.nextInt();
			switch (choice) {
			case 1:
				logger.info(studOperations.showDetails(id));
				break;
			case 2:
				logger.info(studOperations.viewMyCourse(id));
				break;
			case 3:
				logger.info(studOperations.viewCourseCatalog());
				break;
			case 4:
				logger.info(studOperations.doRegistration(id));
				break;
			case 5:
				logger.info(studOperations.payFees(id));
				break;
			case 6:
				logger.info(studOperations.generateReportCard(id));
				break;
			default:
				break;
			}
			}
			while(choice == 1 || choice == 2|| choice == 3|| choice == 4|| choice == 5|| choice == 6);
		
	input.close();
	logger.info("Logging Out");

	}
	

}
