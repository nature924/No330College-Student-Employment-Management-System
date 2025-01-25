package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnDB {

	private static String username = "root";
	
	private static String password = "root";
	
	private static String url =  "jdbc:mysql://localhost:3306/employ?useUnicode=true&characterEncoding=utf-8";
	
	private static Connection conn;
	
	
	public static Connection getConn(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConnDB cd = new ConnDB();
		cd.getConn();
	}

}
