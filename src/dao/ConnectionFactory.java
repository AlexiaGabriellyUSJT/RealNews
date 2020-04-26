package dao;

import java.sql.*;

public class ConnectionFactory {
	
	public static Connection getConexao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost/portal_realnews?useTimezone=true&serverTimezone=America/Sao_Paulo&useSSL=false", "root", "");
		} catch(Exception e) {
			System.err.println("Falha: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
