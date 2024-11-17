package modelo;

import java.time.LocalDate;

public class Usuario {
	private int id;
	private String nome;
	private String email;
	private String senha;
	private String foto;
	private LocalDate dataCadastro;
	private String cidade;
	private String estado;
	private String descricao;
	private StatusConta status;
	
	public Usuario(String nome, String email, String senha, String cidade,
			String estado) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.cidade = cidade;
		this.estado = estado;
		this.status = StatusConta.ATIVA;
	}

	public boolean fazerCadastro() {
		return true;
	}
	
	public void fazerLogin() {
		
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

	public StatusConta getStatus() {
		return status;
	}

	public void setStatus(StatusConta status) {
		this.status = status;
	}
}
