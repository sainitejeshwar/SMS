package com.flipkart.bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.flipkart.service.RegistrationOperations;

public class Registration {
	private static  Logger logger = Logger.getLogger(Registration.class);
	private static int Counter = 1;
	private int RegistrationNumber;
	private String  RegistrationTime;
	private boolean feespaid;
	
	
	public boolean isFeespaid() {
		return feespaid;
	}
	public void setFeespaid(boolean feespaid) {
		this.feespaid = feespaid;
	}
	public Registration(ArrayList<Course> studentCourses) {
		this.RegistrationNumber = Counter;
		this.setFeespaid(false);
		this.setRegistrationTime();
		logger.info("Registration number : " + this.getRegistrationNumber()
		+"\n Registration Time : "+this.getRegistrationNumber());
		Counter++;
	}
	public int getRegistrationNumber() {
		return RegistrationNumber;
	}
	
	public String getRegistrationTime() {
		return RegistrationTime;
	}
	public void setRegistrationTime() {
		RegistrationTime = DateandTime();
	}
	
	
	private static String DateandTime() {
		LocalDateTime localDateTime = LocalDateTime.now();
		return localDateTime.getYear() + "-" +localDateTime.getMonthValue()+"-"+localDateTime.getDayOfMonth()+" "
				+ localDateTime.getHour()+":"+localDateTime.getMinute()+":"+localDateTime.getSecond();

	}

}
