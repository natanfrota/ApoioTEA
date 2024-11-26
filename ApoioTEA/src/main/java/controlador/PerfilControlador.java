package controlador;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Atividade;
import modelo.Familia;
import modelo.Voluntario;


@WebServlet(urlPatterns = {"/perfil-voluntario", "/perfil-familia"})
public class PerfilControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PerfilControlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		
		if(action.equals("/perfil-voluntario")) {
			exibirVoluntario(request, response);
		} else if(action.equals("/perfil-familia")) {
			exibirFamilia(request, response);
		}
	}
	
	protected void exibirVoluntario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = null;

		try {
			id = Integer.valueOf(request.getParameter("id"));
		} catch (NumberFormatException e) {
			System.err.println(e);
		}
		
		if(id != null) { // parâmetro id não está ausente
			Voluntario voluntario = new Voluntario();
			voluntario.setId(id);
			boolean encontrado = voluntario.selecionarVoluntario();
						
			if(encontrado) { // esse usuário existe no BD
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
}