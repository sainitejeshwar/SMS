package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

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
	@Override
	public String listAll() {
		// TODO Auto-generated method stub
		return null;
	}
}