package com.flipkart.client;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.bean.Admin;
import com.flipkart.bean.User;
import com.flipkart.helper.clientHelper;
import com.flipkart.service.AdminOperations;
import com.flipkart.service.StudentOperations;
import com.flipkart.utils.DateTimeUtil;

public class AdminClient implements clientHelper {
	
	private static  Logger logger = Logger.getLogger(AdminClient.class);
	private static DateTimeUtil  DTUtils= new DateTimeUtil();
	
	
	public void landingPage(User user, Scanner input) {
		
		AdminOperations adminOperations = new AdminOperations();
		Admin admin = new Admin();
		
		admin = adminOperations.getAdmin(user.getemailID());
		logger.debug("Logged in as Admin: "+getSalutation(user.getGender())+admin.getName());
		
		int choice  = 7;
		do {
			// view catalog
			logger.info("\nChoices\n"
					+ "1.Add User\n2.Update User\n3.View All Users\n"
					+ "4.Add Course\n5.Reset Course\n6.View Courses\n7.Logout");
			choice = input.nextInt();
			switch (choice) {
			case 1:
				adminOperations.addUser(input);
				break;
			case 2:
				logger.info("Can only update Password");
				adminOperations.updateUser(input);
				break;
			case 3:
				adminOperations.viewUsers(input);
				break;
			case 4:
				adminOperations.addCourse(input);
				break;
			case 5:
				adminOperations.updateCourse(input);
				break;
			case 6:
				adminOperations.viewCourseCatalog();
			default:
				break;
			}
		}
		while(choice == 1 || choice == 2|| choice == 3|| choice == 4|| choice == 5|| choice == 6);

	logger.info("Logging Out at "+ DTUtils.currDateandTime());
	
	}
	
}
