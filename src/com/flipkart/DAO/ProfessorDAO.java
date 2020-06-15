package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.flipkart.bean.Professor;
import com.flipkart.bean.User;
import com.flipkart.constants.SQLQueryConstant;
import com.flipkart.utils.DBUtils;

public class ProfessorDAO {
	private static Connection conn = null;
	private static PreparedStatement stmt = null;
	private static Logger logger = Logger.getLogger(ProfessorDAO.class);
	
	public void addProf(User user) {
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.PROF_INSERT);
			stmt.setString(1, user.getemailID());
			stmt.setString(2, user.getName());
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
	}
	public Professor listByID(String emailid) {
		conn = DBUtils.getConnection();
		ResultSet rs = null;
		Professor professor = new Professor();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.PROFESSOR_SELECT_BY_ID);
			stmt.setString(1, emailid);
			rs = stmt.executeQuery();
			while(rs.next()) {
				professor.setProfessorID(rs.getInt("ProfID"));
				professor.setName(rs.getString("Name"));
			}
			rs.close();
			return professor;
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return null;
	}
	public ArrayList<Professor> listAll() {
		conn = DBUtils.getConnection();
		ResultSet rs = null;
		ArrayList<Professor> professors = new ArrayList<Professor>();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.PROFESSOR_SELECT_ALL);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Professor professor = new Professor();
				professor.setProfessorID(rs.getInt("ProfID"));
				professor.setName(rs.getString("Name"));
				professors.add(professor);
			}
			rs.close();
			
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return professors;
	}
}
