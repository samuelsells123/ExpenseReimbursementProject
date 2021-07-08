package com.models;

public class LoginInfo {
	private int employeeNumber;
	private String username;
	private String password;
	
	public LoginInfo(int employeeNumber, String username, String password) {
		super();
		this.employeeNumber = employeeNumber;
		this.username = username;
		this.password = password;
	}
	
	public LoginInfo() {
	}
	
	public int getEmployeeNumber() {
		return employeeNumber;
	}
	
	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
