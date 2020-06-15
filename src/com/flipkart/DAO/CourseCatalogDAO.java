package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipkart.constants.SQLQueryConstant;
import com.flipkart.utils.DBUtils;

public class CourseCatalogDAO{
	private static  Logger logger = Logger.getLogger(CourseCatalogDAO.class);
	Connection conn = null;
	PreparedStatement stmt = null;
}
