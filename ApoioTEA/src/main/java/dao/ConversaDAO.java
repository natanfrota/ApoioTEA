package dao;

import modelo.Conversa;
import modelo.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConversaDAO {
    public int criarConversa(int idUsuario1, int idUsuario2) {
    	String insercao1 = "insert into conversa (id) values (default)";
    	String insercao2 = "insert into usuario_has_conversa (usuario_id, conversa_id) "
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
			if (rs.next()) {
				int idGerado = rs.getInt(1);
				
				ps2 = conexao.prepareStatement(insercao2);
				ps2.setInt(1, idUsuario1);
				ps2.setInt(2, idGerado);
				ps2.executeUpdate();

				ps3 = conexao.prepareStatement(insercao2);
				ps3.setInt(1, idUsuario2);
				ps3.setInt(2, idGerado);
				ps3.executeUpdate();

				conexao.commit();
				conexao.setAutoCommit(true);
				
				return idGerado;
			}
    		
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
    	
    	return 0;
    }
    
    public List<Conversa> selecionarConversasDeUmUsuario(Usuario usuario1){
    	String consulta = "select u.id as usuario2_id, u.nome as usuario2_nome, u_c.conversa_id "
    					+ "from usuario u "
    					+ "join usuario_has_conversa u_c "
    					+ "on u.id = u_c.usuario_id "
    					+ "join usuario_has_conversa u_c2 "
    					+ "on u_c.conversa_id = u_c2.conversa_id "    		
    					+ "where u_c.usuario_id <> u_c2.usuario_id and "
    					+ "u_c2.usuario_id = ?";
    	
    	List<Conversa> conversas = new ArrayList<>();
    	
    	Connection conexao = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	
    	try {
    		conexao = Conexao.criarConexao();
    		ps = conexao.prepareStatement(consulta);
    		ps.setInt(1, usuario1.getId());
    		rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			Usuario usuario2 = new Usuario();
    			usuario2.setId(rs.getInt("usuario2_id"));
    			usuario2.setNome(rs.getString("usuario2_nome"));
    			
    			Conversa conversa = new Conversa();
    			conversa.setId(rs.getInt("conversa_id"));
    			
    			conversa.setUsuario1(usuario1);
    			conversa.setUsuario2(usuario2);
    			
    			conversas.add(conversa);
    		}
    		
    	} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
		}
    	
    	return conversas;
    }
    
    public void selecionarConversa(Conversa conversa) {
    	String consulta = "select u.id as usuario1_id, u.nome as usuario1_nome, "
    					+ "u2.id as usuario2_id, u2.nome as usuario2_nome, u_c.conversa_id "
    					+ "from usuario u "
    					+ "join usuario_has_conversa u_c on u.id = u_c.usuario_id "
    					+ "join usuario_has_conversa u_c2 on u_c.conversa_id = u_c2.conversa_id "
    					+ "join usuario u2 on u2.id = u_c2.usuario_id "
    					+ "where u.id <> u2.id and "
    					+ "u_c.conversa_id = ?";
    	
    	Connection conexao = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	
    	try {
    		conexao = Conexao.criarConexao();
    		ps = conexao.prepareStatement(consulta);
    		ps.setInt(1, conversa.getId());
    		rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			Usuario usuario1 = new Usuario();
    			usuario1.setId(rs.getInt("usuario1_id"));
    			usuario1.setNome(rs.getString("usuario1_nome"));
    			
    			Usuario usuario2 = new Usuario();
    			usuario2.setId(rs.getInt("usuario2_id"));
    			usuario2.setNome(rs.getString("usuario2_nome"));
    			
    			//System.out.println("Usuarios retornados: " + usuario1.getNome() + " " + usuario2.getNome());
    			
    			conversa.setUsuario1(usuario1);
    			conversa.setUsuario2(usuario2);
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
    }
}
