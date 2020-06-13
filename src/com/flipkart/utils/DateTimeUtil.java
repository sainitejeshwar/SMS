package com.flipkart.utils;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;



public class DateTimeUtil {
	
	public String SQLdatetime() {
		LocalDateTime localDateTime = LocalDateTime.now();
		return localDateTime.getYear() + "-" +localDateTime.getMonthValue()+"-"+localDateTime.getDayOfMonth()+" "
				+ localDateTime.getHour()+":"+localDateTime.getMinute()+":"+localDateTime.getSecond();
	}
	public String systemDateTime(String dateTime) {
		
		int year =  Integer.parseInt(dateTime.split("-")[0]);
		int month = Integer.parseInt(dateTime.split("-")[1]);
		int day = Integer.parseInt((dateTime.split("-")[2].split(" ")[0]));
		LocalDate meetingDate = LocalDate.of(year, month, day);
		dateTime = dateTime + "    "+meetingDate.getDayOfWeek();
		return dateTime;
		
	}
	public String currDateandTime() {
		LocalDateTime localDateTime = LocalDateTime.now();
		return localDateTime.getHour() + ":"+localDateTime.getMinute()+"    "+localDateTime.getDayOfMonth()+"-"+localDateTime.getMonth()+"    "
				+ localDateTime.getDayOfWeek();
	}
}

//sql datetime
//datetime with day