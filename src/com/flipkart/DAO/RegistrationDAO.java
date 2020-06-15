package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipkart.bean.Payment;
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
			stmt.setInt(2, student.getStudentID());
			stmt.setString(3, newRegistration.getRegistrationTime());
			stmt.setBoolean(4, newRegistration.isFeespaid());
			logger.info(stmt.executeUpdate());
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
	}
	
	public void updateFeesStatus(Payment payment) {
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.UPDATE_REGISTRATION);
			stmt.setInt(1, payment.getTransactionID());
			stmt.setString(2, payment.getTimeStamp());
			stmt.setInt(3, payment.getRegNO());
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
	}

	public Payment getPaymentStatus(int registrationID) {
		conn = DBUtils.getConnection();
		ResultSet rs = null;
		Payment payment = new Payment();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.PAYMENT_STATUS);
			stmt.setInt(1, registrationID);
			rs = stmt.executeQuery();
			while(rs.next()) {
				payment.setStatus(rs.getBoolean("feespaid"));
				payment.setTimeStamp(rs.getString("TimeStampPayment"));
			}
			
			rs.close();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return payment;
		
	}

}
