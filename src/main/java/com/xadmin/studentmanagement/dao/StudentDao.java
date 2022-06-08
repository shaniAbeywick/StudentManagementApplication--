package com.xadmin.studentmanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.studentmanagement.bean.Student;


public class StudentDao {
	private String jdbcURL ="jdbc:mysql://localhost:3306/studentdb?useSSL=false";
	private String jdbcUsername ="root";
	private String jdbcPassword ="root";
	private String jdbcDriver ="com.mysql.jdbc.Driver";
	
	public StudentDao() {
		
		
	}
	protected Connection getConnection() {
		Connection connection =null;
		try {
		Class.forName(jdbcDriver);
		connection =DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	//insert a student
	public void insertStudent(Student student) {
		
		try(Connection connection=getConnection();
		    PreparedStatement preparedStatement =connection.prepareStatement("INSERT INTO students "+" (name,email,subject) VALUES " + "(?,?,?);")){
			preparedStatement.setString(1,student.getName() );
			preparedStatement.setString(2,student.getEmail() );
			preparedStatement.setString(3,student.getSubject());
			preparedStatement.executeUpdate();
	     }
		catch(SQLException e) {
			printSQLException(e);
		}
	}
	//select student by id
	public Student selectStudent(int id) {
		Student student = null;
		
		try (Connection connection = getConnection();
				
		    PreparedStatement preparedStatement = connection.prepareStatement("SELECT id,name,email,subject FROM students WHERE id=?;")) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			
			while (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String subject = rs.getString("subject");
				student = new Student(id, name, email, subject);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return student;
	}
	//select all student's info
	public List<Student> selectAllStudents() {
       List<Student> students = new ArrayList<>();
	   try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM students");) {
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String subject = rs.getString("subject");
				students.add(new Student(id, name, email, subject));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
	   
		return students;
	}
	
	// update student's data
	public boolean updateStudent(Student student) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement("UPDATE students SET name=? ,email=? ,subject=? WHERE id=?;");) {
			statement.setString(1, student.getName());
			statement.setString(2, student.getEmail());
			statement.setString(3, student.getSubject());
			statement.setInt(4, student.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	//delete a student
	
	public boolean deleteStudent(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement("DELETE FROM students WHERE id=?;");) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	//handle the error
	private void printSQLException(SQLException ex) {
		   for (Throwable e : ex) {
				if (e instanceof SQLException) {
					e.printStackTrace(System.err);
					System.err.println("SQLState: " + ((SQLException) e).getSQLState());
					System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
					System.err.println("Message: " + e.getMessage());
					Throwable t = ex.getCause();
					while (t != null) {
						System.out.println("Cause: " + t);
						t = t.getCause();
					}
				}	
		   }
	}
}
	
	
	
	
	
	
	
	

    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
   

