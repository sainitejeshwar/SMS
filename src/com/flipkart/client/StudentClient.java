package com.flipkart.client;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.bean.Student;
import com.flipkart.service.StudentOperations;

public class StudentClient {
	private static  Logger logger = Logger.getLogger(StudentClient.class);

	
	
	public void landingPage(String emailid) {
		Student student = new Student();
		Scanner studScanner = new Scanner(System.in);
		StudentOperations studOperations = new StudentOperations();
		
		
		student = studOperations.getStudent(emailid);
		logger.debug("Logged in as Student :  "+student.getName());
		
		
		int choice  = 7;
		do {
			
			logger.info("\nChoices\n1.Show My Details\n2.View My Courses\n3.View Catalog\n"
					+ "4.Add Course\n5.Drop Course\n"
					+ "6.Start Registration\n7.Pay Semester Fees\n8.View Report card\n"
					+ "9.Logout");
			choice = studScanner.nextInt();
			switch (choice) {
			case 1:
				logger.info("\nID :"+student.getStudentID()+"\nName:"+student.getName()+"\nContact Number:");
				break;
			case 2:
				logger.info("\nID : " + student.getStudentID() + "\n Name : "+student.getName()+"\n Courses :"
						+ student.getAllCourses());
				break;
			case 3:
				logger.info(studOperations.viewCourseCatalog());
				break;
			case 4:
				logger.info(studOperations.addCourse());
				break;
			case 5:
				logger.info(studOperations.dropCourse());
				break;
			case 6:
				logger.info(studOperations.doRegistration(emailid));
				break;
			case 7:
				logger.info(studOperations.payFees(emailid));
				break;
			case 8:
				logger.info(studOperations.generateReportCard(emailid));
				break;
			default:
				break;
			}
			}
			while(choice == 1 || choice == 2|| choice == 3|| choice == 4|| choice == 5|| choice == 6 || choice ==7 || choice ==8);
		
	studScanner.close();
	logger.info("Logging Out");

	}
	

}
