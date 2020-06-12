package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipkart.constants.SQLQueryConstant;
import com.flipkart.helper.DBOperations;
import com.flipkart.utils.DBUtils;

public class CourseCatalogDAO implements DBOperations {
	private static  Logger logger = Logger.getLogger(CourseCatalogDAO.class);
	Connection conn = null;
	PreparedStatement stmt = null;
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
		return null;
	}
}
