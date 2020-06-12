package com.flipkart.bean;

import java.util.ArrayList;

public class Professor extends User {
	private ArrayList<Course> profCourses = new ArrayList<Course>();
	private  int professorID;
	
	public int getProfessorID() {
		return professorID;
	}

	public void setProfessorID(int professorID) {
		this.professorID = professorID;
	}

	public ArrayList<Course> getProfCourses() {
		return profCourses;
	}
	
	public void setProfCourses(Course newCourse) {
		this.profCourses.add(newCourse);
	}
	public void setProfCourses(ArrayList<Course> newCourses) {
		this.profCourses.addAll(newCourses);
;	}
	
	public String getAllCourses() {
		String res="";
		for(Course itrCourse : this.profCourses)
			res = res + itrCourse +",";
		return res;
	}
	@Override
	public String getUserInfo(User U) {
		return "Professor : "+U.getemailID()+"\n"+ U.getName()+"\n Courses : ";
	}
}
