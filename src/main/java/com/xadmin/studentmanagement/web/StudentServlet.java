package com.xadmin.studentmanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.studentmanagement.bean.Student;
import com.xadmin.studentmanagement.dao.StudentDao;


@WebServlet("/")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private StudentDao studentDao;  
    
	public void init() throws ServletException {
		studentDao = new StudentDao();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action =request.getServletPath();
		try {
			switch (action) {
			case "/newStudent":
				showNewForm(request, response);
				break;
			case "/insertStudent":
				insertStudent(request, response);
				break;
			case "/deleteStudent":
				deleteStudent(request, response);
				break;
			case "/editStudent":
				showEditForm(request, response);
				break;
			case "/updateStudent":
				updateStudent(request, response);
				break;
			default:
				listStudent(request, response);
				break;
			}
		
	}
    catch(SQLException ex) {
    	throw new ServletException(ex);
    }
	}	
		private void showNewForm(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
			dispatcher.forward(request, response);
		}

		private void showEditForm(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, ServletException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			Student existingStudent = studentDao.selectStudent(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
			request.setAttribute("student", existingStudent);
			dispatcher.forward(request, response);

		}

		private void insertStudent(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String subject = request.getParameter("subject");
			Student newStudent = new Student(name, email,subject);
			studentDao.insertStudent(newStudent);
			response.sendRedirect("list");
		}

		private void updateStudent(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String subject= request.getParameter("subject");

			Student updatedList = new Student(id, name, email, subject);
			studentDao.updateStudent(updatedList);
			response.sendRedirect("list");
		}

		private void deleteStudent(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			studentDao.deleteStudent(id);
			response.sendRedirect("list");

		}	
		private void listStudent(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException, ServletException {
			List<Student> listStudent = studentDao.selectAllStudents();
			request.setAttribute("listStudent", listStudent);
			RequestDispatcher dispatcher = request.getRequestDispatcher("student-list.jsp");
			dispatcher.forward(request, response);
		}

	

}
