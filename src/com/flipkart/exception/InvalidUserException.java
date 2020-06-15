package com.flipkart.exception;

/*
 * CLASS DESCRIPTION
 * Throw exception when the student and password doesnot match
 */

public class InvalidUserException extends Exception{
	private String message;
	public  InvalidUserException() {
		this.message = "ID or Password is incoorect";
	}
	public String getMessage() {
		return this.message;
	}

}
