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
	private StatusConta statusConta;
	
	public Usuario() {
		
	}
	
	public Usuario(String nome, String email, String senha, LocalDate dataNascimento, 
			String cidade, String estado, StatusConta statusConta) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.cidade = cidade;
		this.estado = estado;
		this.statusConta = statusConta;
	}
	
	public Usuario(int id, String nome, String email, String senha, LocalDate dataNascimento, 
			String cidade, String estado, StatusConta statusConta) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.cidade = cidade;
		this.estado = estado;
		this.statusConta = statusConta;
	}

	public void fazerCadastro() { // método a ser implementado pelas classes filhas
	}
	
	public boolean fazerLogin() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscarEmailESenha(this);
		
		return (usuario != null);
		
	}
	
	public void editarPerfil() {
		
	}
	
	public void excluirConta() {
		
	}
	
	public void sair() {
		
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

	public StatusConta getStatusConta() {
		return statusConta;
	}

	public void setStatusConta(StatusConta statusConta) {
		this.statusConta = statusConta;
	}
}