package com.flipkart.helper;

import org.apache.log4j.Logger;

import com.flipkart.DAO.AuthorCredentialDAO;
import com.flipkart.service.AdminOperations;
import com.flipkart.service.ProfessorOperations;
import com.flipkart.service.RegistrationOperations;
import com.flipkart.service.StudentOperations;
import com.flipkart.utils.DateTimeUtil;

/*
 * CLASS DESCRIPTION
 * 
 * HELPER INTERFACE FOR ALL CLIENT FUNTIONS
 * 
 * This also  includes object of classes that will be used in different client classes
 */

public interface clientHelper{
	
	//UTILS OBJECT(S)
	static DateTimeUtil  DTUtils= new DateTimeUtil();
	
	//OPERATION OBJECT(S)
	static  ProfessorOperations profOperations = new ProfessorOperations();
	static StudentOperations studOperations = new StudentOperations();
	static RegistrationOperations regOperations = new RegistrationOperations();
	static AdminOperations adminOperations = new AdminOperations();
	
	//DAO OBJECT(S)
	static AuthorCredentialDAO checker = new AuthorCredentialDAO();
	
	//LOGGER OBJECT(S)
	static  Logger logger = Logger.getLogger(clientHelper.class);
	
	default String getSalutation(String Gender) {				// returns salutation based on gender of the Gender
		if(Gender.equals("M"))
			return "Mr. ";
		return "Miss. ";
	}

}
