package controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ConversaDAO;
import dao.FamiliaDAO;
import dao.MensagemDAO;
import dao.ModeradorDAO;
import dao.UsuarioDAO;
import dao.VoluntarioDAO;
import modelo.Conversa;
import modelo.Familia;
import modelo.Mensagem;
import modelo.Voluntario;

@WebServlet(urlPatterns = {"/login-moderador", "/inicio-moderador", "/exibir-voluntarios", "/exibir-familias",
		"/exibir-conversas", "/exibir-conversa", "/suspender-conta", "/reativar-conta"})
public class ModeradorControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ModeradorDAO moderadorDAO = new ModeradorDAO();
	private VoluntarioDAO voluntarioDAO = new VoluntarioDAO();
	private FamiliaDAO familiaDAO = new FamiliaDAO();
	private ConversaDAO conversaDAO = new ConversaDAO();
	private MensagemDAO mensagemDAO = new MensagemDAO();
	
    public ModeradorControlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		System.out.println(action);
		
		if(action.equals("/login-moderador")) {
			autenticarModerador(request, response);
		} else if(action.equals("/inicio-moderador")) {
			response.sendRedirect("inicio-moderador.jsp");
		} else if(action.equals("/exibir-voluntarios")) {
			exibirVoluntarios(request, response);
		} else if(action.equals("/exibir-familias")) {
			exibirFamilias(request, response);
		} else if(action.equals("/exibir-conversas")) {
			exibirConversas(request, response);
		} else if(action.equals("/exibir-conversa")) {
			exibirConversa(request, response);
		} else if(action.equals("/suspender-conta")) {
			suspenderConta(request, response);
		} else if(action.equals("/reativar-conta")) {
			reativarConta(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		System.out.println(action);
		
		if(action.equals("/login-moderador")) {
			autenticarModerador(request, response);
		}
	}

	
	protected void autenticarModerador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		if(email != null && senha != null) {
			boolean encontrado = moderadorDAO.selecionarModerador(email, senha);
			
			if(encontrado == true) {
				HttpSession sessao = request.getSession();
				response.sendRedirect("inicio-moderador.jsp");
				return;
			}
		} 
		
		request.setAttribute("status", "fracasso");
		RequestDispatcher rd = request.getRequestDispatcher("moderador.jsp");
		rd.forward(request, response);
	}
	
	protected void exibirVoluntarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessao = request.getSession(false);
		if(sessao != null) {
			List<Voluntario> voluntarios = voluntarioDAO.selecionarVoluntarios();
			
			request.setAttribute("voluntarios", voluntarios);
			request.getRequestDispatcher("moderar-voluntarios.jsp").forward(request, response);
		} else {
			response.sendRedirect("moderador.jsp");
		}
	}
	
	protected void exibirFamilias(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessao = request.getSession(false);
		if(sessao != null) {
		
			List<Familia> familias = familiaDAO.selecionarFamilias();
		
			request.setAttribute("familias", familias);
			request.getRequestDispatcher("moderar-familias.jsp").forward(request, response);
		} else {
			response.sendRedirect("moderador.jsp");
		}
	}
	
	protected void exibirConversas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int usuarioId = Integer.parseInt(request.getParameter("usuarioId"));
		
		List<Conversa> conversas = conversaDAO.selecionarConversasDeUmUsuario(usuarioId);
		request.setAttribute("conversas", conversas);
		request.getRequestDispatcher("moderar-conversas.jsp").forward(request, response);
	}
	
	protected void exibirConversa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int conversaId = Integer.parseInt(request.getParameter("conversaId"));
		
		Conversa conversa = new Conversa();
		conversa.setId(conversaId);
		conversaDAO.selecionarConversa(conversa);
		List<Mensagem> mensagens = mensagemDAO.selecionarMensagens(conversa);
		conversa.setMensagens(mensagens);
		request.setAttribute("conversa", conversa);
		request.getRequestDispatcher("moderar-conversa.jsp").forward(request, response);	
	}
	
	protected void suspenderConta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int usuarioId = Integer.parseInt(request.getParameter("usuarioId"));
		
		new UsuarioDAO().suspenderConta(usuarioId);
		
		String paginaAnterior = request.getHeader("Referer");
		if(paginaAnterior != null) {
			response.sendRedirect(paginaAnterior);
		}
	}

	protected void reativarConta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int usuarioId = Integer.parseInt(request.getParameter("usuarioId"));
		
		new UsuarioDAO().reativarConta(usuarioId);
		
		String paginaAnterior = request.getHeader("Referer");
		if(paginaAnterior != null) {
			response.sendRedirect(paginaAnterior);
		}

	}
}