package modelo;

import java.time.LocalDate;

import dao.UsuarioDAO;

public class Usuario {
	private int id;
	private String nome;
	private String email;
	private String senha;
	private String foto;
	private LocalDate dataNascimento;
	private LocalDate dataCadastro;
	private String cidade;
	private String estado;
	private String descricao;
	private String tipo;
	private String statusConta;
	
	public Usuario() {
		
	}
	
	public Usuario(String nome, String email, LocalDate dataNascimento, 
			String cidade, String estado, String descricao, String tipo,
			String statusConta) {
		this.nome = nome;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.cidade = cidade;
		this.estado = estado;
		this.descricao = descricao;
		this.tipo = tipo;
		this.statusConta = statusConta;
	}
	
	public Usuario(int id, String nome, String email, LocalDate dataNascimento, 
			String cidade, String estado, String descricao, String tipo,
			String statusConta) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.cidade = cidade;
		this.estado = estado;
		this.descricao = descricao;
		this.tipo = tipo;
		this.statusConta = statusConta;
	}

	public void fazerCadastro() {
	}
	
	public boolean fazerLogin() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscarEmailESenha(this);
		return (usuario != null);
	}
	
	public void editarPerfil() {
		new UsuarioDAO().alterarDadosPerfilUsuario(this);
	}
	
	public void excluirConta() {
		new UsuarioDAO().excluirConta(this);
	}
	
	public int calcularIdade() {
		LocalDate hoje = LocalDate.now();
		int anos = hoje.getYear() - dataNascimento.getYear();
		int meses = hoje.getMonthValue() - dataNascimento.getMonthValue();
		int dias = hoje.getDayOfMonth() - dataNascimento.getDayOfMonth();
		
		if(meses < 0 || (meses == 0 && dias < 0))
			anos -= 1;		
		
		return anos;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatusConta() {
		return statusConta;
	}

	public void setStatusConta(String statusConta) {
		this.statusConta = statusConta;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}