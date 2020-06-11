package com.flipkart.bean;

public class User {
	private String Name;
	private Integer ID;
	private String Password;
	private String ContactNumber;
	private String Type;
	
	protected String getType() {
		return Type;
	}
	protected void setType(String type) {
		Type = type;
	}
	public String getName() {
		return Name;
	}
	protected void setName(String name) {
		Name = name;
	}
	public Integer getID() {
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
	public String getContactNumber() {
		return ContactNumber;
	}
	protected void setContactNumber(String contactNumber) {
		ContactNumber = contactNumber;
	}
	
	protected String getUserInfo(User U) {
		return  U.getName() + ", "+U.getContactNumber();
	}

}
