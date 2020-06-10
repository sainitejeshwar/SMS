package com.flipkart.bean;

import java.util.ArrayList;

public class Student extends User {
	private ArrayList<Course> studentCourses = new ArrayList<Course>();
	
	private String getAllCourses() {
		String res="Courses : ";
		for(Course itrCourse : this.studentCourses)
			res = res + itrCourse +",";
		return res;
	}
	protected String getStudentCourses() {
		return getAllCourses();
	}

	protected void setStudentCourses(ArrayList<Course> newCourses) {
		this.studentCourses.addAll(newCourses);
	}
	protected void setStudentCourses(Course newCourse) {
		this.studentCourses.add(newCourse);
	}
	
	protected String dropStudentCourse(Course excludeCourse) {
		this.studentCourses.remove(excludeCourse);
		return getAllCourses();
	}
	protected void startRegistration() {
		Registration StudentRegistration = new Registration(this.studentCourses);
	}
	@Override
	protected String getUserInfo(User U) {
		return "Student : "+U.getID()+"\n"+ U.getName()+","+U.getContactNumber()+"\n"+getAllCourses();
	}
	
	
}
