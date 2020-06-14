package com.flipkart.bean;

/*
 * CLASS DESCRIPTION
 * 
 *	Professors who will be teaching student 
 * 
 * ATTRIBUTES
 * 		-	TransactionID 	: Unique for all Professor
 */

public class Professor extends User {

	private  int professorID;
	
	public int  getProfessorID() {
		return professorID;
	}
	public void setProfessorID(int professorID) {
		this.professorID = professorID;
	}

	
}
