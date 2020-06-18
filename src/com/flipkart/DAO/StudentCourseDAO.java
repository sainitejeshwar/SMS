package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.flipkart.constants.SQLQueryConstant;
import com.flipkart.utils.DBUtils;

/*
 * CLASS DESCRIPTION
 * 
 * For managing the temporary courses of the student added or dropped before registering
 * 
 * Includes
 * 		- add course
 * 		- drop course
 * 		- get course 
 */
public class StudentCourseDAO {
	
	//CONNECTION OBJECT(S)
	private static Connection conn = null;
	private static PreparedStatement stmt = null;
	//LOGGER OBJECT(S)
	private static Logger logger = Logger.getLogger(StudentCourseDAO.class);

	//Add course of the student
	public void addCourse(int studentID, int courseID) {
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.STUDENT_COURSE_INSERT);
			stmt.setInt(1, studentID);
			stmt.setInt(2, courseID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
	}
	
	//drop student course
	public void dropCourse(int studentID, int courseCode) {
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.STUDENT_COURSE_DELETE);
			stmt.setInt(1, studentID);
			stmt.setInt(2, courseCode);
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
	}
	
	//Fetch all the courses of the student
	public ArrayList<Integer> getCourse(int studentID) {
		ArrayList<Integer> tempCourses = new ArrayList<Integer>();
		conn = DBUtils.getConnection();
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.STUDENT_COURSE_SELECT);
			stmt.setInt(1, studentID);
			rs = stmt.executeQuery();
			while(rs.next()) {
				tempCourses.add(rs.getInt("CourseCode"));
			}
			rs.close();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return tempCourses;
	}
}
