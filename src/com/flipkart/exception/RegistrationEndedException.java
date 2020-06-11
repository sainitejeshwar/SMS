package com.flipkart.exception;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistrationEndedException {
	private String message;

	public  RegistrationEndedException(Date date) {
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		this.message = "Registration Process Ended on "+sdformat.format(date);
	}
	public String getMessage() {
		return this.message;
	}
}
