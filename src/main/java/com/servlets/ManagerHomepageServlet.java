package com.servlets;

import com.models.*;
import com.service.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ManagerHomepageServlet")
public class ManagerHomepageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ManagerHomepageServlet() {
		super();
	}
	
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
    	
    	// read form fields
    	if(req.getParameter("button-1") != null) {
    		viewPending(req, resp);
    	}
    	else if(req.getParameter("button-2") != null) {
    		RequestDispatcher dispatcher = req.getRequestDispatcher("homepages/viewresolved.jsp");
    		dispatcher.include(req, resp);
    	}
    	else if(req.getParameter("button-3") != null) {
    		RequestDispatcher dispatcher = req.getRequestDispatcher("homepages/viewemployees.jsp");
    		dispatcher.include(req, resp);
    	}
    	else if(req.getParameter("button-4") != null) {
    		RequestDispatcher dispatcher = req.getRequestDispatcher("homepages/singleemployeesrequests.jsp");
    		dispatcher.include(req, resp);
    	}
		else {	
			RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
    		dispatcher.include(req, resp);
		}
    }
    
    private void viewPending(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	ReimbursementService service = new ReimbursementService();
    	
    	PrintWriter out = resp.getWriter();  
        resp.setContentType("text/html");  
        out.println("<html><head>"
        		+ "		<meta charset=\"UTF-8\">"
        		+ "		<title>Pending Requests</title>"
        		+ "	</head><body>");  
        
        try {  
        	Map<Integer, Employee> employees = service.getAllEmployees();
        	Map<Integer, ReimbursementRequest> requests = service.getAllRequests();
        	
            out.println("<table border=1 width=75% height=50%>");  
            out.println("<tr><th>Employee</th><th>Request Number</th><th>Amount</th><th>Description</th><tr>");  
            
            for(Integer key : requests.keySet()) {
            	ReimbursementRequest currentRequest = requests.get(key);
            	
            	if(!currentRequest.getApproval()) {
	            	Employee currentEmployee = employees.get(currentRequest.getEmployeeNumber());
	            	
	                String e = currentEmployee.getName();
	                int r = currentRequest.getRequestNumber();
	                double a = currentRequest.getAmount();
	                String d = currentRequest.getDescription();
	            
	                out.println("<tr><td>" + e + "</td><td>" + r + "</td><td>" + a + "</td><td>" + d + "</td></tr>");
            	}
            }  
            
            out.println("</table>");  
            out.println("</html></body>");
       }  
       catch (Exception e) {  
    	   out.println("error");  
       }
    }
}