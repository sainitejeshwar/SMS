package com.flipkart.exception;

public class InvalidUserException extends Exception{
	private String message;
	public  InvalidUserException() {
		this.message = "ID or Password is incoorect";
	}
	public String getMessage() {
		return this.message;
	}

}
