package com.flipkart.service;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.InvalidUserException;
import com.flipkart.helper.clientHelper;
import com.flipkart.helper.operationHelper;

/*
 * CLASS DESCRIPTION
 * 
 * All admin operations
 */
public class AdminOperations implements operationHelper,clientHelper {
	private static  Logger logger = Logger.getLogger(AdminOperations.class);

	
	//For adding a new User
	public void addUser(Scanner input) {
		User user = new User();
		user = addNewUser(input);   // User details are same for all 3 type of user
		authorCredentialDAO.addUser(user);
		
		//Determining the type of user and asking for specific informations and adding particular 
		//usee to their respective tables
		
		switch (user.getType()) {
			case 1:
				Admin admin = new Admin();
				logger.info("Enter Admin Level");
				admin.setLevel(input.next());
				adminDAO.addAdmin(user, admin);
				break;
			case 2:
				Student student = new Student();
				logger.info("Enter Student Semester :");
				student.setSemester(input.nextInt());
				logger.info("Enter Branch : ");
				student.setBranch(input.next());
				studentDAO.addStudent(user,student);
				break;
			case 3:
				professorDAO.addProf(user);
				break;
			default:
				break;
			}
	}
	//For updating user password
	public void updateUser(Scanner input ) {
		logger.info("Can only update Password");
		logger.info("Enter ID to Update Password : ");
		String emailID = null ;
		boolean flag = true;
		
		//checking wether such emailId exit or not
		while(flag) {
			logger.info("Enter EmailID : ");
			emailID = input.next();
				try {
					authorCredentialDAO.checkIdentity(emailID, "dummy");
				} catch (InvalidUserException e) {
					logger.info("Email ID Exists");
					flag = false;
				}
		}
		//checking that wether previous password matches or not
		flag = true;
		String password= null;
		while(flag) {
			logger.info("Enter previous Password");
			password = input.next();
			try {
				authorCredentialDAO.checkIdentity(emailID, password);
				flag = true;
			} catch (InvalidUserException e) {
				logger.error(e.getMessage());
			}
		}
		
		//if matches updating new password
		logger.info("Enter new Password : ");
		password = input.next();
		authorCredentialDAO.updateUser(emailID,password);
	}
	
	//view list all user base on type of user
	public void viewUsers(Scanner input) {
		logger.info("Enter User Type (student/admin/professor)");
		switch (input.next()) {
		case "student":
			logger.info("Name\tStudent ID\tCourses");
			getAllStudents()
				.forEach((student) -> logger.info( student.getName()+"\t\t"+student.getStudentID()+"\t\t"+student.getAllCourses()));
			break;
		case "admin":
			logger.info("Name\tAdmin ID\tLevel");
			getAllAdmins()
				.forEach((admin) -> logger.info(admin.getName()+"\t\t"+admin.getAdminID()+"\t\t"+admin.getLevel()));
			break;
		case "professor":
			logger.info("Name\tProfessor ID");
			getAllProfessors()
				.forEach((professor) ->logger.info(professor.getName()+"\t\t"+professor.getProfessorID()));
			break;
		default:
			break;
		}
	}
	//Adding new Course into catalog
	public void addCourse(Scanner input) {
		Course course = new Course();
		logger.info("Enter Course Name");
		course.setName(input.next());
		logger.info("Enter CatalogID in which course will be added");
		course.setCatalogID(input.nextInt());
		logger.info("Enter Fee for the course");
		course.setFees(input.nextInt());
		courseDAO.addCourse(course);
	}
	//Reseting the course means removing the professor teaching the course
	public void updateCourse(Scanner input) {
		logger.info("Enter Course Code to Reset");
		int courseCode = input.nextInt();
		courseDAO.resetCourse(courseCode);
	}
	//get Admin by ID
	public Admin getAdmin(String emailID) {
		return adminDAO.listByID(emailID);
	}
	//delete the user 
	public void deleteUser(Scanner input) {
		logger.info("Enter email ID to delete : ");
		String emailID = input.next();
		authorCredentialDAO.deleteUser(emailID);
	}
	
}
