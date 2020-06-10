package com.flipkart.bean;

import java.util.ArrayList;
import java.util.HashMap;

public class Registration {
	private static int Counter = 0;
	private int RegistrationNumber;
	private HashMap<Student, Integer> Registrations = new HashMap<Student, Integer>();
	
	
	public Registration(ArrayList<Course> studentCourses) {
		this.RegistrationNumber = Counter;
		Counter++;
	}
	protected int getRegistrationNumber() {
		return RegistrationNumber;
	}
	
	public String doRegistration(Student S) {
		
		Registrations.put(S, this.RegistrationNumber);
		return S.getStudentCourses();
	}

}
