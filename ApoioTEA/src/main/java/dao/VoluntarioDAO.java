package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelo.Voluntario;

public class VoluntarioDAO {
	public boolean inserirVoluntario(Voluntario voluntario) {
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
			
			return true;
		} catch (SQLException e) {
			if (conexao != null) {
				try {
					conexao.rollback();
				} catch (SQLException e2) {
					System.err.println(e2);
				}
			}
			return false;
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
		List<Voluntario> voluntarios = null;
		
		String selecao = "select * from usuario u "
				+ "join voluntario v on u.id = v.usuario_id";
		
		try(PreparedStatement ps = Conexao.criarConexao().prepareStatement(selecao);
			ResultSet rs = ps.executeQuery()) {
			voluntarios = new ArrayList<>();
			System.out.println("dentro de voluntarioDAO");
			while(rs.next()) {
				
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String senha = rs.getString("senha");
				String foto = rs.getString("foto");
				LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();
				LocalDate dataCadastro = rs.getDate("data_cadastro").toLocalDate();
				String cidade = rs.getString("cidade");
				String estado = rs.getString("estado");
				String descricao = rs.getString("descricao");
				String statusConta = rs.getString("statusConta");
				String tipo = rs.getString("tipo");
				String experiencia = rs.getString("experiencia");
				String habilidade = rs.getString("habilidades");
				
				voluntarios.add(new Voluntario(id, nome, email, senha, dataNascimento, cidade, estado, null));
			}			
		} catch (SQLException e) {
			System.err.println(e);
		}
		
		return voluntarios;
	}
}
