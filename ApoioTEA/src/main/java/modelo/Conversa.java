package modelo;

import java.util.List;

public class Conversa {
    private int id;
    private Usuario usuario1;
    private Usuario usuario2;
    private List<Mensagem> mensagens;
	
    public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Usuario getUsuario1() {
		return usuario1;
	}
	
	public void setUsuario1(Usuario usuario1) {
		this.usuario1 = usuario1;
	}
	
	public Usuario getUsuario2() {
		return usuario2;
	}
	
	public void setUsuario2(Usuario usuario2) {
		this.usuario2 = usuario2;
	}
	
	public List<Mensagem> getMensagens() {
		return mensagens;
	}
	
	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}
}
