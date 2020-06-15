package com.flipkart.constants;

public class SQLQueryConstant {
	public static final String STUDENT_SELECT_ALL = "select * from student";
	public static final String STUDENT_SELECT_BY_ID = "select * from student where EmailID=?";
	public static final String UPDATE_STUDENT_REGISTRATION = "update student set Course1 = ? , Course2 = ? , Course3 = ? , Course4 = ? , RegNo = ? where StudentID = ?";
	public static final String STUDENT_INSERT = "insert into student (EmailID , Name , Semester , Branch)  values (?,?,?,?)";

	public static final String USER_SELECT_BY_ID = "select * from users where EmailID=?";
	public static final String USER_UPDATE_TIMESTAMP = "update users set LastLogin = ? where EmailID=?";
	public static final String USER_SET_TIMESTAMP = "select LastLogin from users where EmailID=?";
	public static final String USER_UPDATE = "update users set Password = ? where EmailID = ?";
	public static final String USER_INSERT = "insert into users (EmailID , Password,Type,Gender) values (?,?,?,?)";

	public static final String COURSE_SELECT_BY_SEM = "select * from course where semester = ?";
	public static final String COURSE_SELECT_ALL = "select * from course";
	public static final String UPDATE_COURSE_COUNT = "update course set NumberOfStudent = ? where CourseCode = ?";
	public static final String UPDATE_COURSE_PROF = "update course set ProfessorInfo = ? where CourseCode = ?";
	public static final String COURSE_INSERT = "insert into course (Name,Sem) values (?,?)";
	public static final String COURSE_RESET = "update course set ProfessorInfo = ? , NumberOfStudent = ? where CourseCode = ?";
	
	public static final String INSERT_REGISTRATION = "insert into registrations (RegNo , StudentID , TimeStamp , feespaid , TotalFees) values(?,?,?,?,?)";
	public static final String UPDATE_REGISTRATION = "update registrations set feespaid = true , TransacID = ? , TimeStampPayment = ? where RegNo = ?";
	public static final String PAYMENT_STATUS_R = "select * from registrations where RegNo = ?";
	public static final String UPDATE_FEESPAID = "update registrations set feespaid = true , TransacID = ? where RegNo = ?";
	public static final String SELECT_REGISTRATION_BY_SID = "select * from registrations where RegNo = ?";
	
	public static final String PROFESSOR_SELECT_BY_ID = "select * from professor where EmailID=?";
	public static final String PROFESSOR_SELECT_ALL = "select * from professor";
	public static final String PROF_INSERT = "insert into professor (EmailID , Name) values (?,?)";

	public static final String ADMIN_SELECT_BY_ID = "select * from admin where EmailID=?";
	public static final String ADMIN_INSERT = "insert into admin (EmailID , Name , Level) values (?,?,?)";
	public static final String ADMIN_SELECT_ALL = "select * from admin";
	
	public static final String MARKS_SELECT_BY_ID = "select * from grades where StudentID = ?";
	public static final String MARKS_UPDATE = "update grades set Course1 = ? , Course2 = ? , Course3 = ? , Course4 = ?  where StudentID = ?";
	public static final String MARKS_UPDATE_COURSE1 = "update grades set Course1 = ?  where StudentID = ?";
	public static final String MARKS_UPDATE_COURSE2 = "update grades set Course2 = ?  where StudentID = ?";
	public static final String MARKS_UPDATE_COURSE3 = "update grades set Course3 = ?  where StudentID = ?";
	public static final String MARKS_UPDATE_COURSE4 = "update grades set Course4 = ?  where StudentID = ?";
	public static final String MARKS_INSERT = "insert into grades (StudentID) values (?)";
	
	public static final String INSERT_PAYMENT = "insert into payment values (?,?,?,?,?)";
	public static final String PAYMENT_STATUS = "select * from payment where RegNo = ?";

}
