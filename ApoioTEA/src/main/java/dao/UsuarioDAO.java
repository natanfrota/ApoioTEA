package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Usuario;

public class UsuarioDAO {
	public boolean buscarEmailESenha(Usuario usuario) {
		String consulta = "select id, email, senha, tipo from usuario "
				+ "where email = ? and senha = ? and status_conta = 'ativa'";

		Connection conexao = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	
		try {
			conexao = Conexao.criarConexao();
			ps = conexao.prepareStatement(consulta);
			ps.setString(1, usuario.getEmail());
			ps.setString(2, usuario.getSenha());
			rs = ps.executeQuery();

			if(rs.next()) {
				usuario.setTipo(rs.getString("tipo"));
				usuario.setId(rs.getInt("id"));
				return true;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
			
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(ps != null)
					ps.close();
				if(conexao != null)
					conexao.close();
					
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
		return false;
	}
	
	public void alterarDadosPerfilUsuario(Usuario usuario) {
		String alteracao = "update usuario set nome = ?, email = ?, cidade = ?, "
				+ "estado = ?, descricao = ? where id = ?";
		
		Connection conexao = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conexao = Conexao.criarConexao();
			ps = conexao.prepareStatement(alteracao);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getEmail());
			ps.setString(3, usuario.getCidade());
			ps.setString(4, usuario.getEstado());
			ps.setString(5, usuario.getDescricao());
			ps.setInt(6, usuario.getId());
			ps.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
			
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(ps != null)
					ps.close();
				if (conexao != null)
					conexao.close();			
			} catch (SQLException e2) {
				System.err.println(e2);
			}
		}
	}
	
	public void excluirConta(Usuario usuario) {
		String exclusao = "update usuario set status_conta = 'excluida' "
				+ "where id = ?";
		Connection conexao = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conexao = Conexao.criarConexao();
			ps = conexao.prepareStatement(exclusao);
			ps.setInt(1, usuario.getId());
			ps.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
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
	}
}