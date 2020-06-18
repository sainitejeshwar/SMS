package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.flipkart.constants.SQLQueryConstant;
import com.flipkart.utils.DBUtils;

public class MarksDAO {
	private static Connection conn = null;
	private static PreparedStatement stmt = null;
	private static Logger logger = Logger.getLogger(MarksDAO.class);
	
	
	//uploading grades for a particular course
	public void uploadGrades(int studentID , int courseCode , int grades) {
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.REPORTCARD_INSERT);
			stmt.setInt(1, studentID);
			stmt.setInt(2, courseCode);
			stmt.setInt(3, grades);
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}	
	}
	
	//Fetching grades of all the student
	public HashMap<Integer, Integer> fetchGrades(int studentID) {
		conn = DBUtils.getConnection();
		ResultSet rs = null;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.REPORTCARD_BY_ID);
			stmt.setInt(1, studentID );
			rs = stmt.executeQuery();
			while(rs.next()) {
				map.put(rs.getInt("CourseCode"), rs.getInt("Grade"));
			}
			rs.close();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		} catch (NullPointerException e) {
			logger.debug("No Record Found");
		}
		return map;
	}
	
	
}
