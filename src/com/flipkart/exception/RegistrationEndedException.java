package com.flipkart.exception;

import java.time.LocalDate;
/*
 * CLASS DESCRIPTION
 * Throws excceptions when the registation date is ended and student try to start registration
 */
public class RegistrationEndedException extends Exception {
	private String message;

	public  RegistrationEndedException(LocalDate regDate) {
		this.message = "Registration ended at :" + regDate; 
	}
	public String getMessage() {
		return this.message;
	}
}
