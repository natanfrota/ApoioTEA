package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelo.Familia;

public class FamiliaDAO {
	public boolean inserirFamilia(Familia familia) {
		String insercaoUsuario = "insert into usuario (nome, email, senha, cidade, "
				+ "estado, tipo, data_nascimento, descricao) values (?,?,?,?,?,?,?,?)";
		String insercaoFamilia = "insert into familia (usuario_id) values (?)";

		Connection conexao = null;
		PreparedStatement ps = null, ps2 = null;
		ResultSet rs = null;

		try {
			conexao = Conexao.criarConexao();
			conexao.setAutoCommit(false);

			ps = conexao.prepareStatement(insercaoUsuario,
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, familia.getNome());
			ps.setString(2, familia.getEmail());
			ps.setString(3, familia.getSenha());
			ps.setString(4, familia.getCidade());
			ps.setString(5, familia.getEstado());
			ps.setString(6, familia.getTipo());
			ps.setDate(7, Date.valueOf(familia.getDataNascimento()));
			ps.setString(8, familia.getDescricao());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			rs.next();
			int idUsuario = rs.getInt(1);

			ps2 = conexao.prepareStatement(insercaoFamilia);
			ps2.setInt(1, idUsuario);
			ps2.executeUpdate();

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
	
	public List<Familia> selecionarFamilias() {
		List<Familia> familias = null;
		
		String selecao = "select * from usuario u join familia f on u.id = f.usuario_id";
		
		try(PreparedStatement ps = Conexao.criarConexao().prepareStatement(selecao);
			ResultSet rs = ps.executeQuery()) {
			familias = new ArrayList<>();

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
				
				familias.add(new Familia(id, nome, email, dataNascimento, cidade, estado,
						descricao, statusConta));
			}			
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
		}
		
		return familias;
	}
	
	public boolean selecionarFamilia(Familia familia) {
		String consulta = "select * from usuario u join familia f "
								  + "on u.id = f.usuario_id "
								  + "where u.id = ? and status_conta = 'ativa'";

		Connection conexao = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conexao = Conexao.criarConexao();
			ps = conexao.prepareStatement(consulta);
			ps.setInt(1, familia.getId());
			rs = ps.executeQuery();
			if(rs.next()) {			
				familia.setNome(rs.getString("nome"));
				familia.setEmail(rs.getString("email"));
				//familia.setFoto(rs.getString("foto"));
				familia.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
				familia.setDataCadastro(rs.getDate("data_cadastro").toLocalDate());
				familia.setCidade(rs.getString("cidade"));
				familia.setEstado(rs.getString("estado"));
				familia.setDescricao(rs.getString("descricao"));
				familia.setStatusConta(rs.getString("status_conta"));
				familia.setTipo(rs.getString("tipo"));
				
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
	
	public void alterarDadosPerfil(Familia familia) {
		String alteracaoUsuario = "update usuario set nome = ?, email = ?, data_nascimento = ?,"
				+ " cidade = ?, estado = ?, descricao = ? where id = ?";
		
		Connection conexao = null;
		PreparedStatement ps = null;
		
		try {
			conexao = Conexao.criarConexao();
			ps = conexao.prepareStatement(alteracaoUsuario);
			ps.setString(1, familia.getNome());
			ps.setString(2, familia.getEmail());
			ps.setDate(3, Date.valueOf(familia.getDataNascimento()));
			ps.setString(4, familia.getCidade());
			ps.setString(5, familia.getEstado());
			ps.setString(6, familia.getDescricao());
			ps.setInt(7, familia.getId());
			ps.executeUpdate();
			
			System.out.println("dados alterados");

		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
			
		} finally {
			try {
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