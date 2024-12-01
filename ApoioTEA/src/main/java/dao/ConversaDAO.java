package dao;

import modelo.Conversa;
import modelo.Mensagem;
import modelo.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConversaDAO {
    public void criarConversa(int idUsuario1, int idUsuario2) {
    	String insercao1 = "insert into conversa (id) values (default)";
    	String insercao2 = "insert into usuario_has_conversa (usuario_id, conversa_id)"
    					+ " values (?, ?)";
    	String insercao3 = "insert into usuario_has_conversa (usuario_id, conversa_id) "
    					+ "values (?, ?)";
    	
    	Connection conexao = null;
    	PreparedStatement ps1 = null;
    	PreparedStatement ps2 = null; 
    	PreparedStatement ps3 = null;
    	ResultSet rs = null;
    	
    	try {
    		conexao = Conexao.criarConexao();
    		conexao.setAutoCommit(false);
    		
    		ps1 = conexao.prepareStatement(insercao1, PreparedStatement.RETURN_GENERATED_KEYS);
    		ps1.executeUpdate();
    		
    		rs = ps1.getGeneratedKeys();
    		rs.next();
    		int idGerado = rs.getInt(1);
   			
    		ps2 = conexao.prepareStatement(insercao2);
    		ps2.setInt(1, idUsuario1);
    		ps2.setInt(2, idGerado);
    		ps2.executeUpdate();
    		
    		ps3 = conexao.prepareStatement(insercao3);
    		ps3.setInt(1, idUsuario2);
    		ps3.setInt(2, idGerado);
    		ps3.executeUpdate();
    		
    		conexao.commit();
    		conexao.setAutoCommit(true);
    		
    	} catch (ClassNotFoundException | SQLException e) {
    		System.err.println(e);
    		
    		try {
    			if(conexao != null)
    				conexao.rollback();
			} catch (SQLException e2) {
				System.err.println(e2);
			}
    		
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(ps3 != null)
					ps3.close();
				if(ps2 != null)
					ps2.close();
				if(ps1 != null)
					ps1.close();
				if(conexao != null)
					conexao.close();
				
			} catch (SQLException e2) {
				System.err.println(e2);
			}
		}
    }
    
    public List<Conversa> selecionarConversasDeUmUsuario(Usuario usuario){
    	String consulta = "select u.id as usuario_id, u.nome as usuario_nome, u_c.conversa_id "
    					+ "from usuario u join usuario_has_conversa u_c "
    					+ "on u.id = u_c.usuario_id join usuario_has_conversa u_c2 "
    					+ "on u_c.conversa_id = u_c2.conversa_id where u_c2.usuario_id = ?";
    	
    	List<Conversa> conversas = new ArrayList<>();
    	
    	Connection conexao = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	
    	try {
    		conexao = Conexao.criarConexao();
    		
    		
    		
    		
    	} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
		}
    }
}
