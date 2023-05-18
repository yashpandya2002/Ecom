package com.util;

import java.sql.Connection;

import java.sql.DriverManager;


public class DbConnection {
	static String url="jdbc:mysql://localhost:3306/ecom";
	static String userName="root";
	static String password="root";
	static String driverClass="com.mysql.cj.jdbc.Driver";
	
	public static Connection getConnection() {
		try {
			Class.forName(driverClass);
			Connection con= DriverManager.getConnection(url,userName,password);
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
