package com.flipkart.exception;

/*
 * CLASS DESCRIPTION
 * 
 * Throws Exception when the registration cannoted be completed as 4 courses inlcuding alternate courses 
 * cannot be finalized for registration
 * 
 */

public class NotificationMessage extends Exception{
	private String message;
	public NotificationMessage() {
		this.message = "Registration cannot be done as 4 primary courses cannot be selected";
	}
	public String getMessage() {
		return this.message;
	}
}
