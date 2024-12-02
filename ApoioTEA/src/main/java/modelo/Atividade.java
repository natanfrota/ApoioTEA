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
	private List<Voluntario> voluntariosCandidatos;
	private Voluntario voluntarioEscolhido;
	
	public Atividade() {
		
	}
	
	public Atividade(String titulo, String categoria, String descricao, 
			String localizacao, String status, LocalDate data, LocalTime hora) {
		this.titulo = titulo;
		this.categoria = categoria;
		this.descricao = descricao;
		this.localizacao = localizacao;
		this.status = status;
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
	
	public boolean criar() {
		return new AtividadeDAO().inserirAtividade(this);
	}
	
	public void editarAtividade() {
		new AtividadeDAO().editarAtividade(this);
	}
	
	public boolean excluir() {
		return new AtividadeDAO().excluirAtividade(id);
	}
	
	public boolean registrarCandidaturaDeUmVoluntario(int voluntarioId) {
		return new AtividadeDAO().registrarCandidaturaDeUmVoluntario(id, voluntarioId);
	}
	
	public boolean removerCandidaturaDeUmaAtividade(int voluntarioId) {
		return new AtividadeDAO().removerCandidaturaDeUmaAtividade(id, voluntarioId);
	}
	
	public void registrarVoluntarioEscolhido(int voluntarioId) {
		new AtividadeDAO().registrarVoluntarioEscolhido(id, voluntarioId); 
	}
	
	public void removerVoluntarioEscolhido() {
		new AtividadeDAO().removerVoluntarioEscolhido(id);
	}
	
	public boolean buscarCandidato(int candidatoId) {
		if(voluntariosCandidatos != null) {
			for (Voluntario voluntario : voluntariosCandidatos) {
				if(voluntario.getId() == candidatoId)
					return true;
			}
		}
		return false;
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

	public List<Voluntario> getVoluntariosCandidatos() {
		return voluntariosCandidatos;
	}

	public void setVoluntariosCandidatos(List<Voluntario> voluntariosCandidatos) {
		this.voluntariosCandidatos = voluntariosCandidatos;
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
	
	public boolean selecionarAtividade() {
		return new AtividadeDAO().selecionarAtividade(this); 
	}
	
	public List<Atividade> selecionarTodasAsAtividades(){
		return new AtividadeDAO().selecionarTodasAsAtividades();
	}
	
	public List<Atividade> selecionarAtividadesDeUmaFamilia(int idFamilia){
		return new AtividadeDAO().selecionarAtividadesDeUmaFamilia(idFamilia);
	}
	
	public List<Atividade> selecionarAtividadesConfirmadasDeUmaFamilia(int idFamilia){
		return new AtividadeDAO().selecionarAtividadesConfirmadasDeUmaFamilia(idFamilia);
	}
	
	public List<Atividade> selecionarAtividadesConfirmadasDeUmVoluntario(int voluntarioId){
		return new AtividadeDAO().selecionarAtividadesConfirmadasDeUmVoluntario(voluntarioId);
	}
}
