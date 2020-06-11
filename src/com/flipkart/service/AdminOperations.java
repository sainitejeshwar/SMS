package com.flipkart.service;

import org.apache.log4j.Logger;

import com.flipkart.DAO.AuthorCredentialDAO;
import com.flipkart.DAO.CourseCatalogDAO;
import com.flipkart.DAO.CourseDAO;

public class AdminOperations {
	private static  Logger logger = Logger.getLogger(AdminOperations.class);
	AuthorCredentialDAO authCredDAO = new AuthorCredentialDAO();
	CourseDAO courseDAO = new CourseDAO();
	
	
	/*
	 * CRUD operations for course and user
	 */
	public void addUser(Object obj, String type) {
		
	}
}
