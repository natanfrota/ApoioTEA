package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Usuario;
import modelo.Voluntario;

public class UsuarioDAO {
	public Usuario buscarEmailESenha(Usuario usuario) {
		String consulta = "select email, senha from usuario where email = ? and senha = ?";
		Usuario usuario1 = null;
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
				//System.out.println("dentro de usuarioDAO");
				usuario1 = new Usuario();
				usuario1.setEmail(rs.getString("email"));
				usuario1.setSenha(rs.getString("senha"));
			}
			
		} catch (SQLException e) {
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

		return usuario1;
	}
	
	public void alterarDadosPerfilUsuario(Usuario usuario) {
		String alteracao = "update usuario set nome = ?, cidade = ?, estado = ?, "
				+ "descricao = ? where id = ?";
		
		Connection conexao = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conexao = Conexao.criarConexao();
			ps = conexao.prepareStatement(alteracao);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getCidade());
			ps.setString(3, usuario.getEstado());
			ps.setString(4, usuario.getDescricao());
			ps.setInt(5, usuario.getId());
			
			ps.executeUpdate();

		} catch (SQLException e) {
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
}

