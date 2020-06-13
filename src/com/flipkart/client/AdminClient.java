package com.flipkart.client;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.service.AdminOperations;
import com.flipkart.service.StudentOperations;
import com.flipkart.utils.DateTimeUtil;

public class AdminClient {
	
	private static  Logger logger = Logger.getLogger(AdminClient.class);
	private static DateTimeUtil  DTUtils= new DateTimeUtil();
	
	
	public void landingPage(String emailID) {
		
		Scanner input = new Scanner(System.in);
		AdminOperations adminOperations = new AdminOperations();
		
		logger.debug("Logged in as Admin "+emailID);
		int choice  = 7;
		do {
			// view catalog
			logger.info("\nChoices\n"
					+ "1.Add User\n2.Update User\n3.View Users\n"
					+ "4.Add Course\n5.Update Course\n6.View Courses\n7.Logout");
			choice = input.nextInt();
			switch (choice) {
			case 1:
				break;
			default:
				break;
			}
			}
			while(choice == 1 || choice == 2|| choice == 3|| choice == 4|| choice == 5|| choice == 6);

	logger.info("Logging Out at "+ DTUtils.currDateandTime());
	input.close();
	
	}
	
}
