package com.flipkart.service;


import org.apache.log4j.Logger;

import com.flipkart.bean.Payment;
import com.flipkart.bean.Student;
import com.flipkart.helper.operationHelper;
import com.flipkart.utils.DateTimeUtil;

public class StudentOperations implements operationHelper{;
	private static  Logger logger = Logger.getLogger(StudentOperations.class);
	private static Student student = new Student();
	private static DateTimeUtil dTimeUtil = new DateTimeUtil();
	
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

	public void showCourse(int CatalogID) {
		returnCourseCatalog()
		.stream()
		.filter(course -> course.getCatalogID() == CatalogID)
		.forEach(course -> logger.info(course.getCourseCode()+"\t"+course.getName()));
		;
	}
	public String viewReportCard(String emailID) {
		student = studentDAO.getGrade(emailID);
		String grades = "";
		int ind = 0;
		for(Integer itr : student.getMarks()) {
			grades = grades + (getCourseName(student.getStudentCourses().get(ind)) +"\t"+itr+"\n");
			ind++;
		}
		if(grades.equals("")) {
			grades = "Not Available";
		}
		return grades;
	}
	
	public String showDetails(Student student) {
		return "ID :"+student.getStudentID()+"\nName:"+student.getName()+"\nBranch:"+student.getBranch()
		+"\nSemester:"+student.getSemester();
	}
}

