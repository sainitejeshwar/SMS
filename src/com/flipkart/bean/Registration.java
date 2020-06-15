package com.flipkart.bean;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.flipkart.utils.DateTimeUtil;


/*
 * CLASS DESCRIPTION
 * 
 * To instantiate Registration process for the student
 * 		
 * CONSTRUCTOR
 * 		- Registration(ArrayList<Course> studentCourses , int studentID)
 * 				Takes Courses student with studentID is registering
 * 
 * 
 * ATTRIBUTES
 * 		- 	RegistrationNumber	: Unique for all student registration
 * 		-	feespaid 			: Stores whether fees is paid by the Student or not 
 * 		- 	RegistrationTime	: Time Stamp of the registration
 */



public class Registration {
	private int RegistrationNumber;
	private String  RegistrationTime;
	private int amount;
	private boolean feespaid;
	
	public boolean isFeespaid() {
		return feespaid;
	}
	public void setFeespaid(boolean feespaid) {
		this.feespaid = feespaid;
	}
	private static  Logger logger = Logger.getLogger(Registration.class);
	private DateTimeUtil DTUtil = new DateTimeUtil();
	
	
	/*
	 * CONSTRUCTORS
	 */
	public Registration(ArrayList<Course> studentCourses , int studentID) {
		this.RegistrationNumber = DTUtil.getUniqueNumber(studentID);
		this.setRegistrationTime();
		this.setFeespaid(false);
		logger.info("Registration number : " + this.getRegistrationNumber()
		+"\n Registration Time : "+DTUtil.systemDateTime(this.getRegistrationTime()));
	}
	public Registration() {}
	
	
	/*
	 * GETTERS AND SETTER
	 */
	public int getRegistrationNumber() {
		return RegistrationNumber;
	}
	public String getRegistrationTime() {
		return RegistrationTime;
	}
	public void setRegistrationTime() {
		RegistrationTime = DTUtil.SQLdatetime();
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
