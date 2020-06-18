package com.flipkart.constants;

/*
 * CLASS DESCRIPTIONS
 * 
 * 	 Contains all the qureies that will be executed on the database
 * 
 */
public class SQLQueryConstant {
	
	//STUDENT TABLE QUERIES
	public static final String STUDENT_SELECT_ALL = "select * from student";
	public static final String STUDENT_SELECT_BY_ID = "select * from student where EmailID=?";
	public static final String STUDENT_UPDATE_REGISTRATION = "update student set Course1 = ? , Course2 = ? , Course3 = ? , Course4 = ? , RegNo = ? where StudentID = ?";
	public static final String STUDENT_INSERT = "insert into student (EmailID , Name , Semester , Branch)  values (?,?,?,?)";

	//USER TABLE QUERIES
	public static final String USER_SELECT_BY_ID = "select * from users where EmailID=?";
	public static final String USER_UPDATE_TIMESTAMP = "update users set LastLogin = ? where EmailID=?";
	public static final String USER_SET_TIMESTAMP = "select LastLogin from users where EmailID=?";
	public static final String USER_UPDATE = "update users set Password = ? where EmailID = ?";
	public static final String USER_INSERT = "insert into users (EmailID , Password,Type,Gender) values (?,?,?,?)";
	public static final String DELETE_USER = "delete from users where EmailID = ?";
	
	//COURSE TABLE QUERIES
	public static final String COURSE_SELECT_ALL = "select * from course";
	public static final String COURSE_UPDATE_COUNT = "update course set NumberOfStudent = ? where CourseCode = ?";
	public static final String COURSE_UPDATE_PROF = "update course set ProfessorInfo = ? where CourseCode = ?";
	public static final String COURSE_INSERT = "insert into course (Name,CatalogID,Fees) values (?,?,?)";
	public static final String COURSE_RESET = "update course set ProfessorInfo = ? , NumberOfStudent = ? where CourseCode = ?";
	public static final String COURSE_SELECT_BY_ID = "select * from course where CourseCode = ?";
	
	//REGISTRATION TABLE QUERIES
	public static final String REGISTRATION_INSERT = "insert into registrations (RegNo , StudentID , TimeStamp , feespaid , TotalFees) values(?,?,?,?,?)";
	public static final String REGISTRATION_PAYMENT_STATUS = "select * from registrations where RegNo = ?";
	public static final String REGISTRATION_UPDATE_FEESPAID = "update registrations set feespaid = true , TransacID = ? where RegNo = ?";
	public static final String REGISTRATION_SELECT_BY_SID = "select * from registrations where RegNo = ?";
	
	//PROFESSOR TABLE QUERIES
	public static final String PROFESSOR_SELECT_BY_ID = "select * from professor where EmailID=?";
	public static final String PROFESSOR_SELECT_ALL = "select * from professor";
	public static final String PROFESSOR_INSERT = "insert into professor (EmailID , Name) values (?,?)";

	//ADMIN TABLE QUERIES
	public static final String ADMIN_SELECT_BY_ID = "select * from admin where EmailID=?";
	public static final String ADMIN_SELECT_ALL = "select * from admin";
	public static final String ADMIN_INSERT = "insert into admin (EmailID , Name , Level) values (?,?,?)";
		
	//REPORTCARD TABLE QUERIES
	public static final String REPORTCARD_INSERT = "insert into reportcard values (?,?,?)";
	public static final String REPORTCARD_BY_ID = "select * from reportcard where StudentID = ?";

	//PAYMENT TABLE QUERIES
	public static final String PAYMENT_INSERT = "insert into payment values (?,?,?,?,?)";
	public static final String PAYMENT_STATUS = "select * from payment where RegNo = ?";
	
	//STUDENT COURSE TABLE QUERIES
	public static final String STUDENT_COURSE_INSERT = "insert into studentcourses values (?,?)";
	public static final String STUDENT_COURSE_DELETE = "delete from studentcourses where StudentID = ? and CourseCode = ?";
	public static final String STUDENT_COURSE_SELECT = "select * from studentcourses where StudentID = ?";
}
