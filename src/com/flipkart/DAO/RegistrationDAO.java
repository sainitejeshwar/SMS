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
	private static Connection conn = null;
	private static PreparedStatement stmt = null;
	private static Logger logger = Logger.getLogger(RegistrationDAO.class);
	
	public void addRegistration(Registration newRegistration, Student student) {
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.INSERT_REGISTRATION);
			stmt.setInt(1, newRegistration.getRegistrationNumber());
			stmt.setInt(2, student.getStudentID());
			stmt.setString(3, newRegistration.getRegistrationTime());
			stmt.setBoolean(4, newRegistration.isFeespaid());
			stmt.setInt(5, newRegistration.getAmount());
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
	}
	public Registration getPaymentStatus(int registrationID) {
		conn = DBUtils.getConnection();
		ResultSet rs = null;
		Registration checkRegistration = new Registration();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.PAYMENT_STATUS_R);
			stmt.setInt(1, registrationID);
			rs = stmt.executeQuery();
			while(rs.next()) {
				checkRegistration.setFeespaid(rs.getBoolean("feespaid"));
				return checkRegistration;
			}
			rs.close();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return null;
	}
	public int getRegistrationFees(int registrationID) {
		conn = DBUtils.getConnection();
		ResultSet rs = null;
		int fees = 0;
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.SELECT_REGISTRATION_BY_SID);
			stmt.setInt(1, registrationID);
			rs = stmt.executeQuery();
			while(rs.next()) {
				fees = (rs.getInt("TotalFees"));
			}
			rs.close();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return fees;
		
	}
	public void updatePayment(Payment payment) {
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.UPDATE_FEESPAID);
			stmt.setInt(1, payment.getTransactionID());
			stmt.setInt(2, payment.getRegNO());
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		
	}

}
