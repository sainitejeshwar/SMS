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

public class AdminOperations implements operationHelper,clientHelper {
	private static  Logger logger = Logger.getLogger(AdminOperations.class);

	public void addUser(Scanner input) {
		User user = new User();
		user = addNewUser(input);
		authorCredentialDAO.addUser(user);
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

	public void updateUser(Scanner input) {
		logger.info("Can only update Password");
		logger.info("Enter ID to Update Password : ");
		String emailID = null ;
		boolean flag = true;
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
		flag = true;
		String password= null;
		while(flag) {
			logger.info("Enter previous Password");
			password = input.next();
			try {
				checker.checkIdentity(emailID, password);
				flag = true;
			} catch (InvalidUserException e) {
				logger.error(e.getMessage());
			}
		}
		logger.info("Enter new Password : ");
		password = input.next();
		authorCredentialDAO.updateUser(emailID,password);
	}

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
	public void addCourse(Scanner input) {
		Course course = new Course();
		logger.info("Enter Course Name");
		course.setName(input.next());
		logger.info("Enter course teaching semester");
		course.setSemester(input.nextInt());
		courseDAO.addCourse(course);
	}
	public void updateCourse(Scanner input) {
		logger.info("Enter Course Code to Reset");
		int courseCode = input.nextInt();
		courseDAO.resetCourse(courseCode);
	}
	
	public Admin getAdmin(String emailID) {
		return adminDAO.listByID(emailID);
	}
	
}
