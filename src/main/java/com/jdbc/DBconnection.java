package com.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBconnection {
	public static Connection getConnection() {
		Properties props = new Properties();
		FileInputStream fileIn = null;
		Connection connection = null;
		
		try {
			fileIn = new FileInputStream("C:\\Users\\merli\\eclipse-workspace\\ExpenseReimbursementProject\\db.properties");
			props.load(fileIn);

			// load the Driver Class
			Class.forName(props.getProperty("DB_DRIVER_CLASS"));

			// create the connection now
			connection = DriverManager.getConnection(props.getProperty("DB_URL"),
					props.getProperty("DB_USERNAME"),
					props.getProperty("DB_PASSWORD"));
		} catch (IOException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Database Connection Failed");
			e.printStackTrace();
		}
		
		return connection;
	}
}
