package controlador;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Atividade;
import modelo.Familia;

/**
 * Servlet implementation class AtividadesControlador
 */
@WebServlet(urlPatterns = {"/publicar", "/inicio-voluntario1", "/adicionar-candidato"})
public class AtividadesControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AtividadesControlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/publicar")) {
			publicarAtividade(request, response);
		} else if (action.equals("/inicio-voluntario1")) {
			exibirAtividades(request, response);
		} else if(action.equals("/adicionar-candidato")) {
			adicionarCandidato(request, response);
		}
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	protected void publicarAtividade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String titulo = request.getParameter("titulo");
		String data = request.getParameter("data");
		String hora = request.getParameter("hora");
		String localizacao = request.getParameter("localizacao");
		String descricao = request.getParameter("descricao");
		String categoria = request.getParameter("tipo");		
		
		System.out.println(descricao);
		System.out.println(titulo);
		System.out.println(data);
		System.out.println(hora);
		
		//validar dados depois 
		
		HttpSession sessao = request.getSession(false);
		if(sessao != null && sessao.getAttribute("usuario") != null) {
			Familia familia = (Familia) sessao.getAttribute("usuario");
			Atividade atividade = new Atividade(titulo, categoria, descricao, localizacao, 
					 LocalDate.parse(data), LocalTime.parse(hora));
			familia.criarAtividade(atividade);
			response.sendRedirect("perfil-familia.jsp");
			
		} else {
			response.sendRedirect("login.jsp");
		}
	}
	
	protected void exibirAtividades(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Atividade> atividades = new Atividade().selecionarTodasAsAtividades();
		request.setAttribute("atividades", atividades);
		RequestDispatcher rd = request.getRequestDispatcher("inicio-voluntario1.jsp");
		rd.forward(request, response);
		
	}
	
	protected void adicionarCandidato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int atividadeId = Integer.parseInt(request.getParameter("atividadeId"));
		System.out.println(atividadeId);
		
		
	}

}
