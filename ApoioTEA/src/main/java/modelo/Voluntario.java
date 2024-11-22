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

	public Voluntario(String nome, String email, LocalDate dataNascimento, String cidade, String estado,
			String descricao, String statusConta, String experiencia, String habilidades,
			double avaliacaoMedia) {
		super(nome, email, dataNascimento, cidade, estado, descricao, "voluntario", statusConta);
		this.experiencia = experiencia;
		this.habilidades = habilidades;
		this.avaliacaoMedia = avaliacaoMedia;
	}

	public Voluntario(int id, String nome, String email, LocalDate dataNascimento, String cidade, String estado,
			String descricao, String statusConta, String experiencia, String habilidades,
			double avaliacaoMedia) {
		super(id, nome, email, dataNascimento, cidade, estado, descricao, "voluntario", statusConta);
		this.experiencia = experiencia;
		this.habilidades = habilidades;
		this.avaliacaoMedia = avaliacaoMedia;
	}

	@Override
	public void fazerCadastro() {
		new VoluntarioDAO().inserirVoluntario(this);
	}
	
	@Override
	public void editarPerfil() {
		super.editarPerfil();
		new VoluntarioDAO().alterarDadosPerfil(this);
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
	
	public boolean selecionarVoluntario() {
		return new VoluntarioDAO().selecionarVoluntario(this);
	}
}
