package com.flipkart.client;


import java.util.Scanner;

import org.apache.log4j.Logger;


import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.NotificationMessage;
import com.flipkart.exception.RegistrationEndedException;
import com.flipkart.helper.clientHelper;
import com.flipkart.helper.operationHelper;

public class StudentClient implements clientHelper , operationHelper{
	private static  Logger logger = Logger.getLogger(StudentClient.class);
	private static Student student = new Student();

	public void landingPage(User user , Scanner studScanner) {
		
		student = studOperations.getStudent(user.getEmailID());					// get student details from database
		logger.debug("Logged in as Student :  "+getSalutation(user.getGender())+student.getName());   // getting salutation based on gender and concating with name
		
		//Functionalities for Student
		int choice;
		do {
			logger.info("\nChoices\n"
							+ "1.Show My Details\n"
							+ "2.View My Courses\n"
							+ "3.View Catalog\n"
							+ "4.Start Registration\n"
							+ "5.Pay Semester Fees\n"
							+ "6.View Report card\n"
							+ "7.Logout");
			
			choice = studScanner.nextInt();
			switch (choice) {
				case 1:
					logger.info(studOperations.showDetails(student));
					break;
					
				case 2:
					for(Integer itr : student.getStudentCourses()) {
						logger.info(getCourseName(itr)+" - ");
					}
					break;
					
				case 3:
					studOperations.showCourse(student.getSemester());
					break;
					
				case 4:
					try {
						logger.info(regOperations.doRegistration(student,studScanner));
					} catch (NotificationMessage e) {			// throws this exception when registration cannot be completed
						logger.error(e.getMessage());
					} catch (RegistrationEndedException e) { 	// Throws this when registration is ended
						logger.error(e.getMessage());
					}
					break;
					
				case 5:
					logger.info(studOperations.payFees(student.getRegistrationNumber()));
					break;
					
				case 6:
					studOperations.viewReportCard(user.getEmailID());
					break;
				
				default:
					break;
			}
		}
		while(choice == 1 || choice == 2|| choice == 3|| choice == 4|| choice == 5|| choice == 6);
	logger.info("Logging Out at "+ DTUtils.currDateandTime());			// printing logging out Timestamp
	}
}
