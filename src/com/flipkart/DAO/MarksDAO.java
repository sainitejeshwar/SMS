package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipkart.constants.SQLQueryConstant;
import com.flipkart.utils.DBUtils;

public class MarksDAO {
	private static Connection conn = null;
	private static PreparedStatement stmt = null;
	private static Logger logger = Logger.getLogger(MarksDAO.class);
	
	public void createStudent(int studentID) {
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.MARKS_INSERT);
			stmt.setInt(1, studentID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}	
	}
}
