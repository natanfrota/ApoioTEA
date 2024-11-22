package controlador;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Familia;
import modelo.Voluntario;

/**
 * Servlet implementation class CadastroControlador
 */
@WebServlet("/cadastro")
public class CadastroControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroControlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo = (String) request.getParameter("tipo"); 
		String nome = (String) request.getParameter("nome");
		String email = (String) request.getParameter("email");
		String senha = (String) request.getParameter("senha");
		String dataNascimento = (String) request.getParameter("data_nascimento");
		String cidade = (String) request.getParameter("cidade");
		String estado = (String) request.getParameter("estado");
		String descricao = (String) request.getParameter("descricao");
		String experiencia = (String) request.getParameter("experiencia");
		String habilidades = (String) request.getParameter("habilidades");
		
		if(tipo != null && nome != null && email != null && senha != null && 
			dataNascimento != null &&	cidade != null && estado != null 
			&& descricao != null) {
			if (tipo.equalsIgnoreCase("familia")) {
				Familia familia =  new Familia(nome, email, LocalDate.parse(dataNascimento),
						cidade, estado, descricao, "ativa");
				familia.setSenha(senha);
				familia.fazerCadastro();
			}
			else if(tipo.equalsIgnoreCase("voluntario") && experiencia != null && habilidades != null) {
				Voluntario voluntario = new Voluntario(nome, email, LocalDate.parse(dataNascimento),
						cidade, estado, descricao, experiencia, habilidades, "ativa");
				voluntario.setSenha(senha);
				voluntario.fazerCadastro();
			}
		}
	}

}
