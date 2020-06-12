package com.flipkart.exception;

public class NotificationMessage extends Exception{
	private String message;
	public NotificationMessage() {
		this.message = "Registration cannot be done as 4 primary courses cannot be selected";
	}
	public String getMessage() {
		return this.message;
	}
}
