package com.models;

public final class Employee implements Comparable<Employee> {
	private int employeeNumber;
	private String name;
	private double salary;
	private Address address;
	private boolean isManager;
	
	public Employee(int employeeNumber, String name, double salary, Address address, boolean isManager) {
		super();
		this.employeeNumber = employeeNumber;
		this.name = name;
		this.salary = salary;
		this.address = address;
		this.isManager = isManager;
	}
	
	public Employee(int employeeNumber, String name, double salary, String city, String state, boolean isManager) {
		this(employeeNumber, name, salary, new Address(city, state), isManager);
	}
	
	public Employee() {
	}
	
	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(String city, String state) {
		this.address = new Address(city, state);
	}
	
	public boolean isManager() {
		return isManager;
	}
	
	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}
	
	public String greetEmployee() {
		return "Hello,  " + this.name;
	}

	@Override
	public String toString() {
		return "[Employee #: " + employeeNumber + ", Name: " + name + ", salary: " + salary + ", address: " + address + "]";
	}

	@Override
	public int compareTo(Employee e) {
		if(this.getEmployeeNumber() > e.getEmployeeNumber())
			return 1;
		else if(this.getEmployeeNumber() < e.getEmployeeNumber())
			return -1;
		else
			return 0;
	}
}