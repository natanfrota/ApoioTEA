package modelo;

import java.time.LocalDate;
import java.util.List;

import dao.FamiliaDAO;

public class Familia extends Usuario{
	private List<Atividade> atividades;
	
	public Familia() {
		
	}
	
	public Familia(String nome, String email, LocalDate dataNascimento, String cidade, String estado, String descricao,
			String statusConta) {
		super(nome, email, dataNascimento, cidade, estado, descricao, "familia", statusConta);
	}
	
	public Familia(int id, String nome, String email, LocalDate dataNascimento, String cidade, String estado, String descricao,
			String statusConta) {
		super(id, nome, email, dataNascimento, cidade, estado, descricao, "familia", statusConta);
	}
	
	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

	@Override
	public void fazerCadastro() {
		new FamiliaDAO().inserirFamilia(this);
	}
	
	public List<Familia> retornarFamilias(){
		return new FamiliaDAO().selecionarFamilias();
	}
	
	public boolean selecionarFamilia() {
		return new FamiliaDAO().selecionarFamilia(this);
	}
	
	public void criarAtividade(Atividade atividade) {
		atividade.setFamilia(this);
		atividade.criar();
	}
	
	public void excluirAtividade(int atividadeId) {
		if(atividades == null)
			return;
		
		int cont, tam;
		for(cont = 0, tam = atividades.size(); cont < tam; cont++){
			if(atividades.get(cont) != null && atividades.get(cont).getId() == atividadeId) {
				atividades.get(cont).excluir();
				System.out.println("Atividade excluída.");
				break;
			}
		}
	}
	
	public Atividade retornarAtividade(int atividadeId) {
		for (Atividade atividade : atividades) {
			if(atividade.getId() == atividadeId) {
				return atividade;
			}
		}
		return null;
	}
	
	public void alterarDadosPerfil() {
		new FamiliaDAO().alterarDadosPerfil(this);
	}
}