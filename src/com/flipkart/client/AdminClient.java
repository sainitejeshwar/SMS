package com.flipkart.client;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.service.AdminOperations;
import com.flipkart.service.StudentOperations;

public class AdminClient {
	
	private static  Logger logger = Logger.getLogger(AdminClient.class);
	public void landingPage(int id) {
		
		Scanner input = new Scanner(System.in);
		AdminOperations adminOperations = new AdminOperations();
		
		logger.debug("Logged in as Admin "+id);
		int choice  = 7;
		do {
			
			logger.info("\nChoices\n"
					+ "1.Show My Details\n2.View My Courses\n3.View Catalog\n"
					+ "4.Start Registration\n5.Pay Semester Fees\n6.View Report card\n7.Logout");
			choice = input.nextInt();
			switch (choice) {
			case 1:
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
