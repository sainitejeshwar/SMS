package com.flipkart.bean;

/*
 * CLASS DESCRIPTION
 * 
 * Parent class of Student , Professor , Admin
 * Contains login Details 
 * 
 * 
 * ATTRIBUTES
 * 		- 	Name		 		: Name of the user
 * 		-	emailID			 	: Email Id of user (unique)  
 * 		- 	Password			: password for emailID
 * 		- 	Type				: type of user : student / professor / admin
 * 		- 	Gender				: M/F
 * 									
 */



public class User {
	private String Name;
	private String emailID;
	private String Password;
	private String Type;
	private String Gender;
	
	
	/*
	 * GETTERS AND SETTERS
	 */
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
}
