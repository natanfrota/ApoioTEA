package controlador;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Voluntario;

/**
 * Servlet implementation class PerfilVoluntarioControlador
 */
@WebServlet(urlPatterns = {"/perfilvoluntario"})
public class PerfilVoluntarioControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PerfilVoluntarioControlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				RequestDispatcher rd = request.getRequestDispatcher("perfilvoluntario.jsp");
				rd.forward(request, response);
				return;
			}
		}
		response.sendRedirect("erro.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
