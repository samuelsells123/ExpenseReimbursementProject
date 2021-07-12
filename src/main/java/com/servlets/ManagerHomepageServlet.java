package com.servlets;

import com.models.*;
import com.service.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

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
    	
        ReimbursementService service = new ReimbursementService();
        
    	// read form fields
    	if(req.getParameter("button-1") != null)
    		viewPending(req, resp, service);
    	else if(req.getParameter("button-2") != null)
    		viewResolved(req, resp, service);
    	else if(req.getParameter("button-3") != null)
    		viewEmployees(req, resp, service);
    	else if(req.getParameter("button-4") != null)
    		viewSingleEmployeeRequests(req, resp, service);
		else {	
			RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
    		dispatcher.include(req, resp);
		}
    }
    
    private void viewPending(HttpServletRequest req, HttpServletResponse resp, ReimbursementService service) throws ServletException, IOException {
    	PrintWriter out = resp.getWriter();  
        resp.setContentType("text/html");  
        out.println("<html><head>"
        		+ "<meta charset=\"UTF-8\">"
        		+ "<title>Pending Requests</title>"
        		+ "	</head><body>");  
          
    	Map<Integer, Employee> employees = service.getAllEmployees();
    	Map<Integer, ReimbursementRequest> requests = service.getAllRequests();
    	
    	out.println("<form name= \"ApproveRequestsForm\" action=\"ApproveOrDenyServlet\" method=\"post\">");
        out.println("<table border=1 width=80% height=60%>");  
        out.println("<tr><th>Employee</th><th>Request Number</th><th>Amount</th><th>Description</th></tr>");  
        
        for(Integer key : requests.keySet()) {
        	ReimbursementRequest currentRequest = requests.get(key);
        	
        	if(currentRequest.getStatus().equals("Pending")) {
            	Employee currentEmployee = employees.get(currentRequest.getEmployeeNumber());
            	
                String e = currentEmployee.getName();
                int r = currentRequest.getRequestNumber();
                double a = currentRequest.getAmount();
                String d = currentRequest.getDescription();
            
                out.println("<tr><td>" + e + "</td><td>" + r + "</td><td>" + a + "</td><td>" + d + "</td>");
                out.println("<td><input type=\"submit\" value=\"Approve\" name=\"approvebutton-" + r + "\"/></td>");
                out.println("<td><input type=\"submit\" value=\"Deny\" name=\"denybutton-" + r + "\"/></td></tr>");
        	}
        }  
        
        out.println("</table></form><br>");
        out.println("<form name= \"ManagerReturnForm\" action=\"ReturnManagerServlet\" method=\"post\">");
        out.println("<input type=\"submit\" value=\"Back to Homepage\" name=\"return-button\" />");
        out.println("</form></body></html>");
    }
    
    private void viewResolved(HttpServletRequest req, HttpServletResponse resp, ReimbursementService service) throws ServletException, IOException {
    	PrintWriter out = resp.getWriter();  
        resp.setContentType("text/html");  
        out.println("<html><head>"
        		+ "<meta charset=\"UTF-8\">"
        		+ "<title>Resolved Requests</title>"
        		+ "	</head><body><form name= \"ManagerRequestsForm\" action=\"ReturnManagerServlet\" method=\"post\">");  
         
    	Map<Integer, Employee> employees = service.getAllEmployees();
    	Map<Integer, ReimbursementRequest> requests = service.getAllRequests();
    	
        out.println("<table border=1 width=80% height=60%>");  
        out.println("<tr><th>Employee</th><th>Request Number</th><th>Amount</th><th>Description</th><th>Status</th><th>Manager Approved/Denied</th></tr>");  
        
        for(Integer key : requests.keySet()) {
        	ReimbursementRequest currentRequest = requests.get(key);
        	
        	if(!currentRequest.getStatus().equals("Pending")) {
            	Employee currentEmployee = employees.get(currentRequest.getEmployeeNumber());
            	
                String e = currentEmployee.getName();
                int r = currentRequest.getRequestNumber();
                double a = currentRequest.getAmount();
                String d = currentRequest.getDescription();
                String s = currentRequest.getStatus();
                String m = currentRequest.getManager();
            
                out.println("<tr><td>" + e + "</td><td>" + r + "</td><td>" + a + "</td><td>" + d + "</td><td>" + s + "</td><td>" + m + "</td></tr>");
        	}
        }  
        
        out.println("</table><br>");
        out.println("<input type=\"submit\" value=\"Back to Homepage\" name=\"return-button\" />");
        out.println("</form></body></html>");
    }
    
    private void viewEmployees(HttpServletRequest req, HttpServletResponse resp, ReimbursementService service) throws ServletException, IOException {
    	PrintWriter out = resp.getWriter();  
        resp.setContentType("text/html");  
        out.println("<html><head>"
        		+ "<meta charset=\"UTF-8\">"
        		+ "<title>View Employees</title>"
        		+ "	</head><body><form name= \"ManagerRequestsForm\" action=\"ReturnManagerServlet\" method=\"post\">");
         
    	Map<Integer, Employee> employees = service.getAllEmployees();
    	
        out.println("<table border=1 width=80% height=60%>");  
        out.println("<tr><th>Employee Number</th><th>Name</th><th>Salary</th><th>Address</th><th>Manager?</th></tr>");  
        
        for(Integer key : employees.keySet()) {
        	Employee currentEmployee = employees.get(key);
        	
            String n = currentEmployee.getName();
            double s = currentEmployee.getSalary();
            String a = currentEmployee.getAddress().toString();
            String m = currentEmployee.isManager() ? "Yes" : "No";
            
            out.println("<tr><td>" + key + "</td><td>" + n + "</td><td>" + s + "</td><td>" + a + "</td><td>" + m + "</td></tr>");
        }  
        
        out.println("</table><br>");
        out.println("<input type=\"submit\" value=\"Back to Homepage\" name=\"return-button\" />");
        out.println("</form></body></html>");
    }
    
    private void viewSingleEmployeeRequests(HttpServletRequest req, HttpServletResponse resp, ReimbursementService service) throws ServletException, IOException {
    	PrintWriter out = resp.getWriter();  
        resp.setContentType("text/html");  
        out.println("<html><head>"
        		+ "<meta charset=\"UTF-8\">"
        		+ "<title>Select Employee</title>"
        		+ "</head><body>");  
          
    	Map<Integer, Employee> employees = service.getAllEmployees();
    	
    	out.println("<form name= \"SelectEmployeeForm\" action=\"ShowEmployeeRequestsServlet\" method=\"post\">");
        out.println("<h3 align=\"center\">Select an Employee</h3>");
    	out.println("<table align=\"center\">");  
        
        for(Integer key : employees.keySet()) {
        	Employee currentEmployee = employees.get(key);
        
            out.println("<td><input type=\"submit\" value=\"" + currentEmployee.getName() + "\" name=\"button-" + key + "\"/></td></tr>");
        }
        
        out.println("</table></form><br>");
        out.println("<form name= \"ManagerReturnForm\" action=\"ReturnManagerServlet\" method=\"post\">");
        out.println("<input type=\"submit\" value=\"Back to Homepage\" name=\"return-button\" />");
        out.println("</form></body></html>");
    }
}