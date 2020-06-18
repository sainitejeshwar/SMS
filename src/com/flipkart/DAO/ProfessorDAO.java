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
/*
 * CLASS DESCIPTION
 * 
 * For perfomring oprations on Professor Table
 * And also doing professor related DAO operations
 */
public class ProfessorDAO {
	private static Connection conn = null;
	private static PreparedStatement stmt = null;
	private static Logger logger = Logger.getLogger(ProfessorDAO.class);
	
	//Add a professor in Professor Tabel
	public void addProf(User user) {
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.PROFESSOR_INSERT);
			stmt.setString(1, user.getemailID());
			stmt.setString(2, user.getName());
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
	}
	
	//Get details about particular professor using his email ID
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
	
	// For listing all the professors and returning an ArrayList of professor objects
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
