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
import dao.ConversaDAO;
import modelo.Conversa;

@WebServlet("/conversas")
public class ConversaControlador extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int usuarioId = (int) request.getSession().getAttribute("usuarioId");
        try (Connection connection = Conexao.criarConexao()){
            ConversaDAO conversaDAO = new ConversaDAO(connection);
            List<Conversa> conversas = conversaDAO.listarConversas(usuarioId);
            request.setAttribute("conversas", conversas);
            request.getRequestDispatcher("conversas.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
}
