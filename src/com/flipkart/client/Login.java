package com.flipkart.client;

import java.time.LocalDateTime;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.DAO.AuthorCredentialDAO;
import com.flipkart.exception.InvalidUserException;


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
		
		while(exit) {
			
			logger.info("Enter ID ");
			emailid = input.nextLine();
			if(emailid.equals("exit")) {
				exit = false;
				break;
			}
			logger.info("Enter Password");
			password = input.nextLine();
			String typeOfUser = null ;
			try {
				typeOfUser = checker.checkIdentity(emailid, password);
				logger.info("Last Login : "+checker.getLastLoginTimeStamp(emailid));
				
				checker.updateLoginTimeStamp(emailid,DateandTime() );
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
		
		// TODO  - same error now in student operations , Unique RegistrationNumber Generation - same for transacction number 
	}
	private static String DateandTime() {
		LocalDateTime localDateTime = LocalDateTime.now();
		return localDateTime.getYear() + "-" +localDateTime.getMonthValue()+"-"+localDateTime.getDayOfMonth()+" "
				+ localDateTime.getHour()+":"+localDateTime.getMinute()+":"+localDateTime.getSecond();

	}
	
}


