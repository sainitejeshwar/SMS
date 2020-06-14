package com.flipkart.bean;

import java.time.LocalDateTime;

import com.flipkart.utils.DateTimeUtil;

public class Payment {
	private int TransactionID;
	private boolean Status;
	private String timeStamp;
	private static int counter = 0;
	DateTimeUtil DTUtil = new DateTimeUtil();
	
	public int getTransactionID() {
		return TransactionID;
	}
	public void setTransactionID(int ID) {
		
		TransactionID = DTUtil.getUniqueNumber(ID);
	}
	public boolean isStatus() {
		return Status;
	}
	public void setStatus(boolean status) {
		Status = status;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp() {
		this.timeStamp = DTUtil.systemDateTime(null);
	}

}
