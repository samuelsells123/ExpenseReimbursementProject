package com.models;

public class ReimbursementRequest {
	private int employeeNumber;
	private int requestNumber;
	private double amount;
	private String description;
	
	private String status;
	private String manager;
	
	public ReimbursementRequest(int employeeNumber, int requestNumber, double amount, String description) {
		super();
		this.employeeNumber = employeeNumber;
		this.requestNumber = requestNumber;
		this.amount = amount;
		this.description = description;
		this.status = "Pending";
		this.manager = "N/A";
	}
	
	public ReimbursementRequest(int employeeNumber, int requestNumber, double amount, String description, String status, String manager) {
		super();
		this.employeeNumber = employeeNumber;
		this.requestNumber = requestNumber;
		this.amount = amount;
		this.description = description;
		this.status = status;
		this.manager = manager;
	}
	
	public ReimbursementRequest() {
	}

	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public int getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(int requestNumber) {
		this.requestNumber = requestNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getStatus() {
		return status;
	}
	
	public String getManager() {
		return manager;
	}
	
	public void approve(String managerApproved) {
		this.status = "Approved";
		this.manager = managerApproved;
	}
	
	public void deny(String managerDenied) {
		this.status = "Denied";
		this.manager = managerDenied;
	}
}
