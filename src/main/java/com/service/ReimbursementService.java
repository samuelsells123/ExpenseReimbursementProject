package com.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jdbc.DBconnection;
import com.models.*;

public class ReimbursementService {
	public ReimbursementService() {
		
	}
	
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
            return employees;


        } catch(Exception e) {
            System.out.println("Failed to obtain employee info from database " + e.getMessage());
        }

        return employees;
	}
	
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
            return loginInfo;


        } catch(Exception e) {
            System.out.println("Failed to obtain login info from database " + e.getMessage());
        }

        return loginInfo;
	}
	
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
            return requests;


        } catch(Exception e) {
            System.out.println("Failed to obtain login info from database " + e.getMessage());
        }

        return requests;
	}
	
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
	
	public void updateEmployeeInfo(int employeeNumber, String name, String city, String state) {
		String sql = "";
        
        try(Connection connection = DBconnection.getConnection()) {
            sql = "UPDATE EMPLOYEES "
            		+ "SET NAME = '" + name + "', CITY = '" + city + "', STATE = '" + state + "' "
            		+ "WHERE EMPLOYEENUMBER = " + employeeNumber;
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        } catch(Exception e) {
            System.out.println("Failed to update employee info in database " + e.getMessage());
        }
	}
	
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
	
	public void approve(int requestNumber, String managerName) {
		String sql = "";
        
        try(Connection connection = DBconnection.getConnection()) {
            sql = "UPDATE REIMBURSEMENTREQUESTS "
            		+ "SET MANAGER = '" + managerName + "', STATUS = 'APPROVED'"
            		+ "WHERE REQUESTNUMBER = " + requestNumber;
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        } catch(Exception e) {
            System.out.println("Failed to approve request in database " + e.getMessage());
        }
	}
	
	public void deny(int requestNumber, String managerName) {
		String sql = "";
        
        try(Connection connection = DBconnection.getConnection()) {
            sql = "UPDATE REIMBURSEMENTREQUESTS "
            		+ "SET MANAGER = '" + managerName + "', STATUS = 'DENIED'"
            		+ "WHERE REQUESTNUMBER = " + requestNumber;
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        } catch(Exception e) {
            System.out.println("Failed to approve request in database " + e.getMessage());
        }
	}
}