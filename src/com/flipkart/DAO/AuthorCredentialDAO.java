package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipkart.exception.InvalidUserException;
import com.flipkart.utils.DBUtils;

public class AuthorCredentialDAO implements DBOperations {
	Connection conn = null;
	PreparedStatement stmt = null;
	private static Logger logger = Logger.getLogger(AuthorCredentialDAO.class);
	
	
	public String checkIdentity(int ID , String password) throws InvalidUserException {
			conn = DBUtils.getConnection();
			String sql = "select * from users where ID=?";
			try {
				stmt = conn.prepareStatement(sql);
			} catch (SQLException e) {
				logger.debug(e.getMessage());
			}
			try {
				stmt.setInt(1, ID);
			} catch (SQLException e) {
				logger.debug(e.getMessage());
			}
			ResultSet rs = null;
			try {
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				logger.debug(e.getMessage());
			}
			String res= null;
			String type = null;
			try {
				while(rs.next()) {
					res = rs.getString("Password");
					type = rs.getString("Type");
				}
			} catch (SQLException e) {
				logger.debug(e.getMessage());
			}
			try {
				rs.close();
			} catch (SQLException e) {
				logger.debug(e.getMessage());
			}
			try {
				if(res.equals(password)) {
					return type;
				}
				else {
					throw new InvalidUserException();
				}
				
			}
			catch (NullPointerException e) {
				throw new InvalidUserException();
			}
		
		
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


	@Override
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