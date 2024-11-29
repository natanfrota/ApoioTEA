package controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Conexao;
import dao.MensagemDAO;
import modelo.Mensagem;

@WebServlet("/chat")
public class ChatControlador extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int conversaId = Integer.parseInt(request.getParameter("conversaId"));
        try (Connection connection = Conexao.criarConexao()) {
            MensagemDAO mensagemDAO = new MensagemDAO(connection);
            List<Mensagem> mensagens = mensagemDAO.listarMensagens(conversaId);
            request.setAttribute("mensagens", mensagens);
            request.getRequestDispatcher("chat.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int conversaId = Integer.parseInt(request.getParameter("conversaId"));
        int usuarioId = (int) request.getSession().getAttribute("usuarioId");
        String conteudo = request.getParameter("conteudo");

        try (Connection connection = Conexao.criarConexao()) {
            MensagemDAO mensagemDAO = new MensagemDAO(connection);
            Mensagem mensagem = new Mensagem();
            mensagem.setConversaId(conversaId);
            mensagem.setUsuarioId(usuarioId);
            mensagem.setConteudo(conteudo);
            mensagemDAO.criarMensagem(mensagem);
            response.sendRedirect("chat?conversaId=" + conversaId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
}
