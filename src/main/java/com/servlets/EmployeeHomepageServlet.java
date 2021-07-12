package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.models.Employee;
import com.models.ReimbursementRequest;
import com.service.ReimbursementService;

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
        
        ReimbursementService service = new ReimbursementService();
 
    	RequestDispatcher dispatcher;  	
    	
    	if(req.getParameter("button-1") != null) {
    		dispatcher = req.getRequestDispatcher("dataEntry/submitrequest.jsp");
			dispatcher.include(req, resp);
    	}
    	else if(req.getParameter("button-2") != null)
    		viewMyPending(req, resp, service);
    	else if(req.getParameter("button-3") != null)	
    		viewMyResolved(req, resp, service);
    	else if(req.getParameter("button-4") != null)
    		updateInfo(req, resp, service);
		else {	
			dispatcher = req.getRequestDispatcher("index.jsp");
			dispatcher.include(req, resp);
		}
    }
    
    private void viewMyPending(HttpServletRequest req, HttpServletResponse resp, ReimbursementService service) throws ServletException, IOException {
    	PrintWriter out = resp.getWriter();  
        resp.setContentType("text/html");  
        out.println("<html><head>"
        		+ "		<meta charset=\"UTF-8\">"
        		+ "		<title>My Pending Requests</title>"
        		+ "	</head><body>");  
        
    	Map<Integer, ReimbursementRequest> requests = service.getAllRequests();
    	
        out.println("<table border=1 width=80% height=60%>");  
        out.println("<tr><th>Request Number</th><th>Amount</th><th>Description</th></tr>");  
        
        int employeeNumber = (int) req.getSession().getAttribute("employeeNumber");
        
        for(Integer key : requests.keySet()) {
        	ReimbursementRequest currentRequest = requests.get(key);
        	
        	if(currentRequest.getEmployeeNumber() == employeeNumber && currentRequest.getStatus().equals("Pending")) {
                int r = currentRequest.getRequestNumber();
                double a = currentRequest.getAmount();
                String d = currentRequest.getDescription();
            
                out.println("<tr><td>" + r + "</td><td>" + a + "</td><td>" + d + "</td></tr>");
        	}
        }  
        
        out.println("</table></form><br>");
        out.println("<form name= \"EmployeeReturnForm\" action=\"ReturnEmployeeServlet\" method=\"post\">");
        out.println("<input type=\"submit\" value=\"Back to Homepage\" name=\"return-button\" />");
        out.println("</form></body></html>");
    }
    
    private void viewMyResolved(HttpServletRequest req, HttpServletResponse resp, ReimbursementService service) throws ServletException, IOException {
    	PrintWriter out = resp.getWriter();  
        resp.setContentType("text/html");  
        out.println("<html><head>"
        		+ "		<meta charset=\"UTF-8\">"
        		+ "		<title>My Resolved Requests</title>"
        		+ "	</head><body><form name= \"EmployeeRequestsForm\" action=\"ReturnEmployeeServlet\" method=\"post\">");  
        
    	Map<Integer, ReimbursementRequest> requests = service.getAllRequests();
    	
        out.println("<table border=1 width=80% height=60%>");  
        out.println("<tr><th>Request Number</th><th>Amount</th><th>Description</th><th>Status</th><th>Manager Approved/Denied</th></tr>");  
        
        int employeeNumber = (int) req.getSession().getAttribute("employeeNumber");
        
        for(Integer key : requests.keySet()) {
        	ReimbursementRequest currentRequest = requests.get(key);
        	
        	if(currentRequest.getEmployeeNumber() == employeeNumber && !currentRequest.getStatus().equals("Pending")) {
                int r = currentRequest.getRequestNumber();
                double a = currentRequest.getAmount();
                String d = currentRequest.getDescription();
                String s = currentRequest.getStatus();
                String m = currentRequest.getManager();
            
                out.println("<tr><td>" + r + "</td><td>" + a + "</td><td>" + d + "</td><td>" + s + "</td><td>" + m + "</td></tr>");
        	}
        }  
        
        out.println("</table></form><br>");
        out.println("<form name= \"EmployeeReturnForm\" action=\"ReturnEmployeeServlet\" method=\"post\">");
        out.println("<input type=\"submit\" value=\"Back to Homepage\" name=\"return-button\" />");
        out.println("</form></body></html>");
    }
    
    private void updateInfo(HttpServletRequest req, HttpServletResponse resp, ReimbursementService service) throws ServletException, IOException {
    	PrintWriter out = resp.getWriter();  
        resp.setContentType("text/html");
        out.println("<meta charset=\"UTF-8\">\r\n"
        		+ "<title>Update Employee Information</title>"
        		+ "</head><br><br><br>"
        		+ "<body><form name=\"UpdateInfo\" action=\"UpdateInfoServlet\" method=\"post\">");
        
        int employeeNumber = (int) req.getSession().getAttribute("employeeNumber");
        Employee employee = service.getAllEmployees().get(employeeNumber);
        
        out.println("<h1 align=\"center\"></h1><table align=\"center\">" 
        		+ "<tr><td>Name</td><td><input type=\"text\" name=\"name\" value = \"" + employee.getName() + "\"></td></tr>"
        		+ "<tr><td>City</td><td><input type=\"text\" name=\"city\" value = \"" + employee.getAddress().getCity() + "\"></td></tr>"
        		+ "<tr><td>State</td><td><input type=\"text\" name=\"state\" value = \"" + employee.getAddress().getState() + "\"></td></tr>" 
        		+ "<tr><td></td><td><input type=\"submit\" value=\"submit\" id=\"button\" /></td></tr>");
        
        out.println("</table></form><br>"
        		+ "<form name= \"EmployeeReturnForm\" action=\"ReturnEmployeeServlet\" method=\"post\">"
        		+ "<input type=\"submit\" value=\"Back to Homepage\" name=\"return-button\"/>"
        		+ "</form></body></html>");
    }
}