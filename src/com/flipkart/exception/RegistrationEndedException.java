package com.flipkart.exception;

import java.time.LocalDate;

public class RegistrationEndedException extends Exception {
	private String message;

	public  RegistrationEndedException(LocalDate regDate) {
		this.message = "Registration ended at :" + regDate; 
	}
	public String getMessage() {
		return this.message;
	}
}
