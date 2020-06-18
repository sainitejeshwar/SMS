package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipkart.bean.Payment;
import com.flipkart.constants.SQLQueryConstant;
import com.flipkart.utils.DBUtils;
/*
 * CLASS DECRIPTION
 * 
 * For saving payment details of registration fees payment
 */
public class PaymentDAO {
	private static Connection conn = null;
	private static PreparedStatement stmt = null;
	private static Logger logger = Logger.getLogger(PaymentDAO.class);
	
	//For inserting a new payment in the database
	public void updatePayment(Payment payment) {
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.PAYMENT_INSERT);
			stmt.setInt(1, payment.getTransactionID());
			stmt.setInt(2, payment.getRegNO());
			stmt.setString(3, payment.getTimeStamp());
			stmt.setInt(4,payment.getAmount());
			stmt.setString(5, payment.getStatus());
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
	}
	
	//For fetching the details about a particular payment
	public Payment getPaymentStatus(int registrationID) {
		conn = DBUtils.getConnection();
		ResultSet rs = null;
		Payment payment = new Payment();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.PAYMENT_STATUS);
			stmt.setInt(1, registrationID);
			rs = stmt.executeQuery();
			while(rs.next()) {
				payment.setStatus(rs.getString("Status"));
				payment.setAmount(rs.getInt("Amount"));
				payment.setTimeStamp(rs.getString("TimeStampPayment"));
			}
			
			rs.close();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return payment;
	}
}
