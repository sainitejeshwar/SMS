package com.flipkart.service;


import org.apache.log4j.Logger;

import com.flipkart.bean.Payment;
import com.flipkart.bean.Student;
import com.flipkart.helper.operationHelper;
import com.flipkart.utils.DateTimeUtil;

public class StudentOperations implements operationHelper{;
	private static  Logger logger = Logger.getLogger(StudentOperations.class);
	Student student = new Student();
	DateTimeUtil dTimeUtil = new DateTimeUtil();
	
	public String payFees(int registrationID) {
		try {
			if(registrationDAO.getPaymentStatus(registrationID).isFeespaid()) {
				return "Fees Already Paid on :" +dTimeUtil.systemDateTime(paymentDAO.getPaymentStatus(registrationID).getTimeStamp());
			}
		}
		catch (Exception e) {
			return ("Do Registration First");
		}
		int fees = registrationDAO.getRegistrationFees(registrationID);
		logger.info("Amount to be Paid : " + fees);
		logger.info("Paying Fees for Registration ID : "+registrationID);
		Payment payment = new Payment();
		payment.setTimeStamp(dTimeUtil.SQLdatetime());
		payment.setStatus("Completed");   //Assuming True from payment Gateway
		payment.setTransactionID(registrationID);
		payment.setRegNO(registrationID);
		payment.setAmount(fees);
		paymentDAO.updatePayment(payment);
		registrationDAO.updatePayment(payment);
		return "Fees paid on : "+dTimeUtil.systemDateTime(payment.getTimeStamp())+" with Transacction ID : "+ payment.getTransactionID();
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

