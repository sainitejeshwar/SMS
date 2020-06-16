package com.flipkart.bean;

/*
 * CLASS DESCRIPTION
 * 
 * Course refers to Subject that a student will learn and Professor will teach
 * 
 * ATTRIBUTES
 * 		-	CourseCode 		: Unique for all courses
 * 		-	Name 			: Name of the Course
 * 		- 	Semester		: Semester in which this Course will be taught
 * 		- 	Prof			: ProfessorID of Professor that will teach the course 
 * 								Default = -1
 * 		- 	NumberofStudent	: Number of Student completed registration for the course
 */



public class Course {
	private int CourseCode;
	private String Name;
	private int CatalogID;
	private int Prof;
	private int NumberofStudents;
	private int Fees;
	
	
	/*
	 * GETTERS AND SETTERS
	 */
	
	public int getFees() {
		return Fees;
	}
	public void setFees(int fees) {
		Fees = fees;
	}
	public int getNumberofStudents() {
		return NumberofStudents;
	}
	public void setNumberofStudents(int numberofStudents) {
		NumberofStudents = numberofStudents;
	}
	public int getCourseCode() {
		return CourseCode;
	}
	public void setCourseCode(int courseCode) {
		CourseCode = courseCode;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getCatalogID() {
		return CatalogID;
	}
	public void setCatalogID(int catalogID) {
		CatalogID = catalogID;
	}
	public int getProf() {
		return Prof;
	}
	public void setProf(int prof) {
		Prof = prof;
	}
	
}
