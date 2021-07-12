package com.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.ReimbursementService;

@WebServlet("/UpdateInfoServlet")
public class UpdateInfoServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UpdateInfoServlet() {
		super();
	}
	
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
    	
    	// read form fields
        String name = req.getParameter("name");
    	String city = req.getParameter("city");
    	String state = req.getParameter("state");
    	
    	ReimbursementService service = new ReimbursementService();
    	
    	service.updateEmployeeInfo((int) req.getSession().getAttribute("employeeNumber"), name, city, state);
    	
    	RequestDispatcher dispatcher = req.getRequestDispatcher("homepages/employeehomepage.jsp");
    	dispatcher.include(req, resp);
    }
}