package com.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ReturnEmployeeServlet")
public class ReturnEmployeeServlet extends HttpServlet  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReturnEmployeeServlet() {
		super();
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("homepages/employeehomepage.jsp");
        dispatcher.include(req, resp);
	}
}