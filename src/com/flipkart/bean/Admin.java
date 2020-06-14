package com.flipkart.bean;


/*CLASS DESCRIPTION
 * 
 * Admin is the user who perform CRUD operation on Professor ,Student and Admin
 * Also perform CRUD operation on Courses
 * 
 * Has 
 * 		- AdminID - Unique For all admin
 * 		- Level - Describes the level of admin
 */


public class Admin extends User{
	private String Level;
	private int AdminID;

	
	/*
	 * GETTERS AND SETTERS
	 */
	public int getAdminID() {
		return AdminID;
	}
	public void setAdminID(int adminID) {
		AdminID = adminID;
	}
	public String getLevel() {
		return Level;
	}
	public void setLevel(String level) {
		Level = level;
	}
}
