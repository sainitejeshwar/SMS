package com.flipkart.bean;

import java.util.ArrayList;

public class Professor extends User {
	private ArrayList<Course> profCourses = new ArrayList<Course>();
	
	public ArrayList<Course> getProfCourses() {
		return profCourses;
	}
	
	public void setProfCourses(Course newCourse) {
		this.profCourses.add(newCourse);
	}
	public void setProfCourses(ArrayList<Course> newCourses) {
		this.profCourses.addAll(newCourses);
;	}
	
	private String getAllCourses() {
		String res="";
		for(Course itrCourse : this.profCourses)
			res = res + itrCourse +",";
		return res;
	}
	@Override
	protected String getUserInfo(User U) {
		return "Professor : "+U.getID()+"\n"+ U.getName()+","+U.getContactNumber()+"\n Courses : ";
	}
}
