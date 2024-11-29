package dao;

import modelo.Conversa;
import modelo.Mensagem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConversaDAO {
    private Connection connection;

    public ConversaDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Conversa> listarConversas(int usuarioId) throws SQLException {
        String query = """
            SELECT c.id, c.titulo, c.avatar, 
                   (SELECT m.conteudo FROM mensagem m WHERE m.conversa_id = c.id ORDER BY m.dataHoraDeEnvio DESC LIMIT 1) AS ultimaMensagem,
                   (SELECT m.dataHoraDeEnvio FROM mensagem m WHERE m.conversa_id = c.id ORDER BY m.dataHoraDeEnvio DESC LIMIT 1) AS ultimaAtualizacao
            FROM usuario_has_conversa uc
            JOIN conversa c ON uc.conversa_id = c.id
            WHERE uc.usuario_id = ?
        """;

        List<Conversa> conversas = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Conversa conversa = new Conversa();
                conversa.setId(rs.getInt("id"));
                conversa.setTitulo(rs.getString("titulo"));
                conversa.setAvatar(rs.getString("avatar"));
                conversa.setUltimaMensagem(rs.getString("ultimaMensagem"));
                conversa.setUltimaAtualizacao(rs.getString("ultimaAtualizacao"));
                conversas.add(conversa);
            }
        }
        return conversas;
    }

    public void criarConversa(Conversa conversa) throws SQLException {
        String query = "INSERT INTO conversa (titulo, avatar) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, conversa.getTitulo());
            stmt.setString(2, conversa.getAvatar());
            stmt.executeUpdate();
        }
    }
}
