package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipkart.bean.User;
import com.flipkart.constants.SQLQueryConstant;
import com.flipkart.exception.InvalidUserException;
import com.flipkart.utils.DBUtils;
/*
 * CLASS DESCRIPTION
 * 
 * Performs SQL query to fetch and store data from User Table
 * 
 */
public class AuthorCredentialDAO  {
	
	//DATABASE CONNECTION OBJECTS
	private static Connection conn = null;
	private static PreparedStatement stmt = null;
	
	//LOGGER OBJECT(S)
	private static Logger logger = Logger.getLogger(AuthorCredentialDAO.class);
	
	//Add user to table
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
	
	//updates user Password
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
	
	
	//Check whether emailID and Password is in Database or not 
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
		
		// If no password is feteched then user is not available so throws exception
		try {
			if(user.getPassword().length() == 0) {
				return null;
			}
		}
		catch (NullPointerException e) {
			throw new NullPointerException();
		}
		
		//Trying to match user entered password for the email id in User table if not matched throws exception
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
	
	//Update login timestamp every time the user logs in
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
	
	//Fetches user last login timestamp
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

	public void deleteUser(String emailID) {
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.DELETE_USER);
			stmt.setString(1, emailID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
	}
}
