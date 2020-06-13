package com.flipkart.client;

import java.time.LocalDateTime;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.DAO.AuthorCredentialDAO;
import com.flipkart.exception.InvalidUserException;
import com.flipkart.utils.DateTimeUtil;


public class Login{
	private static  Logger logger = Logger.getLogger(Login.class);
	public static void main (String[] args) {
		// pass Scanner to each client and deep down the function Calls
		
		boolean exit = true;
		logger.info("SMS");
		Scanner input = new Scanner(System.in);
		String emailid = "";
		String password ="";
		AuthorCredentialDAO checker = new AuthorCredentialDAO();
		DateTimeUtil DTutil = new DateTimeUtil();
		
		while(exit) {
			
			logger.info("Enter ID ");
			emailid = input.next();
			if(emailid.equals("exit")) {
				exit = false;
				break;
			}
			logger.info("Enter Password");
			password = input.next();
			String typeOfUser = null ;
			try {
				typeOfUser = checker.checkIdentity(emailid, password);
				logger.info("Last Login : "+ DTutil.systemDateTime(checker.getLastLoginTimeStamp(emailid)));
				checker.updateLoginTimeStamp(emailid, DTutil.SQLdatetime() );
				switch(typeOfUser) {
				
					case "student":
						StudentClient studentLogin = new StudentClient();
						studentLogin.landingPage(emailid,input);
						break;
						
					case "admin":
						AdminClient adminClient = new AdminClient();
						adminClient.landingPage(emailid);
						break;
						
					case "professor":
						ProfessorClient professorClient = new ProfessorClient();
						professorClient.landingPage(emailid,input);
						break;
				}
			}
			catch (InvalidUserException e) {
				logger.error(e.getMessage());
			}
		}
		logger.info("Terminated.!");
		
		// TODO  -  Unique RegistrationNumber Generation - same for transacction number 
	}
}


