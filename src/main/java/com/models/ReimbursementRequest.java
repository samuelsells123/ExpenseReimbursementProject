package com.models;

public class ReimbursementRequest {
	private int employeeNumber;
	private int requestNumber;
	private double amount;
	private String description;
	private boolean approved;
	private String managerApproved;
	
	public ReimbursementRequest(int employeeNumber, int requestNumber, double amount, String description) {
		super();
		this.employeeNumber = employeeNumber;
		this.requestNumber = requestNumber;
		this.amount = amount;
		this.description = description;
		this.approved = false;
		this.managerApproved = "N/A";
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
	
	public boolean getApproval() {
		return approved;
	}
	
	public String getManagerApproved() {
		return managerApproved;
	}
	
	public void approve(String managerApproved) {
		this.approved = true;
		this.managerApproved = managerApproved;
	}
}
