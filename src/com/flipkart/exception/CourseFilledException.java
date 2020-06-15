package com.flipkart.exception;
/*
 * CLASS DESCRIPTION
 * Throws exception when the course student selected for registration already have 10 registered students
 */

public class CourseFilledException extends Exception {
	private int message;
	public CourseFilledException(int courseCode) {
		this.message = courseCode;
	}
	public int Message() {
		return this.message;
	}
}
