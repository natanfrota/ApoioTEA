package modelo;

import java.time.LocalDate;
import java.util.List;

import dao.AvaliacaoDAO;

public class Avaliacao {
	private int id;
	private int nota;
	private String comentario;
	private LocalDate data;
	private Familia familia;
	
	public Avaliacao() {
		
	}
	
	public void registrarAvalicao(int familiaId, int voluntarioId) {
		new AvaliacaoDAO().inserirAvalicao(this, familiaId, voluntarioId);
	}
	
	public List<Avaliacao> selecionarAvaliacoesDeUmVoluntario(int voluntarioId){
		return new AvaliacaoDAO().selecionarAvaliacoesDeUmVoluntario(voluntarioId);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}
}
