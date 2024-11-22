package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Atividade {
	private int id;
	private String categoria;
	private String titulo;
	private String descricao;
	private String localizacao;
	private String status; //aberta, confirmada, concluida, cancelada
	private LocalDate data;
	private LocalTime hora;
	
	public Atividade() {
		
	}
	
	public Atividade(int id, String categoria, String titulo, String descricao, String localizacao, 
			String status, LocalDate data, LocalTime hora) {
		this.id = id;
		this.categoria = categoria;
		this.titulo = titulo;
		this.descricao = descricao;
		this.localizacao = localizacao;
		this.status = status;
		this.data = data;
		this.hora = hora;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
}
