package com.flipkart.exception;

public class InvalidCourseException extends Exception {
	private int message;
	public InvalidCourseException(int courseCode) {
		this.message = courseCode ;
	}
	public int Message() {
		return this.message;
	}
}
