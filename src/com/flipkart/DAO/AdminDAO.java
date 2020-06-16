package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.flipkart.bean.Admin;
import com.flipkart.bean.User;
import com.flipkart.constants.SQLQueryConstant;
import com.flipkart.utils.DBUtils;

/*
 * DAO OPERATIONS on admin table 
 * Includes :
 * 			listByID
 * 			listAll 
 * 			addAdmin 
 */

public class AdminDAO{
	private static Connection conn = null;
	private static PreparedStatement stmt = null;
	private static Logger logger = Logger.getLogger(AdminDAO.class);
	
	
	//Get the admin using the emailID of the admin
	// Returns admin object with details fetched from database
	public Admin listByID(String emailID) {
		conn = DBUtils.getConnection();
		ResultSet rs = null;
		Admin admin = new Admin();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.ADMIN_SELECT_BY_ID);
			stmt.setString(1, emailID);
			rs = stmt.executeQuery();
			while(rs.next()) {
				admin.setName(rs.getString("Name"));
				admin.setEmailID(rs.getString("EmailID"));
				admin.setAdminID(rs.getInt("AdminID"));
				admin.setLevel(rs.getString("Level"));
			}
			rs.close();
			return admin;
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return null;
	}
	
	//Get all the admins form the admin table
	// Returns Array List of admin object with details fetched from database
	public ArrayList<Admin> listAll(){
		ArrayList<Admin> admins = new ArrayList<Admin>();
		conn = DBUtils.getConnection();
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.ADMIN_SELECT_ALL);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Admin admin = new Admin();
				admin.setName(rs.getString("Name"));
				admin.setEmailID(rs.getString("EmailID"));
				admin.setAdminID(rs.getInt("AdminID"));
				admin.setLevel(rs.getString("Level"));
				admins.add(admin);
			}
			rs.close();
			return admins;
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return null;
	}

	//Add admin in the admin table corresponding to the emailID
	public void addAdmin(User user , Admin admin) {
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.ADMIN_INSERT);
			stmt.setString(1, user.getemailID());
			stmt.setString(2, user.getName());
			stmt.setString(3, admin.getLevel());
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
	}
}

