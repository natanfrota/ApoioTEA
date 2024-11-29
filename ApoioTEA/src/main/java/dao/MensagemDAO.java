package dao;

import modelo.Mensagem;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MensagemDAO {
    private Connection connection;

    public MensagemDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Mensagem> listarMensagens(int conversaId) throws SQLException {
        String query = "SELECT * FROM mensagem WHERE conversa_id = ? ORDER BY dataHoraDeEnvio";
        List<Mensagem> mensagens = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, conversaId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Mensagem mensagem = new Mensagem();
                mensagem.setId(rs.getInt("id"));
                mensagem.setConteudo(rs.getString("conteudo"));
                mensagem.setDataHoraDeEnvio(rs.getTimestamp("dataHoraDeEnvio").toLocalDateTime());
                mensagem.setConversaId(rs.getInt("conversa_id"));
                mensagem.setUsuarioId(rs.getInt("usuario_id"));
                mensagens.add(mensagem);
            }
        }
        return mensagens;
    }

    public void criarMensagem(Mensagem mensagem) throws SQLException {
        String query = "INSERT INTO mensagem (conteudo, conversa_id, usuario_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, mensagem.getConteudo());
            stmt.setInt(2, mensagem.getConversaId());
            stmt.setInt(3, mensagem.getUsuarioId());
            stmt.executeUpdate();
        }
    }
}
