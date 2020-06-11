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
			int id = 0;
			do {
				logger.info("Enter ID ");
				String ID = input.next();
				if(ID.equals("exit")) {
					exit = false;
					break;
				}
				try {
					id = Integer.parseInt(ID);
					flag = false;
				}
				catch (NumberFormatException e) {
					logger.debug("Invalid Entry..!!");
				}
			}
			while(flag);
			if(exit == false)
				break;
			
			logger.info("Enter Password");
			String password = input.next();
			
			AuthorCredentialDAO checker = new AuthorCredentialDAO();
			
			String typeOfUser = null ;
			try {
				typeOfUser = checker.checkIdentity(id, password);
				if(typeOfUser.equals("student")) {
					StudentClient studentLogin = new StudentClient();
					studentLogin.landingPage(id);
				}
				else if (typeOfUser.equals("admin")) {
					AdminClient adminClient = new AdminClient();
					adminClient.landingPage(id);
				}
				else if (typeOfUser.equals("professor")) {
					ProfessorClient professorClient = new ProfessorClient();
					professorClient.landingPage(id);
				}
			}
			catch (InvalidUserException e) {
				logger.error(e.getMessage());
			}
			
		}
		input.close();
	}

}
