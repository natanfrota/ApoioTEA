package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModeradorDAO {
	public boolean selecionarModerador(String email, String senha) {
		String consulta = "select * from moderador where email = ? and senha = ?";
		
		Connection conexao = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conexao = Conexao.criarConexao();
			ps = conexao.prepareStatement(consulta);
			ps.setString(1, email);
			ps.setString(2, senha);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			
		} catch(ClassNotFoundException | SQLException e) {
			System.err.println(e);
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(ps != null)
					ps.close();
				if(conexao != null)
					conexao.close();
				
			} catch (SQLException e2) {
				System.err.println(e2);
			}
		}
		
		return false;
	}
}
