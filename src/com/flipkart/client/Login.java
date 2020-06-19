package com.flipkart.client;

import java.util.Scanner;

import org.apache.log4j.Logger;


import com.flipkart.bean.User;
import com.flipkart.exception.InvalidUserException;
import com.flipkart.helper.clientHelper;


/*
 * CLASS DESCRIPTION
 * Login page of the course management system
 * 
 * Redirects the student , admin and professor to respective clients
 */

public class Login implements clientHelper{
	private static  Logger logger = Logger.getLogger(Login.class);
	private static User user = new User();
	
	public static void main (String[] args) {
		logger.info("*************WELCOME TO STUDENT MANAGEMENT SYSTEM***********");
		Scanner input = new Scanner(System.in);			// Scanner object for taking input
		String emailid = "" , password ="";
		boolean exit = true;
		
		while(exit) {
			logger.info("Enter ID ");
			emailid = input.next();
			if(emailid.equals("exit")) {				// Exits the login portal when exit is typed in as emailid
				exit = false;							// Else can login again
				break;
			}
			logger.info("Enter Password ");
			password = input.next();

			try {
				user = checker.checkIdentity(emailid, password);		// check whether its a valid user or not
				logger.info("Last Login : "+ DTUtils.systemDateTime(checker.getLastLoginTimeStamp(emailid)));  // prints last login time stamp
				checker.updateLoginTimeStamp(emailid, DTUtils.SQLdatetime() );    		// Updating last login timestamp in database for the user
				
				
				//enterin different landing page based on type of user
				switch(user.getType()) {
				
					case 1:
						AdminClient adminClient = new AdminClient();
						adminClient.landingPage(user,input);					//Admin Landing page
						break;
						
					case 2:
						StudentClient studentLogin = new StudentClient();
						studentLogin.landingPage(user,input);					//Student Landing page
						break;
						
					case 3:
						ProfessorClient professorClient = new ProfessorClient();
						professorClient.landingPage(user,input);				// Professor landing page
						break;
				}
			} catch (InvalidUserException e) {									// id or password doenot match with one saved in data base throws this exception
				logger.error(e.getMessage());
			}
			catch (NullPointerException e) {
				logger.error("Invalid Id or Password");
			}
		}
		logger.info("Terminated.!");
	}
}


