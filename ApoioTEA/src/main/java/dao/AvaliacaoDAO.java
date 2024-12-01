package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Avaliacao;
import modelo.Familia;

public class AvaliacaoDAO {
	public void inserirAvalicao(Avaliacao avaliacao, int familiaId, int voluntarioId) {
		String insercao = "insert into avaliacao (nota, comentario, familia_usuario_id, voluntario_usuario_id) "
						+ "values (?, ?, ?, ?)";
		
		try(Connection conexao = Conexao.criarConexao();
			PreparedStatement ps = conexao.prepareStatement(insercao)) {
	        ps.setInt(1, avaliacao.getNota());
            ps.setString(2, avaliacao.getComentario());
            ps.setInt(3, familiaId);
            ps.setInt(4, voluntarioId);
            ps.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
		}
	}
	
	public List<Avaliacao> selecionarAvaliacoesDeUmVoluntario(int voluntarioId){
		String consulta = "select u.id as familia_id, u.nome as familia_nome, a.* "
						+ "from avaliacao a "
						+ "join usuario u on a.familia_usuario_id = u.id "
						+ "where voluntario_usuario_id = ?";
		
		List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
		Connection conexao = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conexao = Conexao.criarConexao();
			ps = conexao.prepareStatement(consulta);
			ps.setInt(1, voluntarioId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int familiaId = rs.getInt("familia_id");
	            String familiaNome = rs.getString("familia_nome");
	            Familia familia = new Familia();
	            familia.setId(familiaId);
	            familia.setNome(familiaNome);
	            
	            Avaliacao avaliacao = new Avaliacao();
	            avaliacao.setId(rs.getInt("id"));
	            avaliacao.setNota(rs.getInt("nota"));
	            avaliacao.setComentario(rs.getString("comentario"));
	            avaliacao.setData(rs.getDate("data").toLocalDate());
	            avaliacao.setFamilia(familia);
	            
	            avaliacoes.add(avaliacao);
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
		return avaliacoes;
	}
}
