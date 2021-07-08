package com.service;

import java.util.Map;
import java.util.HashMap;

import com.models.*;

public class ReimbursementService {

	private Map<Integer, Employee> employees = new HashMap<Integer, Employee>();
	private Map<String, LoginInfo> loginInfo = new HashMap<String, LoginInfo>();
	private Map<Integer, ReimbursementRequest> reimbursementRequests = new HashMap<Integer, ReimbursementRequest>();
	
	public ReimbursementService() {
		// empNo, name, salary, city, state
		employees.put(1, new Employee(1, "Johnny Fakename", 100.00, "Nowhere Island", "NW", false));
		employees.put(2, new Employee(2, "Somemanager Dude", 10000.00, "Nowhere Island", "NW", true));
		employees.put(3, new Employee(3, "TestGuy1", 1.00, "Nowhere Island", "NW", true));
		employees.put(4, new Employee(4, "TestGuy2", 2.00, "Nowhere Island", "NW", false));
		
		loginInfo.put("Sk8rdude195", new LoginInfo(1, "Sk8rdude195", "12345"));
		loginInfo.put("sdude", new LoginInfo(2, "sdude", "somepassword"));
		loginInfo.put("z", new LoginInfo(3, "z", "z"));
		loginInfo.put("x", new LoginInfo(4, "x", "x"));
		
		reimbursementRequests.put(101, new ReimbursementRequest(1, 101, 100.00, "Mint PS1 Tony Hawk's Pro Skater 1-4"));
		reimbursementRequests.put(102, new ReimbursementRequest(2, 102, 1000.00, "Business Stuff"));
	}
	
	public Map<Integer, Employee> getAllEmployees(){
		return employees;
	}
	
	public Map<String, LoginInfo> getLoginInfo(){
		return loginInfo;
	}
	
	public Map<Integer, ReimbursementRequest> getAllRequests(){
		return reimbursementRequests;
	}
}
