package com.flipkart.bean;

public class User {
	private String Name;
	private Integer ID;
	private String Password;
	private String ContactNumber;
	
	protected String getName() {
		return Name;
	}
	protected void setName(String name) {
		Name = name;
	}
	protected Integer getID() {
		return ID;
	}
	protected void setID(Integer iD) {
		ID = iD;
	}
	protected String getPassword() {
		return Password;
	}
	protected void setPassword(String password) {
		Password = password;
	}
	protected String getContactNumber() {
		return ContactNumber;
	}
	protected void setContactNumber(String contactNumber) {
		ContactNumber = contactNumber;
	}
	
	protected String getUserInfo(User U) {
		return  U.getName() + ", "+U.getContactNumber();
	}

}
