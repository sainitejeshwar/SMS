package com.flipkart.service;

import org.apache.log4j.Logger;

import com.flipkart.DAO.CourseCatalogDAO;
import com.flipkart.DAO.CourseDAO;
import com.flipkart.DAO.RegistrationDAO;
import com.flipkart.DAO.StudentDAO;
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

	public boolean addCourse() {
		
		return false;
	}

	public boolean dropCourse() {
		// TODO Auto-generated method stub
		return false;
	}
	


}
