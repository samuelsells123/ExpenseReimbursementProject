package com.servlets;

import com.models.*;
import com.service.*;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EmployeeHomepageServlet")
public class EmployeeHomepageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeHomepageServlet() {
		super();
	}
	
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
    	
    	// read form fields
    	String buttonPressed = req.getParameter("name");
    	
    	ReimbursementService service = new ReimbursementService();
    	RequestDispatcher dispatcher;  	
    	
    	if(req.getParameter("button-1") != null)
    		dispatcher = req.getRequestDispatcher("homepages/submitrequest.jsp");
    	else if(req.getParameter("button-2") != null)
			dispatcher = req.getRequestDispatcher("homepages/viewpending.jsp");
    	else if(req.getParameter("button-3") != null)	
			dispatcher = req.getRequestDispatcher("homepages/viewresolved.jsp");
    	else if(req.getParameter("button-4") != null)	
			dispatcher = req.getRequestDispatcher("homepages/updateinfo.jsp");
		else	
			dispatcher = req.getRequestDispatcher("index.jsp");
		
		dispatcher.include(req, resp);
    }
}