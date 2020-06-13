package com.flipkart.bean;

public class Admin extends User{
	private String Level;
	private int AdminID;

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
