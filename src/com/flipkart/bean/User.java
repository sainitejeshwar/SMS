package com.flipkart.bean;

public class User {
	protected String Name;
	protected String emailID;
	protected String Password;
	protected String Type;
	
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getemailID() {
		return emailID;
	}
	public void setID(String iD) {
		emailID = iD;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	public String getUserInfo(User U) {
		return  U.getName() + ", "+U.getemailID();
	}

}
