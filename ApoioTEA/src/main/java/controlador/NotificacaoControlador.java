package controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.NotificacaoDAO;
import modelo.Notificacao;
import modelo.Usuario;

@WebServlet(urlPatterns = {"/notificacoes"})
public class NotificacaoControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NotificacaoDAO notificacaoDAO = new NotificacaoDAO();   
	
    public NotificacaoControlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if(action.equals("/notificacoes")) {
			exibirNotificacoes(request, response);
		}
	}
	
	protected void exibirNotificacoes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessao = request.getSession(false);
		Usuario usuario = (Usuario) sessao.getAttribute("usuario");
		
		List<Notificacao> notificacoes = notificacaoDAO.selecionarNotificacoesDeUmUsuario(usuario.getId());
		
		request.setAttribute("notificacoes", notificacoes);
		request.getRequestDispatcher("notificacoes.jsp").forward(request, response);
	}
}