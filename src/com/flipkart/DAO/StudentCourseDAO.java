package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.constants.SQLQueryConstant;
import com.flipkart.utils.DBUtils;

public class StudentCourseDAO {
	
	private static Connection conn = null;
	private static PreparedStatement stmt = null;
	private static Logger logger = Logger.getLogger(StudentCourseDAO.class);

	public void addCourse(int studentID, int courseID) {
		// TODO Auto-generated method stub
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
