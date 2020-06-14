package com.flipkart.bean;

import java.time.LocalDateTime;

import com.flipkart.utils.DateTimeUtil;

public class Payment {
	private int TransactionID;
	private boolean Status;
	private String timeStamp;
	private int RegNO ;
	public int getRegNO() {
		return RegNO;
	}
	public void setRegNO(int regNO) {
		RegNO = regNO;
	}
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
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}


}
