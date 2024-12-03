package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		} catch (ClassNotFoundException | SQLException e) {
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
		
		String selecao = "select * from usuario u join voluntario v on u.id = v.usuario_id";
		
		try(PreparedStatement ps = Conexao.criarConexao().prepareStatement(selecao);
			ResultSet rs = ps.executeQuery()) {
			voluntarios = new ArrayList<>();

			while(rs.next()) {
				Voluntario voluntario = new Voluntario();
				
				voluntario.setId(rs.getInt("id"));
			    voluntario.setNome(rs.getString("nome"));
			    voluntario.setEmail(rs.getString("email"));
			    // voluntario.setFoto(rs.getString("foto")); 
			    voluntario.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
			    voluntario.setCidade(rs.getString("cidade"));
			    voluntario.setEstado(rs.getString("estado"));
			    voluntario.setDescricao(rs.getString("descricao"));
			    voluntario.setStatusConta(rs.getString("status_conta"));
			    voluntario.setExperiencia(rs.getString("experiencia"));
			    voluntario.setHabilidades(rs.getString("habilidades"));
			    voluntario.setAvaliacaoMedia(rs.getDouble("avaliacao_media"));
			    
			    voluntarios.add(voluntario);
			}			
		} catch (ClassNotFoundException | SQLException e) {
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
				//voluntario.setFoto(rs.getString("foto"));
				voluntario.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
				voluntario.setDataCadastro(rs.getDate("data_cadastro").toLocalDate());
				voluntario.setCidade(rs.getString("cidade"));
				voluntario.setEstado(rs.getString("estado"));
				voluntario.setDescricao(rs.getString("descricao"));
				voluntario.setAvaliacaoMedia(rs.getDouble("avaliacao_media"));
				voluntario.setStatusConta(rs.getString("status_conta"));
				voluntario.setTipo(rs.getString("tipo"));
				voluntario.setExperiencia(rs.getString("experiencia"));
				voluntario.setHabilidades(rs.getString("habilidades"));
				
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
				if (conexao != null)
					conexao.close();			
			} catch (SQLException e2) {
				System.err.println(e2);
			}
		}
		return false;
	}
	
	public void alterarDadosPerfil(Voluntario voluntario) {
		String alteracaoUsuario = "update usuario set nome = ?, email = ?, data_nascimento = ?,"
				+ " cidade = ?, estado = ?, descricao = ? where id = ?";
		
		String alteracaoVoluntario = "update voluntario set experiencia = ?, habilidades = ? "
				+ "where usuario_id = ?";
		
		Connection conexao = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		
		try {
			conexao = Conexao.criarConexao();
			ps = conexao.prepareStatement(alteracaoUsuario);
			ps.setString(1, voluntario.getNome());
			ps.setString(2, voluntario.getEmail());
			ps.setDate(3, Date.valueOf(voluntario.getDataNascimento()));
			ps.setString(4, voluntario.getCidade());
			ps.setString(5, voluntario.getEstado());
			ps.setString(6, voluntario.getDescricao());
			ps.setInt(7, voluntario.getId());
			ps.executeUpdate();
			
			ps2 = conexao.prepareStatement(alteracaoVoluntario);
			ps2.setString(1, voluntario.getExperiencia());
			ps2.setString(2, voluntario.getHabilidades());
			ps2.setInt(3, voluntario.getId());
			
			ps2.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
			
		} finally {
			try {
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
	
	public List<Voluntario> selecionarCandidatosDeUmaAtividade(int atividadeId) {
		String consulta = "select u.id, u.nome "
						+ "from atividade_has_voluntario as ahv "
						+ "join usuario as u on ahv.voluntario_usuario_id = u.id "
						+ "where ahv.atividade_id = ?";

		Connection conexao = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Voluntario> candidatos = null;

		try {
			conexao = Conexao.criarConexao();
			ps = conexao.prepareStatement(consulta);
			ps.setInt(1, atividadeId);
			rs = ps.executeQuery();

			candidatos = new ArrayList<Voluntario>();
			
			while (rs.next()) {
				int voluntarioId = rs.getInt("id");
				String nome = rs.getString("nome");
				Voluntario voluntario = new Voluntario();
				voluntario.setId(voluntarioId);
				voluntario.setNome(nome);
				
				candidatos.add(voluntario);
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conexao != null)
					conexao.close();
			} catch (SQLException e2) {
				System.err.println(e2);
			}
		}

		return candidatos;
	}
}