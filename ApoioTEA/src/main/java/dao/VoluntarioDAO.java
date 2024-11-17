package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import modelo.Voluntario;

public class VoluntarioDAO {
	private List<Voluntario> voluntarios;

	public void inserirVoluntario(Voluntario voluntario) {
		String insercaoUsuario = "insert into usuario (nome, email, senha, cidade, "
				+ "estado, tipo) values (?,?,?,?,?,?)";
		String insercaoVoluntario = "insert into voluntario (usuario_id, experiencia, "
				+ "habilidades) values (?, ?, ?)";

		Connection conexao = null;
		PreparedStatement ps = null, ps2 = null;
		ResultSet rs = null;

		try {
			conexao = Conexao.criarConexao();
			conexao.setAutoCommit(false);

			ps = conexao.prepareStatement(insercaoUsuario,
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, voluntario.getNome());
			ps.setString(2, voluntario.getEmail());
			ps.setString(3, voluntario.getSenha());
			ps.setString(4, voluntario.getCidade());
			ps.setString(5, voluntario.getEstado());
			ps.setString(6, "voluntário");
			System.out.println("Dados inseridos na tabelas usuário.");

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			rs.next();
			int idUsuario = rs.getInt(1);

			ps2 = conexao.prepareStatement(insercaoVoluntario);
			ps2.setInt(1, idUsuario);
			ps2.setString(2, voluntario.getExperiencia());
			ps2.setString(3, voluntario.getHabilidades());
			ps2.executeUpdate();
			System.out.println("Dados inseridos na tabelas voluntário.");

			conexao.commit();
			conexao.setAutoCommit(true);

		} catch (SQLException e) {
			if (conexao != null) {
				try {
					conexao.rollback();
				} catch (SQLException e2) {
					System.err.println(e2);
				}
			}
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(ps2 != null)
					ps2.close();
				if(ps != null)
					ps.close();
				if (conexao != null)
					conexao.close();			
			} catch (SQLException e2) {
				System.err.println(e2);
			}
		}
	}

	public List<Voluntario> selecionarVoluntarios() {
		return voluntarios;
	}
}
