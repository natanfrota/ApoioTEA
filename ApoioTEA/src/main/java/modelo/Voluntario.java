package modelo;

import java.time.LocalDate;
import java.util.List;

import dao.VoluntarioDAO;

public class Voluntario extends Usuario {
	private String experiencia;
	private String habilidades;
	private double avaliacaoMedia;
	
	public Voluntario() {
		
	}
	
	public Voluntario(String nome, String email, String senha, LocalDate dataNascimento, 
			String cidade, String estado, StatusConta statusConta) {
		super(nome, email, senha, dataNascimento, cidade, estado, statusConta);
	}
	
	public Voluntario(int id, String nome, String email, String senha, LocalDate dataNascimento, 
			String cidade, String estado, StatusConta statusConta) {
		super(id, nome, email, senha, dataNascimento, cidade, estado, statusConta);
	}

	@Override
	public void fazerCadastro() {
		new VoluntarioDAO().inserirVoluntario(this);
	}
	
	public String getExperiencia() {
		return experiencia;
	}
	
	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}
	
	public String getHabilidades() {
		return habilidades;
	}
	
	public void setHabilidades(String habilidades) {
		this.habilidades = habilidades;
	}
	
	public double getAvaliacaoMedia() {
		return avaliacaoMedia;
	}
	
	public void setAvaliacaoMedia(double avaliacaoMedia) {
		this.avaliacaoMedia = avaliacaoMedia;
	}
	
	public List<Voluntario> retornarVoluntarios(){
		return new VoluntarioDAO().selecionarVoluntarios();

	}
}
