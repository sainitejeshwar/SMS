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
		boolean flag = true;
		do {
			logger.info("\nChoices\n"
							+ "1.Show My Details\n"
							+ "2.View My Courses\n"
							+ "3.View Catalog\n"
							+ "4.Add Course\n"
							+ "5.Drop Course\n"
							+ "6.Start Registration\n"
							+ "7.Pay Semester Fees\n"
							+ "8.View Report card\n"
							+ "9.Logout");
			
			choice = studScanner.nextInt();
			switch (choice) {
				case 1:
					logger.info(studOperations.showDetails(student));
					break;
					
				case 2:
					student = studOperations.getStudent(user.getEmailID());
					logger.info("Already Added Courses  :");
					for(Integer itr: studentCourseDAO.getCourse(student.getStudentID())) {
						logger.info(getCourseName(itr) + "  "+itr);
					}
					logger.info("\nRegistered Courses : ");
					for(Integer itr : student.getStudentCourses()) {
						logger.info(getCourseName(itr));}
					break;
					
				case 3:
					studOperations.showCourse(student.getSemester());
					break;
					
				//Can only be done before registering
				case 4:
					logger.info(studOperations.addCourse(student, studScanner));
					break;
					
				//Can only be done before registering
				case 5:
					logger.info(studOperations.dropCourse(student, studScanner));
					break;
					
				case 6:
					try {
						logger.info(regOperations.doRegistration(student,studScanner));
					} catch (NotificationMessage e) {			// throws this exception when registration cannot be completed
						logger.error(e.getMessage());
					} catch (RegistrationEndedException e) { 	// Throws this when registration is ended
						logger.error(e.getMessage());
					}
					break;
					
				case 7:
					logger.info(studOperations.payFees(student.getRegistrationNumber()));
					break;
					
				case 8:
					studOperations.viewReportCard(user.getEmailID());
					break;
				case 9:
					flag = false;
					break;
				default:
					break;
			}
		}
		while(flag);
	logger.info("Logging Out at "+ DTUtils.currDateandTime());			// printing logging out Timestamp
	}
}
