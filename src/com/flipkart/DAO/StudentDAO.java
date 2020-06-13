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


	public ArrayList<Student> listAll() {
		// TODO Auto-generated method stub
		ArrayList<Student> students = new ArrayList<Student>();
		conn = DBUtils.getConnection();
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.STUDENT_SELECT_ALL);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Student student = new Student();
				student.setStudentID(rs.getString("StudentID"));
				student.setName(rs.getString("Name"));
				student.setBranch(rs.getString("Branch"));
				student.setSemester(rs.getInt("Semester"));
				student.setStudentCourses(rs.getString("Course1"));
				student.setStudentCourses(rs.getString("Course2"));
				student.setStudentCourses(rs.getString("Course3"));
				student.setStudentCourses(rs.getString("Course4"));
				students.add(student);
			}
			rs.close();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
		return students;
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
	public void UpdateStudentRegistration(ArrayList<Course> courses , Student student) {
		conn = DBUtils.getConnection();
		try {
			stmt = conn.prepareStatement(SQLQueryConstant.UPDATE_STUDENT_REGISTRATION);
			stmt.setInt(1, courses.get(0).getCourseCode());
			stmt.setInt(2, courses.get(1).getCourseCode());
			stmt.setInt(3, courses.get(2).getCourseCode());
			stmt.setInt(4, courses.get(3).getCourseCode());
			stmt.setInt(5, student.getRegistrationNumber());
			stmt.setString(6, student.getStudentID());
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
		}
	}

}
