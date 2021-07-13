package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.HashMap;
import com.jdbc.DBconnection;
import com.models.*;

public class ReimbursementService {
	public ReimbursementService() {
		
	}
	
	//Sends SQL query to database
	public ResultSet sendSQLquery(String sqlMessage, String failMessage) {
        ResultSet rs = null;
		
		try(Connection connection = DBconnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sqlMessage);
            rs = ps.executeQuery();
            
        } catch(Exception e) {
            System.out.println(failMessage + " " + e.getMessage());
        }
		
		return rs;
	}
	
	//Adds employee to database
	public void addEmployee(int employeeNumber, String name, double salary, String city, String state) {
		String sql = "";
        
        try(Connection connection = DBconnection.getConnection()) {
            sql = "INSERT INTO EMPLOYEES (EMPLOYEENUMBER, NAME, SALARY, CITY, STATE, ISMANAGER) "
            		+ "VALUES (" + employeeNumber
            		+ ", " + "'" + name + "'"
            		+ ", " + salary
            		+ ", " +  "'" + city + "'" 
            		+ ", " + "'" + state + "'"
            		+ ", 0)";
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        } catch(Exception e) {
            System.out.println("Failed to add reimbursement request to database" + e.getMessage());
        }
	}
	
	//Adds acount to database
	public void addAcount(int employeeNumber, String username, String password) {
		String sql = "";
        
        try(Connection connection = DBconnection.getConnection()) {
            sql = "INSERT INTO LOGIN (EMPLOYEENUMBER, USERNAME, PASSWORD) "
            		+ "VALUES (" + employeeNumber
            		+ ", " + "'" + username + "'" 
            		+ ", " + "'" + password + "')";
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        } catch(Exception e) {
            System.out.println("Failed to add reimbursement request to database" + e.getMessage());
        }
	}
	
	//Approves the reimbursement request whose number is specified
	public void approve(int requestNumber, String managerName) {
		String sql = "UPDATE REIMBURSEMENTREQUESTS "
            		+ "SET MANAGER = '" + managerName + "', STATUS = 'Approved'"
            		+ "WHERE REQUESTNUMBER = " + requestNumber;
        String failMessage = "Failed to approve request in database";
		
		sendSQLquery(sql, failMessage);
	}
	
	//Denies the reimbursement request whose number is specfied
	public void deny(int requestNumber, String managerName) {
		String sql = "UPDATE REIMBURSEMENTREQUESTS "
            		+ "SET MANAGER = '" + managerName + "', STATUS = 'Denied'"
            		+ "WHERE REQUESTNUMBER = " + requestNumber;
		String failMessage = "Failed to approve request in database";
        
		sendSQLquery(sql, failMessage);
	}
	
	//Updates the specified employees info with the values given
	public void updateEmployeeInfo(int employeeNumber, String name, String city, String state) {
        String sql = "UPDATE EMPLOYEES "
        		+ "SET NAME = '" + name + "', CITY = '" + city + "', STATE = '" + state + "' "
        		+ "WHERE EMPLOYEENUMBER = " + employeeNumber;
        String failMessage = "Failed to update employee info in database";
	
        sendSQLquery(sql, failMessage);
	}
	
	//Returns map of all employees
	public Map<Integer, Employee> getAllEmployees(){		
		String sql = "";
		Map<Integer, Employee> employees = null;
        
        try(Connection connection = DBconnection.getConnection()) {
            sql = "SELECT EMPLOYEENUMBER, NAME, SALARY, CITY, STATE, ISMANAGER FROM EMPLOYEES";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            employees = new HashMap<Integer, Employee>();
            while(rs.next()) {
                int employeeNumber = rs.getInt("EMPLOYEENUMBER");
                String name = rs.getString("NAME");
                double salary = rs.getDouble("SALARY");
                String city = rs.getString("CITY");
                String state = rs.getString("STATE");
                boolean isManager = (rs.getInt("ISMANAGER") >= 1);
                
                Employee e = new Employee(employeeNumber, name, salary, city, state, isManager);
                
                employees.put(employeeNumber, e);
            }
        } catch(Exception e) {
            System.out.println("Failed to obtain employee info from database " + e.getMessage());
        }

        return employees;
	}
	
	//Returns a map with all login info
	public Map<String, LoginInfo> getLoginInfo(){
		String sql = "";
		Map<String, LoginInfo> loginInfo = null;
        
        try(Connection connection = DBconnection.getConnection()) {
            sql = "SELECT EMPLOYEENUMBER, USERNAME, PASSWORD FROM LOGIN";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            loginInfo = new HashMap<String, LoginInfo>();
            while(rs.next()) {
                int employeeNumber = rs.getInt("EMPLOYEENUMBER");
                String username = rs.getString("USERNAME");
                String password = rs.getString("PASSWORD");
                
                LoginInfo l = new LoginInfo(employeeNumber, username, password);
                
                loginInfo.put(username, l);
            }
        } catch(Exception e) {
            System.out.println("Failed to obtain login info from database " + e.getMessage());
        }

        return loginInfo;
	}
	
	//Returns a map with all reimbursement requests
	public Map<Integer, ReimbursementRequest> getAllRequests(){
		String sql = "";
		Map<Integer, ReimbursementRequest> requests = null;
        
        try(Connection connection = DBconnection.getConnection()) {
            sql = "SELECT EMPLOYEENUMBER, REQUESTNUMBER, AMOUNT, DESCRIPTION, STATUS, MANAGER FROM REIMBURSEMENTREQUESTS";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            requests = new HashMap<Integer, ReimbursementRequest>();
            while(rs.next()) {
                int employeeNumber = rs.getInt("EMPLOYEENUMBER");
                int requestNumber = rs.getInt("REQUESTNUMBER");
                double amount = rs.getDouble("AMOUNT");
                String description = rs.getString("DESCRIPTION");
                String status = rs.getString("STATUS");
                String manager = rs.getString("MANAGER");
                
                ReimbursementRequest r = new ReimbursementRequest(employeeNumber, requestNumber, amount, description, status, manager);
                
                requests.put(requestNumber, r);
            }
        } catch(Exception e) {
            System.out.println("Failed to obtain login info from database " + e.getMessage());
        }

        return requests;
	}
	
	//Adds a reimbursement request to the database 
	public void addRequest(int employeeNumber, double amount, String description) {
		String sql = "";
        
        try(Connection connection = DBconnection.getConnection()) {
            sql = "INSERT INTO REIMBURSEMENTREQUESTS (EMPLOYEENUMBER, REQUESTNUMBER, AMOUNT, DESCRIPTION, STATUS, MANAGER) "
            		+ "VALUES (" + employeeNumber
            		+ ", " + generateRequestNumber()
            		+ ", " + amount
            		+ ", '" + description + "'" 
            		+ ", 'Pending'" 
            		+ ", 'N/A')";
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        } catch(Exception e) {
            System.out.println("Failed to add reimbursement request to database" + e.getMessage());
        }
	}
	
	//Generates a new employee number for a new user
	public int generateEmployeeNumber() {
		String sql = "";
        
        try(Connection connection = DBconnection.getConnection()) {
            sql = "SELECT MAX(EMPLOYEENUMBER) FROM EMPLOYEES";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
            	return rs.getInt(1) + 1;
            }
        } catch(Exception e) {
            System.out.println("Failed to generate new employee number " + e.getMessage());
        }
        
        return -1;
	}
	
	//Generates a new request number for a recently submitted request
	public int generateRequestNumber() {
		String sql = "";
		
        try(Connection connection = DBconnection.getConnection()) {
            sql = "SELECT MAX(REQUESTNUMBER) FROM REIMBURSEMENTREQUESTS";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
            	return rs.getInt(1) + 1;
            }
        } catch(Exception e) {
            System.out.println("Failed to generate new employee number " + e.getMessage());
        }
        
        return -1;
	}
}