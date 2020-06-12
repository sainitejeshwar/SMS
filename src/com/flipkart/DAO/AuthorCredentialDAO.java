package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.apache.log4j.Logger;

import com.flipkart.bean.User;
import com.flipkart.constants.SQLQueryConstant;
import com.flipkart.exception.InvalidUserException;
import com.flipkart.helper.DBOperations;
import com.flipkart.utils.DBUtils;

public class AuthorCredentialDAO implements DBOperations {
	Connection conn = null;
	PreparedStatement stmt = null;
	private static Logger logger = Logger.getLogger(AuthorCredentialDAO.class);
	User user = new User();
	
	public String checkIdentity(String emailid , String password) throws InvalidUserException {
			conn = DBUtils.getConnection();
			ResultSet rs = null;
			try {
				stmt = conn.prepareStatement(SQLQueryConstant.USER_SELECT_BY_ID);
				stmt.setString(1, emailid);
				rs = stmt.executeQuery();
				while(rs.next()) {
					user.setPassword(rs.getString("Password"));
					user.setType(rs.getString("Type"));
				}
				
				rs.close();
			} catch (SQLException e) {
				logger.debug(e.getMessage());
			}
			try {
				if(user.getPassword().equals(password)) {
					return user.getType();
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
		ResultSet rs = null;
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

	@Override
	public boolean insertdata() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean updatedata() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean deletedata() {
		// TODO Auto-generated method stub
		return false;
	}


	
	public String listAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * insertuser
	 * deleteuser
	 * modify user
	 * list all user -- only accessible by admin
	 */

}
