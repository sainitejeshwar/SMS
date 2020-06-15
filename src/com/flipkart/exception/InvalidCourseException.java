package com.flipkart.exception;

/*
 * CLASS DESCRIPTION
 * Throws exception when the student or professor select a course for which they are not eligible
 */
public class InvalidCourseException extends Exception {
	private int message;
	public InvalidCourseException(int courseCode) {
		this.message = courseCode ;
	}
	public int Message() {
		return this.message;
	}
}
