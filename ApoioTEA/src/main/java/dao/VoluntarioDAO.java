package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.xml.transform.Result;

import modelo.Voluntario;

public class VoluntarioDAO {
	private List<Voluntario> voluntarios; 
	
	public void inserirVoluntario(Voluntario voluntario) {
		String insercaoUsuario = "insert into usuario (nome, email, senha, cidade, "
				  + "estado, tipo) values (?,?,?,?,?,?)";
		String insercaoVoluntario = "insert into voluntario (experiencia, habilidades)"
				+ " values (?, ?)";
		
		try (Connection conexao = Conexao.criarConexao()){
			conexao.setAutoCommit(false);
			
			try (PreparedStatement ps = conexao.prepareStatement(insercaoUsuario, 
					PreparedStatement.RETURN_GENERATED_KEYS)) {
				ps.setString(1, voluntario.getNome());
				ps.setString(2, voluntario.getEmail());
				ps.setString(3, voluntario.getSenha());
				ps.setString(4, voluntario.getCidade());
				ps.setString(5, voluntario.getEstado());
				ps.setString(6, "voluntário");
				
				ps.executeUpdate();
				
				ResultSet rs = ps.getGeneratedKeys();
				int idUsuario;
				if(rs.next())
					idUsuario = rs.getInt("id");
				
				
				
			} catch (SQLException e) {
				System.err.println(e);
			}
			
		} catch (Exception e) {
			
		}
	}
	
	public List<Voluntario> selecionarVoluntarios(){
		
	}
}
