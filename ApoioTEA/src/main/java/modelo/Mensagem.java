package modelo;

import java.time.LocalDateTime;

public class Mensagem {
    private int id;
    private String conteudo;
    private LocalDateTime dataHoraDeEnvio;
    private int conversaId;
    private int usuarioId;
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getConteudo() { return conteudo; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }
    public LocalDateTime getDataHoraDeEnvio() { return dataHoraDeEnvio; }
    public void setDataHoraDeEnvio(LocalDateTime dataHoraDeEnvio) { this.dataHoraDeEnvio = dataHoraDeEnvio; }
    public int getConversaId() { return conversaId; }
    public void setConversaId(int conversaId) { this.conversaId = conversaId; }
    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }
}
