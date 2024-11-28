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

import modelo.Atividade;
import modelo.Familia;
import modelo.Voluntario;


@WebServlet(urlPatterns = {"/perfil-voluntario", "/perfil-familia", "/inicio-voluntario",
		"/inicio-familia", "/sair"})
public class PerfilControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PerfilControlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getServletPath();
		System.out.println(action);
		
		if(action.equals("/perfil-voluntario")) {
			exibirVoluntario(request, response);
		} else if(action.equals("/perfil-familia")) {
			exibirFamilia(request, response);
		} else if (action.equals("/inicio-voluntario")) {
			exibirPaginaInicialDoVoluntario(request, response);
		} else if (action.equals("/inicio-familia")) {
			exibirPaginaInicialDaFamilia(request, response);
		} else if (action.equals("/sair")) {
			sair(request, response);
		}
	}
	
	protected void exibirVoluntario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = null;

		try {
			id = Integer.valueOf(request.getParameter("id"));
		} catch (NumberFormatException e) {
			System.err.println(e);
		}
		
		if(id != null) { 
			Voluntario voluntario = new Voluntario();
			voluntario.setId(id);
			boolean encontrado = voluntario.selecionarVoluntario();
						
			if(encontrado) { 
				request.setAttribute("voluntario", voluntario);
				RequestDispatcher rd = request.getRequestDispatcher("perfil-voluntario.jsp");
				rd.forward(request, response);
				return;
			}
		}
	}
	
	protected void exibirFamilia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = null;

		try {
			id = Integer.valueOf(request.getParameter("id"));
		} catch (NumberFormatException e) {
			System.err.println(e);
		}
		
		if(id != null) {
			Familia familia = new Familia();
			familia.setId(id);
			boolean encontrado = familia.selecionarFamilia();
						
			if(encontrado) { 
				familia.setAtividades(new Atividade().selecionarAtividadesDeUmaFamilia(
						familia.getId()));
				request.setAttribute("familia", familia);
				RequestDispatcher rd = request.getRequestDispatcher("perfil-familia.jsp");
				rd.forward(request, response);
				return;
			}
		}
	}
	
	protected void exibirPaginaInicialDoVoluntario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessao = request.getSession(false);
		Voluntario voluntario = (Voluntario) sessao.getAttribute("usuario");
		
		if(voluntario != null) {
			List<Atividade> atividades = new Atividade().selecionarTodasAsAtividades();
			request.setAttribute("atividades", atividades);
			RequestDispatcher rd = request.getRequestDispatcher("inicio-voluntario.jsp");
			rd.forward(request, response);
		}
	}
	
	protected void exibirPaginaInicialDaFamilia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessao = request.getSession(false);
		Familia familia = (Familia) sessao.getAttribute("usuario");
		
		if(familia != null) {
			List<Atividade> atividades = new Atividade().selecionarAtividadesDeUmaFamilia(familia.getId());
			request.setAttribute("atividades", atividades);
			RequestDispatcher rd = request.getRequestDispatcher("inicio-familia.jsp");
			rd.forward(request, response);
		}
	}
	
	protected void sair(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("dentro de sair");
		HttpSession sessao = request.getSession(false);
		System.out.println(sessao);
		sessao.invalidate();
		response.sendRedirect("login.jsp");
	}
}