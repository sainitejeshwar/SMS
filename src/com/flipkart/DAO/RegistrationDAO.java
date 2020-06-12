package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipkart.bean.Registration;
import com.flipkart.bean.Student;
import com.flipkart.constants.SQLQueryConstant;
import com.flipkart.utils.DBUtils;

public class RegistrationDAO {
	Connection conn = null;
	PreparedStatement stmt = null;
	private static Logger logger = Logger.getLogger(RegistrationDAO.class);
	
	public void addRegistration(Registration newRegistration, Student student) {
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.INSERT_REGISTRATION);
			stmt.setInt(1, newRegistration.getRegistrationNumber());
			stmt.setString(2, student.getStudentID());
			stmt.setString(3, newRegistration.getRegistrationTime());
			stmt.setBoolean(4, newRegistration.isFeespaid());
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
	}
	
	public void updateFeesStatus(int registrationID) {
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.UPDATE_REGISTRATION);
			stmt.setInt(1, registrationID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
	}

}
