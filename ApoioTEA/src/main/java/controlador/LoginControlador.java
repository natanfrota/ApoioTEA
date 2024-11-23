package controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Familia;
import modelo.Usuario;
import modelo.Voluntario;

/**
 * Servlet implementation class LoginControlador
 */
@WebServlet(urlPatterns = {"/login"})
public class LoginControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginControlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		Usuario usuario;
		if(email != null && senha != null) {
			usuario = new Usuario();
			usuario.setEmail(email);
			usuario.setSenha(senha);
			
			if (usuario.fazerLogin()) {
				HttpSession sessao = request.getSession();
				if(usuario.getTipo().equals("voluntario")) {
					Voluntario v = new Voluntario();
					v.setId(usuario.getId());
					v.selecionarVoluntario();
					sessao.setAttribute("usuario", v);
					response.sendRedirect("inicioVoluntario.jsp");
				} else if(usuario.getTipo().equals("familia")){
					Familia f = new Familia();
					f.setId(usuario.getId());
					f.selecionarFamilia();
					sessao.setAttribute("usuario", f);
					response.sendRedirect("inicioFamilia.jsp");
				}
			} else {
				response.sendRedirect("login.jsp");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
