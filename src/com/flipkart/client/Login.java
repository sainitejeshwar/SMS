package com.flipkart.client;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.DAO.AuthorCredentialDAO;
import com.flipkart.bean.User;
import com.flipkart.exception.InvalidUserException;
import com.flipkart.utils.DateTimeUtil;


public class Login{
	private static  Logger logger = Logger.getLogger(Login.class);
	public static void main (String[] args) {

		boolean exit = true;
		logger.info("SMS");
		Scanner input = new Scanner(System.in);
		String emailid = "";
		String password ="";
		AuthorCredentialDAO checker = new AuthorCredentialDAO();
		DateTimeUtil DTutil = new DateTimeUtil();
		User user = new User();
		
		while(exit) {
			
			logger.info("Enter ID ");
			emailid = input.next();
			if(emailid.equals("exit")) {
				exit = false;
				break;
			}
			logger.info("Enter Password");
			password = input.next();

			try {
				user = checker.checkIdentity(emailid, password);
				logger.info("Last Login : "+ DTutil.systemDateTime(checker.getLastLoginTimeStamp(emailid)));
				checker.updateLoginTimeStamp(emailid, DTutil.SQLdatetime() );
				switch(user.getType()) {
				
					case "student":
						StudentClient studentLogin = new StudentClient();
						studentLogin.landingPage(user,input);
						break;
						
					case "admin":
						AdminClient adminClient = new AdminClient();
						adminClient.landingPage(user,input);
						break;
						
					case "professor":
						ProfessorClient professorClient = new ProfessorClient();
						professorClient.landingPage(user,input);
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


