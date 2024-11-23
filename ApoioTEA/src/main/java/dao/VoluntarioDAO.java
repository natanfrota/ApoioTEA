package dao;

import java.sql.Connection;
import java.sql.Date;
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
				+ "estado, tipo, data_nascimento, descricao) values (?,?,?,?,?,?,?,?)";
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
			ps.setString(6, voluntario.getTipo());
			ps.setDate(7, Date.valueOf(voluntario.getDataNascimento()));
			ps.setString(8, voluntario.getDescricao());

			int linhas = ps.executeUpdate();
			System.out.println("Linhas alteradas com ps: " + linhas);

			rs = ps.getGeneratedKeys();
			rs.next();
			int idUsuario = rs.getInt(1);

			ps2 = conexao.prepareStatement(insercaoVoluntario);
			ps2.setInt(1, idUsuario);
			ps2.setString(2, voluntario.getExperiencia());
			ps2.setString(3, voluntario.getHabilidades());
			linhas = ps2.executeUpdate();
			System.out.println("Linhas alteradas com ps2: " + linhas);
			
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
				+ "join voluntario v on u.id = v.usuario_id where status_conta = 'ativa'";
		
		try(PreparedStatement ps = Conexao.criarConexao().prepareStatement(selecao);
			ResultSet rs = ps.executeQuery()) {
			voluntarios = new ArrayList<>();

			while(rs.next()) {
				
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				//String foto = rs.getString("foto");
				LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();
				String cidade = rs.getString("cidade");
				String estado = rs.getString("estado");
				String descricao = rs.getString("descricao");
				String statusConta = rs.getString("status_conta");
				String experiencia = rs.getString("experiencia");
				String habilidades = rs.getString("habilidades");
				
				voluntarios.add(new Voluntario(id, nome, email, dataNascimento, cidade, 
						estado, descricao, statusConta, experiencia, habilidades, id));
			}			
		} catch (SQLException e) {
			System.err.println(e);
		}
		
		return voluntarios;
	}
	
	public boolean selecionarVoluntario(Voluntario voluntario) {
		String selecaoVoluntario = "select * from usuario u join voluntario v "
								  + "on u.id = v.usuario_id "
								  + "where u.id = ? and status_conta = 'ativa'";

		Connection conexao = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conexao = Conexao.criarConexao();
			ps = conexao.prepareStatement(selecaoVoluntario);
			ps.setInt(1, voluntario.getId());
			rs = ps.executeQuery();
			if(rs.next()) {			
				voluntario.setNome(rs.getString("nome"));
				voluntario.setEmail(rs.getString("email"));
				//voluntario.setSenha(rs.getString("senha"));
				//voluntario.setFoto(rs.getString("foto"));
				voluntario.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
				voluntario.setDataCadastro(rs.getDate("data_cadastro").toLocalDate());
				voluntario.setCidade(rs.getString("cidade"));
				voluntario.setEstado(rs.getString("estado"));
				voluntario.setDescricao(rs.getString("descricao"));
				voluntario.setStatusConta(rs.getString("status_conta"));
				voluntario.setTipo(rs.getString("tipo"));
				voluntario.setExperiencia(rs.getString("experiencia"));
				voluntario.setHabilidades(rs.getString("habilidades"));
				
				return true;
			}
			
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
		return false;
	}
	
	public void alterarDadosPerfil(Voluntario voluntario) {
		String alteracao = "update voluntario set experiencia = ?, habilidades = ? "
				+ "where usuario_id = ?";
		
		Connection conexao = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
			
		try {
			conexao = Conexao.criarConexao();
			ps = conexao.prepareStatement(alteracao);
			ps.setString(1, voluntario.getExperiencia());
			ps.setString(2, voluntario.getHabilidades());
			ps.setInt(3, voluntario.getId());
			
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