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
import modelo.Usuario;
import modelo.Voluntario;


@WebServlet(urlPatterns = {"/publicar", "/editar-atividade", "/salvar-edicao-atividade", 
		"/inicio-voluntario", "/adicionar-candidato", "/cancelar-candidatura"})
public class AtividadesControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AtividadesControlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/publicar")) {
			publicarAtividade(request, response);
		} else if (action.equals("/editar-atividade")) {
			editarAtividade(request, response);
		} else if (action.equals("/salvar-edicao-atividade")) {
			salvarEdicaoAtividade(request, response);
		} else if (action.equals("/inicio-voluntario")) {
			exibirAtividades(request, response);
		} else if(action.equals("/adicionar-candidato")) {
			adicionarCandidatura(request, response);
		} else if(action.equals("/cancelar-candidatura")) {
			removerCandidatura(request, response);
		}
	}

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
		
		
		HttpSession sessao = request.getSession(false);
		if(sessao != null && sessao.getAttribute("usuario") != null) {
			Familia familia = (Familia) sessao.getAttribute("usuario");
			Atividade atividade = new Atividade(titulo, categoria, descricao, localizacao, 
					 "aberta", LocalDate.parse(data), LocalTime.parse(hora));
			familia.criarAtividade(atividade);
			response.sendRedirect("perfil-familia.jsp");
			
		} else {
			response.sendRedirect("login.jsp");
		}
	}
	
	protected void editarAtividade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = null;

		try {
			id = Integer.valueOf(request.getParameter("atividadeId"));
		} catch (NumberFormatException e) {
			System.err.println(e);
		}
		
		if(id != null) {
			Atividade atividade = new Atividade();
			atividade.setId(id);
			boolean encontrado = atividade.selecionarAtividade();
						
			if(encontrado) {
				request.setAttribute("atividade", atividade);
				RequestDispatcher rd = request.getRequestDispatcher("editar-atividade.jsp");
				rd.forward(request, response);
				return;
			}
		}
	}
	
	protected void salvarEdicaoAtividade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		System.out.println("entrou aqui");
		int atividadeId = Integer.parseInt(request.getParameter("atividadeId"));
		String titulo = request.getParameter("titulo");
		String data = request.getParameter("data");
		String hora = request.getParameter("hora");
		String localizacao = request.getParameter("localizacao");
		String descricao = request.getParameter("descricao");
		String categoria = request.getParameter("tipo");		
		
		HttpSession sessao = request.getSession(false);
		if(sessao != null) {
			Atividade atividade = new Atividade(atividadeId, titulo, categoria, descricao, 
					localizacao, "aberta", LocalDate.parse(data), LocalTime.parse(hora));
			atividade.editarAtividade();
			Usuario usuario = (Usuario) sessao.getAttribute("usuario");
			response.sendRedirect("perfil-familia?id=" + usuario.getId());
			
		} else {
			response.sendRedirect("login.jsp");
		}
	}
	
	
	
	protected void exibirAtividades(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Atividade> atividades = new Atividade().selecionarTodasAsAtividades();
		request.setAttribute("atividades", atividades);
		RequestDispatcher rd = request.getRequestDispatcher("inicio-voluntario.jsp");
		rd.forward(request, response);
		
	}
	
	protected void adicionarCandidatura(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int atividadeId = Integer.parseInt(request.getParameter("atividadeId"));
		int familiaId = Integer.parseInt(request.getParameter("familiaId"));
		
		HttpSession sessao = request.getSession(false);
		if(sessao != null) {
			Voluntario voluntario = (Voluntario) sessao.getAttribute("usuario");
			Atividade atv = new Atividade();
			atv.setId(atividadeId);
			atv.selecionarAtividade();
			atv.registrarCandidaturaDeUmVoluntario(atividadeId, familiaId, voluntario.getId());
		}
	}
	
	protected void removerCandidatura(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int atividadeId = Integer.parseInt(request.getParameter("atividadeId"));
		int familiaId = Integer.parseInt(request.getParameter("familiaId"));
		
		HttpSession sessao = request.getSession(false);
		if(sessao != null) {
			Voluntario voluntario = (Voluntario) sessao.getAttribute("usuario");
			Atividade atv = new Atividade();
			atv.setId(atividadeId);
			atv.selecionarAtividade();
			atv.removerCandidaturaDeUmaAtividade(atividadeId, familiaId, voluntario.getId());
		}
	}
}
