package dao;

import java.sql.*;

public class ConnectionFactory {
	
	public static Connection getConexao() {
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String serverName = "portal_realnews";			 
	        String mydatabase ="mysql";	 
	        String url = "jdbc:mysql://" + serverName + "/" + mydatabase;	 
	        String username = "root";      	 
	        String password = "";
	 
	        connection = DriverManager.getConnection(url, username, password);
	        
		} catch(Exception e) {
			System.err.println("Erro: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return connection;
	}
}
