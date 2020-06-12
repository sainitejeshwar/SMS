package com.flipkart.exception;

import org.apache.log4j.Logger;

public class CourseFilledException extends Exception {
	private String message;
	public CourseFilledException(int courseCode) {
		this.message = courseCode + "is filled";
	}
	public String getMessage() {
		return this.message;
	}

}
