package com.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.service.ReimbursementService;

@WebServlet("/NewUserServlet")
public class NewUserServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NewUserServlet() {
		super();
	}
	
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
    	
    	// read form fields
        String name = req.getParameter("name");
    	double salary = Double.parseDouble(req.getParameter("salary"));
    	String city = req.getParameter("city");
    	String state = req.getParameter("state");
    	
    	String username = req.getParameter("username-create");
    	String password = req.getParameter("password-create");
    	
    	ReimbursementService service = new ReimbursementService();
    	int employeeNumber = service.generateEmployeeNumber();
    	
    	service.addEmployee(employeeNumber, name, salary, city, state);
    	service.addAcount(employeeNumber, username, password);
    	
    	RequestDispatcher dispatcher = req.getRequestDispatcher("homepages/employeehomepage.jsp");
    	dispatcher.include(req, resp);
    }
}
