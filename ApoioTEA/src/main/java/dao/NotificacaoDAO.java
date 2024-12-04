package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Notificacao;

public class NotificacaoDAO {
	public void inserirNotificacao(String descricao, int usuarioId) {
		String insercao = "insert into notificacao (descricao, usuario_id) values (?,?)";
		
		try (Connection conexao = Conexao.criarConexao();
			PreparedStatement ps = conexao.prepareStatement(insercao)) {
			ps.setString(1, descricao);
			ps.setInt(2, usuarioId);
			ps.executeUpdate();
			
			System.out.println("Notificação salva");
			
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
		}
	}
	
	public List<Notificacao> selecionarNotificacoesDeUmUsuario(int usuarioId){
		String consulta = "select * from notificacao where usuario_id = ? "
						+ "order by data desc";
		
		Connection conexao = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Notificacao> notificacoes = new ArrayList<Notificacao>();
		
		try {
			conexao = Conexao.criarConexao();
			ps = conexao.prepareStatement(consulta);
			ps.setInt(1, usuarioId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Notificacao notificacao = new Notificacao();
				notificacao.setDescricao(rs.getString("descricao"));
				notificacao.setData(rs.getDate("data").toLocalDate());
				
				notificacoes.add(notificacao);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
		}
		
		return notificacoes;
	}
}