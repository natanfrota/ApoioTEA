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
import modelo.Usuario;
import modelo.Voluntario;

public class AtividadeDAO {
	public void inserirAtividade(Atividade atividade) {
		String insercao = "insert into atividade (titulo, categoria, descricao, "
				+ "localizacao, status, data, hora, familia_usuario_id) "
				+ "values (?,?,?,?,?,?,?,?)";
		
		Connection conexao = null;
		PreparedStatement ps = null;
		
		try {
			conexao = Conexao.criarConexao();
			ps = conexao.prepareStatement(insercao);
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
			
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
		} finally {
			try {
				if(ps != null)
					ps.close();
				if(conexao != null)
					conexao.close();
			} catch (SQLException e2) {
				System.err.println(e2);
			}
		}
	}
	
	public List<Atividade> selecionarTodasAsAtividades(){
		String consulta = "select * from atividade";
		List<Atividade> atividades = null;
		
		try(Connection conexao = Conexao.criarConexao();
			PreparedStatement ps = conexao.prepareStatement(consulta);
			ResultSet rs = ps.executeQuery()) {
			
			atividades = new ArrayList<Atividade>();
			List<Familia> familias = new FamiliaDAO().selecionarFamilias();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String titulo = rs.getString("titulo");
				String categoria = rs.getString("categoria");
				String descricao = rs.getString("descricao");
				String localizacao = rs.getString("localizacao");
				String status = rs.getString("status");
				LocalDate data = rs.getDate("data").toLocalDate();
				LocalTime hora = rs.getTime("hora").toLocalTime();
				int idFamilia = rs.getInt("familia_usuario_id");
				
				Atividade atividade = new Atividade(id, titulo, categoria, descricao, 
						localizacao, status, data, hora);
				atividades.add(atividade);
				
				associarFamiliasAAtividades(familias, atividade,
						idFamilia);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
		}
		
		return atividades;
	}
	
	private void associarFamiliasAAtividades(List<Familia> familias,
			Atividade atividade, int idFamilia) {
		for (Familia familia : familias) {
			if(familia.getId() == idFamilia) {
				atividade.setFamilia(familia);
				break;
			}
		}
	}
	
	public List<Atividade> selecionarAtividadesDeUmaFamilia(int idFamilia){
		String consulta = "select * from atividade where familia_usuario_id = ?";
		List<Atividade> atividades = null;
		Connection conexao = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			conexao = Conexao.criarConexao();
			ps = conexao.prepareStatement(consulta);
			ps.setInt(1, idFamilia);
			rs = ps.executeQuery();
			
			atividades = new ArrayList<Atividade>();
			
			while(rs.next()) {
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
	
	public void excluirAtividade(int id) {
		String exclusao = "delete from atividade where id = ?";
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = Conexao.criarConexao();
			ps = con.prepareStatement(exclusao);
			ps.setInt(1, id);
			
			int linhas = ps.executeUpdate();
			System.out.println("Linhas: " + linhas);
			
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
		
	}
	
	
	/*
	public static void main(String[] args) {
		Atividade atividade = new Atividade();
		Familia f = new Familia();
		//atividade.setId(9);
		
		//f.excluirAtividade(atividade);
		
		f.setId(2);
		new FamiliaDAO().selecionarFamilia(f);
		atividade.setTitulo("fumo, sancho");
		atividade.setData(LocalDate.now());
		atividade.setHora(LocalTime.now());
		atividade.setFamilia(f);
		atividade.cadastrar();
		
		List<Atividade> as = atividade.selecionarTodasAsAtividades();
		System.out.println(as.size());
	}
	*/
}
