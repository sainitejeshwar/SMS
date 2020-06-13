package com.flipkart.bean;

import java.util.ArrayList;

public class Professor extends User {

	private  int professorID;
	
	public int  getProfessorID() {
		return professorID;
	}

	public void setProfessorID(int professorID) {
		this.professorID = professorID;
	}

	
	@Override
	public String getUserInfo(User U) {
		return "Professor : "+U.getemailID()+"\n"+ U.getName()+"\n Courses : ";
	}
}
