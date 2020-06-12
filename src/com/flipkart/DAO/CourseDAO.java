package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.constants.SQLQueryConstant;
import com.flipkart.helper.DBOperations;
import com.flipkart.utils.DBUtils;

public class CourseDAO implements DBOperations {
	Connection conn = null;
	PreparedStatement stmt = null;
	private static Logger logger = Logger.getLogger(CourseDAO.class);
	Course course = new Course();
	
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


	public String listAll(int sem) {
		conn = DBUtils.getConnection();
		ResultSet rs = null;
		String res = "";
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.COURSE_SELECT_BY_SEM);
			stmt.setInt(1, sem);
			rs = stmt.executeQuery();
			while(rs.next()) {
				res = res + rs.getInt("CourseCode")+"\t"+rs.getString("Name") + "\t"+rs.getString("ProfessorInfo")+"\n";
			}
			rs.close();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return res;
	}

	public String getAllStudents(int courseCode) {
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public String listAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
