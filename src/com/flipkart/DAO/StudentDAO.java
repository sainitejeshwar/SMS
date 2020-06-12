package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipkart.bean.Student;
import com.flipkart.constants.SQLQueryConstant;
import com.flipkart.helper.DBOperations;
import com.flipkart.utils.DBUtils;

public class StudentDAO implements DBOperations{
	Connection conn = null;
	PreparedStatement stmt = null;
	private static Logger logger = Logger.getLogger(StudentDAO.class);
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


	public Student listByID(String emailid) {
		conn = DBUtils.getConnection();
		ResultSet rs = null;
		Student student = new Student();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.STUDENT_SELECT_BY_ID);
			stmt.setString(1, emailid);
			rs = stmt.executeQuery();
			while(rs.next()) {
				student.setStudentID(rs.getString("StudentID"));
				student.setName(rs.getString("Name"));
				student.setBranch(rs.getString("Branch"));
				student.setSemester(rs.getInt("Semester"));
				student.setStudentCourses(rs.getString("Course1"));
				student.setStudentCourses(rs.getString("Course2"));
				student.setStudentCourses(rs.getString("Course3"));
				student.setStudentCourses(rs.getString("Course4"));
			return student;
			}
			rs.close();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return null;
	}

}
