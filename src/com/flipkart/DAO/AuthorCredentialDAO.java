package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipkart.bean.User;
import com.flipkart.constants.SQLQueryConstant;
import com.flipkart.exception.InvalidUserException;
import com.flipkart.helper.DBOperations;
import com.flipkart.utils.DBUtils;

public class AuthorCredentialDAO  {
	Connection conn = null;
	PreparedStatement stmt = null;
	private static Logger logger = Logger.getLogger(AuthorCredentialDAO.class);
	
	
	public User checkIdentity(String emailid , String password) throws InvalidUserException{
			conn = DBUtils.getConnection();
			ResultSet rs = null;
			User user = new User();
			try {
				stmt = conn.prepareStatement(SQLQueryConstant.USER_SELECT_BY_ID);
				stmt.setString(1, emailid);
				rs = stmt.executeQuery();
				while(rs.next()) {
					user.setPassword(rs.getString("Password"));
					user.setType(rs.getInt("Type"));
					user.setGender(rs.getString("Gender"));
					user.setEmailID(rs.getString("EmailID"));
				}
				rs.close();
			} catch (SQLException e) {
				logger.debug(e.getMessage());
				return null;
			}
			if(user.getPassword().length() == 0) {
				return null;
			}
			try {
				if(user.getPassword().equals(password)) {
					return user;
				}
				else {
					throw new InvalidUserException();
				}
			}
			catch (NullPointerException e) {
				throw new InvalidUserException();
			}
		
		
	}
	
	public void updateLoginTimeStamp(String emailid , String currDateTime) {
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.USER_UPDATE_TIMESTAMP);
			stmt.setString(1, currDateTime);
			stmt.setString(2, emailid);
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
	}
	
	public String getLastLoginTimeStamp(String emailid) {
		conn = DBUtils.getConnection();
		ResultSet rs = null;
		String res = null;
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.USER_SET_TIMESTAMP);
			stmt.setString(1, emailid);
			rs = stmt.executeQuery();
			while(rs.next()) {
				res = rs.getString("LastLogin");
			}
			
			rs.close();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return res;
	}


	public void addUser(User user2) {
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.USER_INSERT);
			stmt.setString(1, user2.getemailID());
			stmt.setString(2, user2.getPassword());
			stmt.setInt(3, user2.getType());
			stmt.setString(4, user2.getGender());
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		
		
	}

	public void updateUser(String emailID, String password) {
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.USER_UPDATE);
			stmt.setString(1, password);
			stmt.setString(2, emailID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		
	}
	
	/*
	 * insertuser
	 * deleteuser
	 * modify user
	 * list all user -- only accessible by admin
	 */

}
