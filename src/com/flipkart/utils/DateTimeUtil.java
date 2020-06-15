package com.flipkart.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*
 * CLASS DESCRIPTION
 * 
 * Util function that is used to print Date and Time in different format
 */

public class DateTimeUtil {
	
	//Return current TimeStamp in format in which date is saved in SQL
	public String SQLdatetime() {
		LocalDateTime localDateTime = LocalDateTime.now();
		return localDateTime.getYear() + "-" +localDateTime.getMonthValue()+"-"+localDateTime.getDayOfMonth()+" "
				+ localDateTime.getHour()+":"+localDateTime.getMinute()+":"+localDateTime.getSecond();
	}
	
	//Format in which we want date time to be displayed    
	// Example : 2020-06-15 18:20:02    MONDAY
	
	public String systemDateTime(String dateTime) {
		if(dateTime != null) {
			int year =  Integer.parseInt(dateTime.split("-")[0]);
			int month = Integer.parseInt(dateTime.split("-")[1]);
			int day = Integer.parseInt((dateTime.split("-")[2].split(" ")[0]));
			LocalDate meetingDate = LocalDate.of(year, month, day);
			dateTime = dateTime + "  "+meetingDate.getDayOfWeek();
			return dateTime;
		}
		else {
			return currDateandTime();
		}
	}
	
	//Only date in above format
	public String systemDate(LocalDate localDate) {
		return localDate.getDayOfMonth()+"-"+localDate.getMonth()+"-"+localDate.getYear();
	}
	
	//Current Date Time in above mentioned format
	public String currDateandTime() {
		LocalDateTime localDateTime = LocalDateTime.now();
		return localDateTime.getHour() + ":"+localDateTime.getMinute()+"    "+localDateTime.getDayOfMonth()+"-"+localDateTime.getMonth()+"    "
				+ localDateTime.getDayOfWeek();
	}
	
	//Generates a unique number using current date
	//used of generating unique registartion number and transaction ID
	public int getUniqueNumber(int ID) {
		LocalDateTime localDateTime = LocalDateTime.now();
		return Integer.valueOf(Integer.toString(ID)+Integer.toString(localDateTime.getDayOfMonth())+Integer.toString(localDateTime.getMonthValue()));
	}
}
