package com.madeiramadeira.uteis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbconnection {

	private static String dbUrl;
	private static String username;
	private static String password;
	private static String dbClass;
	
	
	/**
	 * CLASSE CONEXÃO BD PARA EXECUTAR O DELETE DOS DADOS CADASTRADOS.
	 * 
	 */

	static {

		dbUrl = "jdbc:mysql://dw.madeiramadeira.com.br/bi_dw?useTimezone=true&serverTimezone=UTC"; 
		username = "ti"; // Default username is root
		password = "pak-wret8UBr"; // Default password is root
		dbClass = "com.mysql.jdbc.Driver";
	}
	
	public static Connection get(){
		Connection con = null;
		
		try {
			Class.forName(dbClass);
			con = DriverManager.getConnection(dbUrl, username, password);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}

}
