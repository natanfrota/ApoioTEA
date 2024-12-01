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
import dao.MensagemDAO;
import modelo.Conversa;
import modelo.Mensagem;
import modelo.Usuario;

@WebServlet(urlPatterns = {"/conversas", "/conversar", "/enviar-mensagem"})
public class ConversaControlador extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ConversaDAO conversaDAO = new ConversaDAO();
    private MensagemDAO mensagemDAO = new MensagemDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		System.out.println(action);
		
		if (action.equals("/conversas")) { 
			exibirConversas(request, response);
		} else if (action.equals("/conversar")) {
			exibirConversa(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getServletPath();
		
		if (action.equals("/enviar-mensagem")) { 
			enviarMensagem(request, response);
		}
    }
	
	protected void exibirConversas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessao = request.getSession(false);
		Usuario usuario = (Usuario) sessao.getAttribute("usuario");
		
		List<Conversa> conversas = conversaDAO.selecionarConversasDeUmUsuario(usuario);
        request.setAttribute("conversas", conversas);
        request.getRequestDispatcher("conversas.jsp").forward(request, response);
	}
	
	public void exibirConversa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int usuario2Id = Integer.parseInt(request.getParameter("usuario2Id"));
		
		HttpSession sessao = request.getSession(false);
		Usuario usuario = (Usuario) sessao.getAttribute("usuario"); 
		List<Conversa> conversas = conversaDAO.selecionarConversasDeUmUsuario(usuario);
		Conversa conversa = usuario.retornarConversaComOutroUsuario(conversas, usuario2Id);
		
		if(conversa != null) { 			
			conversa.setMensagens(mensagemDAO.selecionarMensagens(conversa));
			
		} else { 						
			conversa = new Conversa();
			int conversaId = conversa.criar(usuario.getId(), usuario2Id);
			conversa.setId(conversaId);
			conversaDAO.selecionarConversa(conversa);
			
			if(conversa.getUsuario1().getId() != usuario.getId()) {
				Usuario temp = conversa.getUsuario1();
				conversa.setUsuario1(conversa.getUsuario2());
				conversa.setUsuario2(temp);
			}
		}
		
		request.setAttribute("conversa", conversa);
		RequestDispatcher rd = request.getRequestDispatcher("chat.jsp");
		rd.forward(request, response);
	}
	
	public void enviarMensagem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int conversaId = Integer.parseInt(request.getParameter("conversaId"));
		String conteudo = request.getParameter("conteudo");
		
		HttpSession sessao = request.getSession(false);
		Usuario usuario = (Usuario) sessao.getAttribute("usuario");
		Mensagem mensagem = new Mensagem();
		mensagem.setConteudo(conteudo);
		mensagem.setRemetente(usuario);
		mensagemDAO.criarMensagem(mensagem, conversaId);
		
		String paginaAnterior = request.getHeader("Referer");
		if (paginaAnterior != null) {
		    response.sendRedirect(paginaAnterior);
		}
	}
}
