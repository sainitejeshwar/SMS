package com.flipkart.service;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.flipkart.DAO.CourseCatalogDAO;
import com.flipkart.DAO.CourseDAO;
import com.flipkart.DAO.RegistrationDAO;
import com.flipkart.DAO.StudentDAO;
import com.flipkart.bean.Course;
import com.flipkart.bean.Payment;
import com.flipkart.bean.Registration;
import com.flipkart.bean.Student;
import com.flipkart.helper.operationHelper;

public class StudentOperations implements operationHelper{
	private static  Logger logger = Logger.getLogger(StudentOperations.class);
	private CourseDAO courseDAO = new CourseDAO();
	private StudentDAO studentDAO = new StudentDAO();
	private RegistrationDAO registrationDAO = new RegistrationDAO();
	Student student = new Student();
	
	
	public String payFees(int registrationID) {
		//TODO Check Payment Status
		logger.info("Paying Fees for Registration ID : "+registrationID);
		Payment payment = new Payment();
		payment.setTimeStamp();
		payment.setStatus(true);
		payment.setTransactionID();
		if(payment.isStatus()) {
			registrationDAO.updateFeesStatus(registrationID);
		}
		return "Fees paid on : "+payment.getTimeStamp()+" with Transacction ID : "+ payment.getTransactionID();
	}
	
	
	public Student getStudent(String emailid) {
		return studentDAO.listByID(emailid);
	}

	public void showCourse() {
		viewCourseCatalog();
	}
	
	public String showDetails(Student student) {
		return "ID :"+student.getStudentID()+"\nName:"+student.getName()+"\nBranch:"+student.getBranch()
		+"\nSemester:"+student.getSemester();
	}
}

