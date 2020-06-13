package com.flipkart.exception;

public class InvalidCourseException extends Exception {
	private String message;
	public InvalidCourseException(int courseCode) {
		this.message = courseCode + "is not valid for you";
	}
	public String getMessage() {
		return this.message;
	}

}
