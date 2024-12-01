package modelo;

import java.time.LocalDateTime;

public class Mensagem {
    private int id;
    private String conteudo;
    private LocalDateTime dataHoraDeEnvio;
    private Usuario remetente;
    private Conversa conversa;
	
    public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getConteudo() {
		return conteudo;
	}
	
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
	public LocalDateTime getDataHoraDeEnvio() {
		return dataHoraDeEnvio;
	}
	
	public void setDataHoraDeEnvio(LocalDateTime dataHoraDeEnvio) {
		this.dataHoraDeEnvio = dataHoraDeEnvio;
	}
	
	public Usuario getRemetente() {
		return remetente;
	}
	
	public void setRemetente(Usuario remetente) {
		this.remetente = remetente;
	}

	public Conversa getConversa() {
		return conversa;
	}

	public void setConversa(Conversa conversa) {
		this.conversa = conversa;
	}
}
