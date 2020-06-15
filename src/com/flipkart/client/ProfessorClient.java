package com.flipkart.client;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.bean.Professor;
import com.flipkart.bean.User;
import com.flipkart.helper.clientHelper;
import com.flipkart.service.ProfessorOperations;
import com.flipkart.utils.DateTimeUtil;


public class ProfessorClient implements clientHelper{
	private static  Logger logger = Logger.getLogger(ProfessorClient.class);
	private static DateTimeUtil  DTUtils= new DateTimeUtil();
	private static  ProfessorOperations profOperations = new ProfessorOperations();
	 
	public void landingPage(User user , Scanner input) {
		Professor prof =  profOperations.getProfessor(user.getEmailID());
		logger.debug("Logged in as Professor : "+getSalutation(user.getGender())+prof.getName());
		
		int choice;
		do {
			logger.info("\nChoices\n"
							+ "1.View Students in a Course\n"
							+ "2.View My Courses\n"
							+ "3.Upload Grades\n"
							+ "4.Add courses to teach\n"
							+ "5.Logout");
			choice = input.nextInt();
			switch (choice) {
			case 1:
				profOperations.getStudentINCourse(input);
				break;
				
			case 2:
				profOperations.returnCourseCatalog()
					.stream()
					.filter(course -> course.getProf() == prof.getProfessorID())
					.forEach((course) -> logger.info(course.getCourseCode()+"\t"+course.getName()));
				break;
				
			case 3:
				profOperations.uploadGrades(input , prof.getProfessorID());
				break;
				
			case 4:
				profOperations.returnCourseCatalog()
				.stream()
				.filter(course -> (course.getProf() == -1))
				.forEach(course -> logger.info(course.getCourseCode()+"\t"+course.getName()));
				int courseCode11 = input.nextInt();
				logger.info(profOperations.addCourseforTeaching(courseCode11 , prof.getProfessorID()));
				break;
				
			default:
				break;
			}
		}
		while(choice == 1 || choice == 2|| choice == 3|| choice == 4);
		logger.info("Logging Out at "+ DTUtils.currDateandTime());		
	}
}
