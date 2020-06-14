package com.flipkart.service;


import org.apache.log4j.Logger;

import com.flipkart.bean.Payment;
import com.flipkart.bean.Registration;
import com.flipkart.bean.Student;
import com.flipkart.helper.operationHelper;

public class StudentOperations implements operationHelper{;
	private static  Logger logger = Logger.getLogger(StudentOperations.class);
	Student student = new Student();
	
	public String payFees(int registrationID) {
		//TODO Check Payment Status
		logger.info("Paying Fees for Registration ID : "+registrationID);
		Payment payment = new Payment();
		payment.setTimeStamp();
		payment.setStatus(true);
		payment.setTransactionID(registrationID);
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
	public String viewReportCard(String emailID) {
		student = studentDAO.getGrade(emailID);
		int ind = 0;
		for(Integer itr : student.getMarks()) {
			logger.info(getCourseName(student.getStudentCourses().get(ind)) +"\t"+itr);
			ind++;
		}
		return null;
	}
	
	
	public String showDetails(Student student) {
		return "ID :"+student.getStudentID()+"\nName:"+student.getName()+"\nBranch:"+student.getBranch()
		+"\nSemester:"+student.getSemester();
	}
}

