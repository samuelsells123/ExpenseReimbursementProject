package com.servlets;

import com.service.ReimbursementService;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ApproveOrDenyServlet")
public class ApproveOrDenyServlet extends HttpServlet  {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ApproveOrDenyServlet() {
			super();
		}
		
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			ReimbursementService service = new ReimbursementService();
			Set<Integer> requestNumbers = service.getAllRequests().keySet();
			Iterator<Integer> it = requestNumbers.iterator();
			
			int requestNumber = it.next();
			
			while(req.getParameter("approvebutton-" + requestNumber) == null && req.getParameter("denybutton-" + requestNumber) == null) {
				requestNumber = it.next();
			}
			
			int employeeNumber = (int) req.getSession().getAttribute("employeeNumber");
			String managerName = service.getAllEmployees().get(employeeNumber).getName();
			
			if(req.getParameter("approvebutton-" + requestNumber) != null)
				service.approve(requestNumber, managerName);
			else
				service.deny(requestNumber, managerName);
			
			resp.setContentType("text/html");
	        resp.setCharacterEncoding("UTF-8");
	        
	        RequestDispatcher dispatcher = req.getRequestDispatcher("homepages/managerhomepage.jsp");
	        dispatcher.include(req, resp);
		}
	}
