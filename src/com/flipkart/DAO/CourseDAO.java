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

public class CourseDAO{
	private static Connection conn = null;
	private static PreparedStatement stmt = null;
	private static Logger logger = Logger.getLogger(CourseDAO.class);
	
	public void addCourse(Course course2) {
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.COURSE_INSERT);
			stmt.setString(1, course2.getName());
			stmt.setInt(2, course2.getCatalogID());
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
	}

	public void resetCourse(int courseCode) {
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.COURSE_RESET);
			stmt.setInt(1, -1);
			stmt.setInt(2, 0);
			stmt.setInt(3, courseCode);
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
	}
	public ArrayList<Course> listAll() {
		ArrayList<Course> allCourses = new ArrayList<Course>();
		conn = DBUtils.getConnection();
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.COURSE_SELECT_ALL);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Course course = new Course();
				course.setCourseCode(rs.getInt("CourseCode"));
				course.setName(rs.getString("Name"));
				course.setCatalogID(rs.getInt("CatalogID"));
				course.setProf(rs.getInt("ProfessorInfo"));
				course.setNumberofStudents(rs.getInt("NumberOfStudent"));
				course.setFees(rs.getInt("Fees"));
				allCourses.add(course);
			}
			rs.close();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return allCourses;
	}
	public void updateStudents(ArrayList<Course> final_courses) {
		conn = DBUtils.getConnection();
		for(Course course : final_courses) {
			try {
				stmt = conn.prepareStatement(SQLQueryConstant.COURSE_UPDATE_COUNT);
				stmt.setInt(2, course.getCourseCode());
				stmt.setInt(1, (course.getNumberofStudents()+1));
				stmt.executeUpdate();
			} catch (SQLException e) {
				logger.debug(e.getMessage());
			}
		}
	}
	public void addCourseProf(int courseCode1, int profID) {
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.COURSE_UPDATE_PROF);
			stmt.setInt(1, profID);
			stmt.setInt(2, courseCode1);
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
	}	
}
