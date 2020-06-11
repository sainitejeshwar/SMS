package com.flipkart.exception;

import org.apache.log4j.Logger;

public class CourseFilledException extends Exception {
	private static Logger logger = Logger.getLogger(CourseFilledException.class);
	private String message;
	public CourseFilledException(String course) {
		this.message = course + "is filled";
	}
	public String getMessage() {
		return this.message;
	}

}
