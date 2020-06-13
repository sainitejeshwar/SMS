package com.flipkart.bean;

public class User {
	public String Name;
	public String emailID;
	public String Password;
	public String Type;
	public String Gender;
	
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
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
