package com.flipkart.bean;

import java.util.ArrayList;
import java.util.HashMap;

public class Student extends User {
	private ArrayList<Course> studentCourses = new ArrayList<Course>();
	private HashMap<Integer, Boolean> RegistrationComplete = new HashMap<Integer, Boolean>();
	
	public String getAllCourses() {
		String res="Courses : ";
		for(Course itrCourse : this.studentCourses)
			res = res + itrCourse +",";
		return res;
	}
	public String getStudentCourses() {
		return getAllCourses();
	}

	public void setStudentCourses(ArrayList<Course> newCourses) {
		this.studentCourses.addAll(newCourses);
	}
	public void setStudentCourses(Course newCourse) {
		this.studentCourses.add(newCourse);
	}
	
	public String dropStudentCourse(Course excludeCourse) {
		this.studentCourses.remove(excludeCourse);
		return getAllCourses();
	}
	public void startRegistration() {
		Registration StudentRegistration = new Registration(this.studentCourses);
	}
	@Override
	public String getUserInfo(User U) {
		return "Student : "+U.getID()+"\n"+ U.getName()+","+U.getContactNumber()+"\n"+getAllCourses();
	}
	public HashMap<Integer, Boolean> getRegistrationComplete() {
		return RegistrationComplete;
	}
	public void setRegistrationComplete(HashMap<Integer, Boolean> registrationComplete) {
		RegistrationComplete = registrationComplete;
	}
	
	
	
}
