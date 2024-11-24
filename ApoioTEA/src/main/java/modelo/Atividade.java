package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import dao.AtividadeDAO;

public class Atividade {
	private int id;
	private String titulo;
	private String categoria;
	private String descricao;
	private String localizacao;
	private String status; //aberta, confirmada, concluida, cancelada
	private LocalDate data;
	private LocalTime hora;
	private Familia familia;
	private Voluntario voluntariosCandidatos;
	private Voluntario voluntarioEscolhido;
	
	public Atividade() {
		
	}
	
	public Atividade(String titulo, String categoria, String descricao, 
			String localizacao, LocalDate data, LocalTime hora) {
		this.titulo = titulo;
		this.categoria = categoria;
		this.descricao = descricao;
		this.localizacao = localizacao;
		this.status = "aberta";
		this.data = data;
		this.hora = hora;
	}

	public Atividade(int id, String titulo, String categoria, String descricao, 
			String localizacao, String status, LocalDate data, LocalTime hora) {
		this.id = id;
		this.titulo = titulo;
		this.categoria = categoria;
		this.descricao = descricao;
		this.localizacao = localizacao;
		this.status = status;
		this.data = data;
		this.hora = hora;
	}
	
	public void cadastrar() {
		new AtividadeDAO().inserirAtividade(this);
	}
	
	public List<Atividade> selecionarTodasAsAtividades(){
		return new AtividadeDAO().selecionarTodasAsAtividades();
	}
	
	public void excluir() {
		new AtividadeDAO().excluirAtividade(id);
	}

	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	public Voluntario getVoluntarioEscolhido() {
		return voluntarioEscolhido;
	}

	public void setVoluntarioEscolhido(Voluntario voluntarioEscolhido) {
		this.voluntarioEscolhido = voluntarioEscolhido;
	}
	
	public Voluntario getVoluntariosCandidatos() {
		return voluntariosCandidatos;
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
