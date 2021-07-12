package com.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.service.ReimbursementService;

@WebServlet("/SubmitRequestServlet")
public class SubmitRequestServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SubmitRequestServlet() {
		super();
	}
	
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
    	
    	// read form fields
    	double amount = Double.parseDouble(req.getParameter("amount"));
    	String description = req.getParameter("description");
    	
    	ReimbursementService service = new ReimbursementService();
    	
    	service.addRequest((int) req.getSession().getAttribute("employeeNumber"), amount, description);
    	
    	RequestDispatcher dispatcher = req.getRequestDispatcher("homepages/employeehomepage.jsp");
    	dispatcher.include(req, resp);
    }
}