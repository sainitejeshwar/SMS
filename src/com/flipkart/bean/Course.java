package com.flipkart.bean;

public class Course {
	private int CourseCode;
	private String Name;
	private int Semester;
	private int Prof;
	private int NumberofStudents;
	
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
	public int getSemester() {
		return Semester;
	}
	public void setSemester(int semester) {
		Semester = semester;
	}
	public int getProf() {
		return Prof;
	}
	public void setProf(int prof) {
		Prof = prof;
	}
	
}
