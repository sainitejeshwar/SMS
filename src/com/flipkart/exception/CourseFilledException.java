package com.flipkart.exception;


public class CourseFilledException extends Exception {
	private int message;
	public CourseFilledException(int courseCode) {
		this.message = courseCode;
	}
	public int Message() {
		return this.message;
	}

}
