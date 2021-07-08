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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}
	
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
    	
    	// read form fields
    	String username = req.getParameter("username");
    	String password = req.getParameter("password");
    	
    	ReimbursementService service = new ReimbursementService();
    	Map<String, LoginInfo> loginInfo = service.getLoginInfo();
    	RequestDispatcher dispatcher;
    	
    	if(loginInfo.containsKey(username) && loginInfo.get(username).getPassword().equals(password)) {
    		Map<Integer, Employee> employees = service.getAllEmployees();
    		int employeeNumber = loginInfo.get(username).getEmployeeNumber();
    		
    		if(employees.get(employeeNumber).isManager())
    			dispatcher = req.getRequestDispatcher("homepages/managerhomepage.jsp");
    		else
    			dispatcher = req.getRequestDispatcher("homepages/employeehomepage.jsp");
		}
    	else
    		dispatcher = req.getRequestDispatcher("loginfail.jsp");
	    
    	dispatcher.include(req, resp);
    }
}