package com.flipkart.bean;


import java.time.LocalDate;
import java.util.ArrayList;


import org.apache.log4j.Logger;

import com.flipkart.utils.DateTimeUtil;

public class Registration {
	private static  Logger logger = Logger.getLogger(Registration.class);
	private int RegistrationNumber;
	private String  RegistrationTime;
	private boolean feespaid;
	private DateTimeUtil DTUtil = new DateTimeUtil();
	
	public Registration(ArrayList<Course> studentCourses , int studentID) {
		this.RegistrationNumber = DTUtil.getUniqueNumber(studentID);
		this.setFeespaid(false);
		this.setRegistrationTime();
		logger.info("Registration number : " + this.getRegistrationNumber()
		+"\n Registration Time : "+DTUtil.systemDateTime(this.getRegistrationTime()));
	}
	public Registration() {
		
	}
	public boolean isFeespaid() {
		return feespaid;
	}
	public void setFeespaid(boolean feespaid) {
		this.feespaid = feespaid;
	}
	public int getRegistrationNumber() {
		return RegistrationNumber;
	}
	
	public String getRegistrationTime() {
		return RegistrationTime;
	}
	public void setRegistrationTime() {
		RegistrationTime = DTUtil.SQLdatetime();
	}

}
