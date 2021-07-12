package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.models.Employee;
import com.models.ReimbursementRequest;
import com.service.ReimbursementService;

@WebServlet("/ShowEmployeeRequestsServlet")
public class ShowEmployeeRequestsServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ShowEmployeeRequestsServlet() {
		super();
	}
	
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	ReimbursementService service = new ReimbursementService();
    	Map<Integer, Employee> employees = service.getAllEmployees();
    	Map<Integer, ReimbursementRequest> requests = service.getAllRequests();
    	
    	Iterator<Integer> it = employees.keySet().iterator();
    	int employeeNumber = (int) it.next();
    	
    	while(req.getParameter("button-" + employeeNumber) == null) {
    		employeeNumber = (int) it.next();
		}
    	
        PrintWriter out = resp.getWriter();  
        resp.setContentType("text/html");  
        out.println("<html><head>"
        		+ "<meta charset=\"UTF-8\">"
        		+ "<title>" + employees.get(employeeNumber) + "'s Reimbursement Requests</title>"
        		+ "</head><body><table border=1 width=80% height=60%>"
        		+ "<tr><th>Request Number</th><th>Amount</th><th>Description</th><th>Status</th><th>Manager Approved/Denied</th></tr>");
        
		for(int key : requests.keySet()) {
			ReimbursementRequest request = requests.get(key);
			
			if(request.getEmployeeNumber() == employeeNumber) {
                out.println("<tr><td>" + request.getRequestNumber() + "</td>"
                		+ "<td>" + request.getAmount() + "</td>"
                		+ "<td>" + request.getDescription() + "</td>"
                		+ "<td>" + request.getStatus() + "</td>"
                		+ "<td>" + request.getManager() + "</td></tr>");
			}
		}
		
		out.println("</table></form><br>");
        out.println("<form name= \"ManagerReturnForm\" action=\"ReturnManagerServlet\" method=\"post\">");
        out.println("<input type=\"submit\" value=\"Back to Homepage\" name=\"return-button\" />");
        out.println("</form></body></html>");
    }
}