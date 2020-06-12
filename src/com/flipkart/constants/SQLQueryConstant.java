package com.flipkart.constants;

public class SQLQueryConstant {
	public static final String DELETE_TABLE_NAME = "delete from ? where ID =?";
	public static final String LIST_TABLE_NAME = "select * from ? where ID = ?";
	public static final String LIST_ALL_TABLE_NAME = "select * from ?";
	
	
	public static final String STUDENT_SELECT_BY_ID = "select * from student where EmailID=?";
	
	public static final String USER_SELECT_BY_ID = "select * from users where EmailID=?";
	public static final String USER_UPDATE_TIMESTAMP = "update users set LastLogin = ? where EmailID=?";
	public static final String USER_SET_TIMESTAMP = "select LastLogin from users where EmailID=?";
	
	public static final String CATALOG_SELECT_ALL = "select * from catalog";
	
	public static final String COURSE_SELECT_BY_SEM = "select * from course where semester = ?";
	public static final String COURSE_SELECT_ALL = "select * from course";
	

	
	
	
}
