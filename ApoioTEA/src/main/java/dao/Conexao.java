package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private final static String URL = "jdbc:mysql://localhost/apoio_tea";
	private final static String USUARIO = "root";
	private final static String SENHA = "";
	private static Connection con;
	
	public static Connection criarConexao() throws SQLException, ClassNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(URL, USUARIO, SENHA);
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			throw e;
		}
		
		return con;
	}
}
