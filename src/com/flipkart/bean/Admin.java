package com.flipkart.bean;

public class Admin extends User{
	private String Level;

	protected String getLevel() {
		return Level;
	}

	protected void setLevel(String level) {
		Level = level;
	}
	

}
