package dao;

import modelo.Conversa;
import modelo.Mensagem;
import modelo.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MensagemDAO {
	public void criarMensagem(Mensagem mensagem) {
        String insercao = "insert into mensagem (conteudo, conversa_id, usuario_id) VALUES (?, ?, ?)";
        try (Connection conexao = Conexao.criarConexao();
        	PreparedStatement ps = conexao.prepareStatement(insercao)){
            ps.setString(1, mensagem.getConteudo());
            ps.setInt(2, mensagem.getConversa().getId());
            ps.setInt(3, mensagem.getRemetente().getId());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
        }
    }
	
    public List<Mensagem> selecionarMensagens(Conversa conversa){
        String consulta = "select * from mensagem where conversa_id = ? "
        				+ "order by dataHoraDeEnvio";
        
        List<Mensagem> mensagens = new ArrayList<>();
        
        Connection conexao = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
        
        try {
        	conexao = Conexao.criarConexao();
        	ps = conexao.prepareStatement(consulta); 
            ps.setInt(1, conversa.getId());
            rs = ps.executeQuery();
            
            Usuario u1 = conversa.getUsuario1();
            Usuario u2 = conversa.getUsuario2();
            
            while (rs.next()) {
                Mensagem mensagem = new Mensagem();
                mensagem.setId(rs.getInt("id"));
                mensagem.setConteudo(rs.getString("conteudo"));
                mensagem.setDataHoraDeEnvio(rs.getTimestamp("dataHoraDeEnvio").toLocalDateTime());
                int usuarioId = rs.getInt("usuario_id");
                
                if(usuarioId == u1.getId()) {
                	mensagem.setRemetente(u1);
                } else {
                	mensagem.setRemetente(u2);
                }
                
                mensagens.add(mensagem);
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
        return mensagens;
    } 
    
}
