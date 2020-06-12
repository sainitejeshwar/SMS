package com.flipkart.client;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.DAO.AuthorCredentialDAO;
import com.flipkart.exception.InvalidUserException;

public class Login {
	private static  Logger logger = Logger.getLogger(Login.class);
	public static void main(String[] args) {
		
		
		/*
		int UserType = loginactivity(); --- service
		student interface
		proff interface
		admin interface
		
		
		logout -- 
		
		exceptions::
		course completed
		invalid user
		password exceptions
		payment passed or failed
		
		
		*/
		
		Scanner input = new Scanner(System.in);
		boolean exit = true;
		logger.info("SMS");
		while(exit) {
			
			
			boolean flag = true;
			String emailid;
			logger.info("Enter ID ");
			emailid = input.next();
			if(emailid.equals("exit")) {
				exit = false;
				break;
			}
			logger.info("Enter Password");
			String password = input.next();
			
			AuthorCredentialDAO checker = new AuthorCredentialDAO();
			
			String typeOfUser = null ;
			try {
				typeOfUser = checker.checkIdentity(emailid, password);
				switch(typeOfUser) {
					case "student":
						StudentClient studentLogin = new StudentClient();
						studentLogin.landingPage(emailid);
						break;
					case "admin":
						AdminClient adminClient = new AdminClient();
						adminClient.landingPage(emailid);
						break;
					case "professor":
						ProfessorClient professorClient = new ProfessorClient();
						professorClient.landingPage(emailid);
						break;
				}
			}
			catch (InvalidUserException e) {
				logger.error(e.getMessage());
			}
		}
		input.close();
		logger.info("Terminated.!");
	}
}


