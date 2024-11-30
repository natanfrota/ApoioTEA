package controlador;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

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
		"/adicionar-candidato", "/cancelar-candidatura", "/cancelar-candidatura-confirmada",
		"/excluir-atividade", "/aceitar-voluntario", "/remover-voluntario-escolhido"})
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
		} else if (action.equals("/excluir-atividade")) {
			excluirAtividade(request, response);
		} else if (action.equals("/salvar-edicao-atividade")) {
			salvarEdicaoAtividade(request, response);
		} else if(action.equals("/adicionar-candidato")) {
			adicionarCandidatura(request, response);
		} else if(action.equals("/cancelar-candidatura")) {
			cancelarCandidatura(request, response);
		} else if(action.equals("/cancelar-candidatura-confirmada")) {
			cancelarCandidaturaConfirmada(request, response);
		} else if(action.equals("/aceitar-voluntario")) {
			aceitarVoluntario(request, response);
		} else if(action.equals("/remover-voluntario-escolhido")) {
			removerVoluntarioEscolhido(request, response);
		}
	}
	
	protected void publicarAtividade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String titulo = request.getParameter("titulo");
		String data = request.getParameter("data");
		String hora = request.getParameter("hora");
		String localizacao = request.getParameter("localizacao");
		String descricao = request.getParameter("descricao");
		String categoria = request.getParameter("tipo");		
		
		HttpSession sessao = request.getSession(false);
		if(sessao != null && sessao.getAttribute("usuario") != null) {
			Familia familia = (Familia) sessao.getAttribute("usuario");
			Atividade atividade = new Atividade(titulo, categoria, descricao, localizacao, 
					 "aberta", LocalDate.parse(data), LocalTime.parse(hora));
			familia.criarAtividade(atividade);
			response.sendRedirect("inicio-familia");
			
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
	
	protected void excluirAtividade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = null;

		try {
			id = Integer.valueOf(request.getParameter("atividadeId"));
		} catch (NumberFormatException e) {
			System.err.println(e);
		}
		
		if(id != null) {		
			HttpSession sessao = request.getSession(false);
			Familia familia = (Familia) sessao.getAttribute("usuario");
			
			if(familia != null && familia.getAtividades() != null) {
				familia.excluirAtividade(id);
			}
			
			String paginaAnterior = request.getHeader("Referer");
			if (paginaAnterior != null) {
			    response.sendRedirect(paginaAnterior);
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
	
	protected void adicionarCandidatura(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int atividadeId = Integer.parseInt(request.getParameter("atividadeId"));
		
		HttpSession sessao = request.getSession(false);
		if(sessao != null) {
			Voluntario voluntario = (Voluntario) sessao.getAttribute("usuario");
			Atividade atv = new Atividade();
			atv.setId(atividadeId);
			atv.selecionarAtividade();
			atv.registrarCandidaturaDeUmVoluntario(voluntario.getId());
			
			String paginaAnterior = request.getHeader("Referer");
			if (paginaAnterior != null) {
			    response.sendRedirect(paginaAnterior);
			}
		}
	}
	
					/* caso o voluntário desista da atividade */
	protected void cancelarCandidatura(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int atividadeId = Integer.parseInt(request.getParameter("atividadeId"));
		
		HttpSession sessao = request.getSession(false);
		if(sessao != null) {
			Voluntario voluntario = (Voluntario) sessao.getAttribute("usuario");
			Atividade atv = new Atividade();
			atv.setId(atividadeId);
			atv.selecionarAtividade();
			atv.removerCandidaturaDeUmaAtividade(voluntario.getId());
			
			String paginaAnterior = request.getHeader("Referer");
			if (paginaAnterior != null) {
			    response.sendRedirect(paginaAnterior);
			}
		}
	}
	
				/* para o caso de o voluntário querer cancelar depois de ser escolhido */
	protected void cancelarCandidaturaConfirmada(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int atividadeId = Integer.parseInt(request.getParameter("atividadeId"));
		
		HttpSession sessao = request.getSession(false);
		if(sessao != null) {
			Atividade atv = new Atividade();
			atv.setId(atividadeId);
			atv.selecionarAtividade();
			atv.removerVoluntarioEscolhido();
			
			String paginaAnterior = request.getHeader("Referer");
			if (paginaAnterior != null) {
			    response.sendRedirect(paginaAnterior);
			}
		}
	}
	
	//testar
	protected void aceitarVoluntario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int atividadeId = Integer.parseInt(request.getParameter("atividadeId"));
		int voluntarioId = Integer.parseInt(request.getParameter("voluntarioId"));
	
		System.out.println("a_id: " + atividadeId + "v_id: " + voluntarioId);
		
		HttpSession sessao = request.getSession(false);
		Familia familia = (Familia) sessao.getAttribute("usuario");
		
		if(familia != null && familia.getAtividades() != null) {
			Atividade atividade = familia.retornarAtividade(atividadeId);
			boolean encontrado = atividade.buscarCandidato(voluntarioId);
			
			System.out.println("dentro do primeiro if de aceitar");
			
			if(atividade != null && encontrado) {
				atividade.registrarVoluntarioEscolhido(voluntarioId);
				//atividade.removerCandidaturaDeUmaAtividade(voluntarioId);
				
				System.out.println("dentro do segundo if de aceitar");
				
				String paginaAnterior = request.getHeader("Referer");
				System.out.println("pagina anterior:" + paginaAnterior);
				if (paginaAnterior != null) {
				    response.sendRedirect(paginaAnterior);
				}
			}
		}
	}

	// testar
				/* para o caso de a família querer remover o voluntário escolhido */
	protected void removerVoluntarioEscolhido(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int atividadeId = Integer.parseInt(request.getParameter("atividadeId"));
		int voluntarioId = Integer.parseInt(request.getParameter("voluntarioId"));

		HttpSession sessao = request.getSession(false);
		Familia familia = (Familia) sessao.getAttribute("usuario");

		if (familia != null && familia.getAtividades() != null) {
			Atividade atividade = familia.retornarAtividade(atividadeId);
			Voluntario voluntario = new Voluntario();
			voluntario.setId(voluntarioId);
			boolean encontrado = voluntario.selecionarVoluntario();
			if (atividade != null && encontrado) {
				atividade.removerVoluntarioEscolhido();
				
				String paginaAnterior = request.getHeader("Referer");
				if (paginaAnterior != null) {
				    response.sendRedirect(paginaAnterior);
				}
			}
		}
	}
}
