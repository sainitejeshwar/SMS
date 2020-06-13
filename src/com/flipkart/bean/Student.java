package com.flipkart.bean;

import java.util.ArrayList;
import java.util.HashMap;

public class Student extends User {
	
	private ArrayList<String> studentCourses = new ArrayList<String>();
	private int RegistrationNumber ;
	public int getRegistrationNumber() {
		return RegistrationNumber;
	}
	public void setRegistrationNumber(int registrationNumber) {
		RegistrationNumber = registrationNumber;
	}
	private int semester;
	private String Branch;
	private String StudentID;
	
	public String getStudentID() {
		return StudentID;
	}
	public void setStudentID(String studentID) {
		StudentID = studentID;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public String getBranch() {
		return Branch;
	}
	public void setBranch(String branch) {
		Branch = branch;
	}
	public String getAllCourses() {
		String res="";
		for(String itrCourse : this.studentCourses)
			res = res + itrCourse +",";
		return res;
	}
	public ArrayList<String> getStudentCourses() {
		return studentCourses;
	}

	public void setStudentCourses(ArrayList<String> newCourses) {
		this.studentCourses.addAll(newCourses);
	}
	public void setStudentCourses(String course) {
		this.studentCourses.add(course);
	}
	
	public String dropStudentCourse(String excludeCourse) {
		this.studentCourses.remove(excludeCourse);
		return getAllCourses();
	}
	public void startRegistration() {
		// TODO : dsds
	}
	@Override
	public String getUserInfo(User U) {
		return "Student : "+U.getemailID()+"\n"+ U.getName()+"\n"+getAllCourses();
	}

	
	
	
}
