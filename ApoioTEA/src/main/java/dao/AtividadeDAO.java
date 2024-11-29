package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import modelo.Atividade;
import modelo.Familia;
import modelo.Voluntario;

public class AtividadeDAO {
	public boolean inserirAtividade(Atividade atividade) {
		String insercao = "insert into atividade (titulo, categoria, descricao, "
				+ "localizacao, status, data, hora, familia_usuario_id) "
				+ "values (?,?,?,?,?,?,?,?)";
		
		Connection conexao = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conexao = Conexao.criarConexao();
			ps = conexao.prepareStatement(insercao, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, atividade.getTitulo());
			ps.setString(2, atividade.getCategoria());
			ps.setString(3, atividade.getDescricao());
			ps.setString(4, atividade.getLocalizacao());
			ps.setString(5, "aberta");
			ps.setDate(6, Date.valueOf(atividade.getData()));
			ps.setTime(7, Time.valueOf(atividade.getHora()));;
			ps.setInt(8, atividade.getFamilia().getId());
			
			int linhas = ps.executeUpdate();
			System.out.println("Linhas: " + linhas);
			
			rs = ps.getGeneratedKeys();
			if(rs.next()) {
				int idGerado = rs.getInt(1);
				System.out.println("Id da nova atividade: " + idGerado);
				atividade.setId(idGerado);
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
			} catch (SQLException e2) {
				System.err.println(e2);
			}
		}
		
		return false;
	}
	
	//testar
	public List<Atividade> selecionarTodasAsAtividades(){
		String consulta = "select u.id as id_familia, u.nome as nome_familia, "
						+ "u2.id as id_voluntario, u2.nome as nome_voluntario, a.* "
						+ "from atividade a "
						+ "join usuario u on a.familia_usuario_id = u.id "
						+ "left join usuario u2 on a.voluntario_usuario_id = u2.id;";
		
		List<Atividade> atividades = new ArrayList<Atividade>();
		
		try(Connection conexao = Conexao.criarConexao();
			PreparedStatement ps = conexao.prepareStatement(consulta);
			ResultSet rs = ps.executeQuery()) {
			
			
			VoluntarioDAO voluntarioDAO = new VoluntarioDAO();
			
			while(rs.next()) {
				int idFamilia = rs.getInt("id_familia");
	            String nomeFamilia = rs.getString("nome_familia");
	            Familia familia = new Familia();
	            familia.setId(idFamilia);
	            familia.setNome(nomeFamilia);
				
	            int idVoluntario = rs.getInt("id_voluntario");
	            String nomeVoluntario = rs.getString("nome_voluntario");
	            Voluntario voluntario = null;
	            if (idVoluntario != 0) {
	                voluntario = new Voluntario();
	                voluntario.setId(idVoluntario);
	                voluntario.setNome(nomeVoluntario);
	            }
	            
				int idAtividade = rs.getInt("id");
				String titulo = rs.getString("titulo");
				String categoria = rs.getString("categoria");
				String descricao = rs.getString("descricao");
				String localizacao = rs.getString("localizacao");
				String status = rs.getString("status");
				LocalDate data = rs.getDate("data").toLocalDate();
				LocalTime hora = rs.getTime("hora").toLocalTime();
				
				Atividade atividade = new Atividade(idAtividade, titulo, categoria, descricao, 
						localizacao, status, data, hora);
				atividades.add(atividade);
				
				atividade.setFamilia(familia);
				
				if (voluntario != null) {
	                atividade.setVoluntarioEscolhido(voluntario);
	            }
				
				atividade.setVoluntariosCandidatos(voluntarioDAO.selecionarCandidatosDeUmaAtividade(idAtividade));
				
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
		}
		
		return atividades;
	}
	
	public boolean selecionarAtividade(Atividade atividade){
		String consulta = "select * from atividade where id = ?";
		
		Connection conexao = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			conexao = Conexao.criarConexao();
			ps = conexao.prepareStatement(consulta);
			ps.setInt(1, atividade.getId());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				atividade.setId(rs.getInt("id"));
				atividade.setTitulo(rs.getString("titulo"));
				atividade.setCategoria(rs.getString("categoria"));
				atividade.setDescricao(rs.getString("descricao"));
				atividade.setLocalizacao(rs.getString("localizacao"));
				atividade.setStatus(rs.getString("status"));
				atividade.setData(rs.getDate("data").toLocalDate());
				atividade.setHora(rs.getTime("hora").toLocalTime());
				
				return true;
			} 
			
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
		}
		
		return false;
	}
	
	//testar
	public List<Atividade> selecionarAtividadesDeUmaFamilia(int idFamilia){
		String consulta = "select u.id as id_voluntario, u.nome as nome_voluntario, a.* "
						+ "from atividade a "
						+ "left join usuario u on a.voluntario_usuario_id = u.id "
						+ "where a.familia_usuario_id = ?;";
		
		List<Atividade> atividades = new ArrayList<Atividade>();
		Connection conexao = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			conexao = Conexao.criarConexao();
			ps = conexao.prepareStatement(consulta);
			ps.setInt(1, idFamilia);
			rs = ps.executeQuery();
			
			VoluntarioDAO voluntarioDAO = new VoluntarioDAO();
			
			while(rs.next()) {
				int idVoluntario = rs.getInt("id_voluntario");
	            String nomeVoluntario = rs.getString("nome_voluntario");
	            Voluntario voluntario = null;
	            if (idVoluntario != 0) {
	                voluntario = new Voluntario();
	                voluntario.setId(idVoluntario);
	                voluntario.setNome(nomeVoluntario);
	            }
				
				int id = rs.getInt("id");
				String titulo = rs.getString("titulo");
				String categoria = rs.getString("categoria");
				String descricao = rs.getString("descricao");
				String localizao = rs.getString("localizacao");
				String status = rs.getString("status");
				LocalDate data = rs.getDate("data").toLocalDate();
				LocalTime hora = rs.getTime("hora").toLocalTime();
				
				Atividade atividade = new Atividade(id, titulo, categoria, descricao, 
						localizao, status, data, hora);
				atividades.add(atividade);
				
				if (voluntario != null) {
	                atividade.setVoluntarioEscolhido(voluntario);
	            }
				
				atividade.setVoluntariosCandidatos(voluntarioDAO.selecionarCandidatosDeUmaAtividade(id));
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
			} catch (SQLException e2) {
				System.err.println(e2);
			}
		}
		return atividades;
	}
	
	public boolean registrarCandidaturaDeUmVoluntario(int atividadeId, int voluntarioId) {
		String insercao = "insert into atividade_has_voluntario "
						+ "(atividade_id, voluntario_usuario_id) "
						+ "values (?,?)";
		
		try(Connection conexao = Conexao.criarConexao();
			PreparedStatement ps = conexao.prepareStatement(insercao)){
			ps.setInt(1, atividadeId);
			ps.setInt(2, voluntarioId);
			int linhas = ps.executeUpdate();
			
			if (linhas > 0) {
				System.out.println("linhas: " + linhas);
				return true;
			}
			
		} catch(ClassNotFoundException | SQLException e) {
			System.err.println(e);
		}
		
		return false;
	}
	
	public boolean removerCandidaturaDeUmaAtividade(int atividadeId, int voluntarioId) {
		String remocao = "delete from atividade_has_voluntario " 
					   + "where atividade_id = ? "
					   + "and voluntario_usuario_id = ?";

		try (Connection conexao = Conexao.criarConexao(); 
			 PreparedStatement ps = conexao.prepareStatement(remocao)) {
			ps.setInt(1, atividadeId);
			ps.setInt(2, voluntarioId);
			int linhas = ps.executeUpdate();

			if (linhas > 0) {
				System.out.println("linhas deletadas: " + linhas);
				return true;
			}

		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
		}

		return false;
	}
	
	// testar
	public void registrarVoluntarioEscolhido(int atividadeId, int voluntarioId) {
		String insercao = "insert into atividade (voluntario_usuario_id) values (?) where id = ?";
		
		Connection conexao = null;
		PreparedStatement ps = null;

		try {
			conexao = Conexao.criarConexao();
			ps = conexao.prepareStatement(insercao);
			ps.setInt(1, voluntarioId);
			ps.setInt(2, atividadeId);
			
			int linhas = ps.executeUpdate();
			if (linhas > 0)
				System.out.println("Voluntário aceito");

		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conexao != null)
					conexao.close();
			} catch (SQLException e2) {
				System.err.println(e2);
			}
		}
	}
	
	//testar
	public void removerVoluntarioEscolhido(int atividadeId) {
		String insercao = "update atividade set voluntario_usuario_id = ? where id = ?";
		
		Connection conexao = null;
		PreparedStatement ps = null;

		try {
			conexao = Conexao.criarConexao();
			ps = conexao.prepareStatement(insercao);
			ps.setNull(1, java.sql.Types.INTEGER);
			ps.setInt(2, atividadeId);
			
			int linhas = ps.executeUpdate();
			if (linhas > 0)
				System.out.println("Voluntário aceito");

		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conexao != null)
					conexao.close();
			} catch (SQLException e2) {
				System.err.println(e2);
			}
		}
	}
	
	public void editarAtividade(Atividade atividade) {
		String edicao = "update atividade set titulo = ?, categoria = ?, "
				+ "descricao = ?, localizacao = ?, status = ?, " + "data = ?, hora = ? where id = ?";

		Connection conexao = null;
		PreparedStatement ps = null;

		try {
			conexao = Conexao.criarConexao();
			ps = conexao.prepareStatement(edicao);
			ps.setString(1, atividade.getTitulo());
			ps.setString(2, atividade.getCategoria());
			ps.setString(3, atividade.getDescricao());
			ps.setString(4, atividade.getLocalizacao());
			ps.setString(5, atividade.getStatus());
			ps.setDate(6, Date.valueOf(atividade.getData()));
			ps.setTime(7, Time.valueOf(atividade.getHora()));
			ps.setInt(8, atividade.getId());

			int linhas = ps.executeUpdate();
			System.out.println("Linhas atualizadas: " + linhas);

		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conexao != null)
					conexao.close();
			} catch (SQLException e2) {
				System.err.println(e2);
			}
		}
	}
	
	
	public boolean excluirAtividade(int id) {
		String exclusao = "delete from atividade where id = ?";
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = Conexao.criarConexao();
			ps = con.prepareStatement(exclusao);
			ps.setInt(1, id);
			
			int linhas = ps.executeUpdate();
			
			if(linhas > 0)
				return true;
			
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
		} finally {
			try {
				if(ps != null)
					ps.close();
				if(con != null)
					con.close();
			} catch (SQLException e2) {
				System.err.println(e2);
			}
		}
		
		return false;
	}
}
