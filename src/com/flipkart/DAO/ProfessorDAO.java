package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.constants.SQLQueryConstant;
import com.flipkart.helper.DBOperations;
import com.flipkart.utils.DBUtils;

public class ProfessorDAO implements DBOperations{
	Connection conn = null;
	PreparedStatement stmt = null;
	private static Logger logger = Logger.getLogger(ProfessorDAO.class);
	
	
	public Professor listByID(String emailid) {
		conn = DBUtils.getConnection();
		ResultSet rs = null;
		Professor professor = new Professor();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.STUDENT_PROFESSOR_BY_ID);
			stmt.setString(1, emailid);
			rs = stmt.executeQuery();
			while(rs.next()) {
				professor.setProfessorID(rs.getInt("ProfID"));
				professor.setName(rs.getString("Name"));
			return professor;
			}
			rs.close();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return null;
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

	public String getAllCourse(Integer id) {
		// TODO Auto-generated method stub
		return null;
		
	}
	
	 

}
