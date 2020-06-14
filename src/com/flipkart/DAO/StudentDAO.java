package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constants.SQLQueryConstant;

import com.flipkart.utils.DBUtils;

public class StudentDAO{
	Connection conn = null;
	PreparedStatement stmt = null;
	private static Logger logger = Logger.getLogger(StudentDAO.class);


	public void addStudent(User user, Student student) {
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.STUDENT_INSERT);
			stmt.setString(1, user.getemailID());
			stmt.setInt(2, student.getStudentID());
			stmt.setString(3, user.getName());
			stmt.setInt(4, student.getSemester());
			stmt.setString(5, student.getBranch());
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		
		
	}

	public ArrayList<Student> listAll() {
		ArrayList<Student> students = new ArrayList<Student>();
		conn = DBUtils.getConnection();
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.STUDENT_SELECT_ALL);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Student student = new Student();
				student.setStudentID(rs.getInt("StudentID"));
				student.setName(rs.getString("Name"));
				student.setBranch(rs.getString("Branch"));
				student.setSemester(rs.getInt("Semester"));
				student.setStudentCourses(rs.getInt("Course1"));
				student.setStudentCourses(rs.getInt("Course2"));
				student.setStudentCourses(rs.getInt("Course3"));
				student.setStudentCourses(rs.getInt("Course4"));
				student.setRegistrationNumber(rs.getInt("RegNo"));
				students.add(student);
			}
			rs.close();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return students;
	}


	public Student listByID(String emailid) {
		conn = DBUtils.getConnection();
		ResultSet rs = null;
		Student student = new Student();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.STUDENT_SELECT_BY_ID);
			stmt.setString(1, emailid);
			rs = stmt.executeQuery();
			while(rs.next()) {
				student.setStudentID(rs.getInt("StudentID"));
				student.setName(rs.getString("Name"));
				student.setBranch(rs.getString("Branch"));
				student.setSemester(rs.getInt("Semester"));
				student.setStudentCourses(rs.getInt("Course1"));
				student.setStudentCourses(rs.getInt("Course2"));
				student.setStudentCourses(rs.getInt("Course3"));
				student.setStudentCourses(rs.getInt("Course4"));
				student.setRegistrationNumber(rs.getInt("RegNo"));
			}
			rs.close();
			return student;
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return null;
	}
	public void UpdateStudentRegistration(ArrayList<Course> courses , Student student) {
		
		//	TODO  make column in marks table
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.UPDATE_STUDENT_REGISTRATION);
			stmt.setInt(1, courses.get(0).getCourseCode());
			stmt.setInt(2, courses.get(1).getCourseCode());
			stmt.setInt(3, courses.get(2).getCourseCode());
			stmt.setInt(4, courses.get(3).getCourseCode());
			stmt.setInt(5, student.getRegistrationNumber());
			stmt.setInt(6, student.getStudentID());
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		
	}

	public Student getGrade(String emailID) {
		conn = DBUtils.getConnection();
		ResultSet rs = null;
		Student student = new Student();
		student = listByID(emailID);
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.MARKS_SELECT_BY_ID);
			stmt.setInt(1, student.getStudentID());
			rs = stmt.executeQuery();
			while(rs.next()) {
				student.setMarks(0 , rs.getInt("Course1"));
				student.setMarks(1,rs.getInt("Course2"));
				student.setMarks(2,rs.getInt("Course3"));
				student.setMarks(3,rs.getInt("Course4"));
			}
			rs.close();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		} catch (NullPointerException e) {
			logger.debug("No Record Found");
		}
		return student;
		
	}

	public void setGrades(int ind, Student student) {
		conn = DBUtils.getConnection();
		try {
			String sql = null;
			switch (ind) {
			case 0:
				sql = SQLQueryConstant.MARKS_UPDATE_COURSE1;
				break;
			case 1:
				sql = SQLQueryConstant.MARKS_UPDATE_COURSE2;
				break;
			case 2:
				sql = SQLQueryConstant.MARKS_UPDATE_COURSE3;
				break;
			case 3:
				sql = SQLQueryConstant.MARKS_UPDATE_COURSE4;
				break;

			default:
				break;
			}
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, student.getMarks().get(ind));

			stmt.setInt(2, student.getStudentID());
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		
	}




}
