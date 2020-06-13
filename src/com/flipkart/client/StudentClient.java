package com.flipkart.client;


import java.util.Scanner;

import org.apache.log4j.Logger;


import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.NotificationMessage;
import com.flipkart.helper.clientHelper;
import com.flipkart.service.RegistrationOperations;
import com.flipkart.service.StudentOperations;
import com.flipkart.utils.DateTimeUtil;

public class StudentClient implements clientHelper{
	private static  Logger logger = Logger.getLogger(StudentClient.class);
	private static DateTimeUtil  DTUtils= new DateTimeUtil();

	public void landingPage(User user , Scanner studScanner) {
		
		Student student = new Student();
		StudentOperations studOperations = new StudentOperations();
		RegistrationOperations regOperations = new RegistrationOperations();
		
		
		student = studOperations.getStudent(user.getEmailID());
		logger.debug("Logged in as Student :  "+getSalutation(user.getGender())+student.getName());
		
		
		int choice  = 7;
		do {
			
			logger.info("\nChoices\n1.Show My Details\n2.View My Courses\n3.View Catalog\n"
					+ "4.Start Registration\n5.Pay Semester Fees\n6.View Report card\n"
					+ "7.Logout");
			
			choice = studScanner.nextInt();
			switch (choice) {
			
			case 1:
				logger.info(studOperations.showDetails(student));
				break;
				
			case 2:
				logger.info("ID : " + student.getStudentID() + "\n Name : "+student.getName()+"\n Courses :"
						+ student.getAllCourses());
				break;
				
			case 3:
				studOperations.showCourse();
				break;
				
			case 4:
				try {
					logger.info(regOperations.doRegistration(student,studScanner));
				} catch (NotificationMessage e) {
					logger.error(e.getMessage());
				}
				break;
				
			case 5:
				logger.info(studOperations.payFees(student.getRegistrationNumber()));
				break;
				
			case 6:
				logger.info(studOperations.viewReportCard(student.getStudentID()));
				break;
			
			default:
				break;
			}
		}
		while(choice == 1 || choice == 2|| choice == 3|| choice == 4|| choice == 5|| choice == 6);
		
	logger.info("Logging Out at "+ DTUtils.currDateandTime());

	}
	

}
