package modelo;

public class Voluntario extends Usuario {
	private String experiencia;
	private String habilidades;
	private double avaliacaoMedia;
	
	public Voluntario(String nome, String email, String senha, String cidade,
			String estado) {
		super(nome, email, senha, cidade, estado);
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
}
