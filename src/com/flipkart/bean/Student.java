package com.flipkart.bean;

import java.util.ArrayList;
import java.util.HashMap;

public class Student extends User {
	
	private ArrayList<Integer> studentCourses = new ArrayList<Integer>();
	private int RegistrationNumber ;
	private int semester;
	private String Branch;  //TODO remove
	private int StudentID;
	private  ArrayList<Integer> marks = new ArrayList<Integer>();
	

	public int getRegistrationNumber() {
		return RegistrationNumber;
	}
	public void setRegistrationNumber(int registrationNumber) {
		RegistrationNumber = registrationNumber;
	}
	public void setMarks(int index ,int grade) {
		marks.add(index, grade);
	}
	public ArrayList<Integer> getMarks(){
		return marks;
	}
	public int getStudentID() {
		return StudentID;
	}
	public void setStudentID(int studentID) {
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
		for(Integer itrCourse : this.studentCourses)
			res = res + itrCourse +",";
		return res;
	}
	public ArrayList<Integer> getStudentCourses() {
		return studentCourses;
	}

	public void setStudentCourses(ArrayList<Integer> newCourses) {
		this.studentCourses.addAll(newCourses);
	}
	public void setStudentCourses(Integer course) {
		this.studentCourses.add(course);
	}
	
	public String dropStudentCourse(int excludeCourse) {
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
