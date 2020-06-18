package com.flipkart.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;


/*
 * CLASS DESCRIPTION
 * 
 * Student Class : stores Student details 
 * 
 * 
 * ATTRIBUTES
 * 		- 	studentCourses 		: Store CourseCode of student Courses for which student has registered
 * 		-	RegistrationNumber 	: Registration Number  
 * 		- 	semester			: Semester in which student is studying
 * 		- 	Branch				: Branch in which student is enrolled
 * 		- 	StudentID			: Primary Key
 * 		- 	marks				: Arraylist of marks of student , courseCode can be taken from student course corresponding to same index
 * 									
 */


public class Student extends User {
	
	private ArrayList<Integer> studentCourses = new ArrayList<Integer>();
	private int RegistrationNumber ;
	private int semester;
	private String Branch;  
	private int StudentID;
	private  ArrayList<Integer> marks = new ArrayList<Integer>(Arrays.asList(new Integer[4]));
	
	public Student(){
		Collections.fill(marks, 0);
	}

	public int getRegistrationNumber() {
		return RegistrationNumber;
	}
	public void setRegistrationNumber(int registrationNumber) {
		RegistrationNumber = registrationNumber;
	}

	public void setMarks(int index , int grade) {
		marks.set(index, grade);
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
}
