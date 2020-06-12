package com.flipkart.bean;

import java.time.LocalDateTime;

public class Payment {
	private int TransactionID;
	private boolean Status;
	private LocalDateTime timeStamp;
	private static int counter = 0;
	public int getTransactionID() {
		return TransactionID;
	}
	public void setTransactionID() {
		
		TransactionID = counter;
		counter++;
	}
	public boolean isStatus() {
		return Status;
	}
	public void setStatus(boolean status) {
		Status = status;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp() {
		this.timeStamp = LocalDateTime.now();
	}

}
