package controlador;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.AtividadeDAO;
import modelo.Atividade;
import modelo.Avaliacao;
import modelo.Familia;
import modelo.Voluntario;


@WebServlet(urlPatterns = {"/perfil-voluntario", "/perfil-familia", "/inicio-voluntario",
		"/inicio-familia", "/salvar-edicao-perfil-familia", "/salvar-edicao-perfil-voluntario",
		"/sair"})
@MultipartConfig (
		location = "C:\\Users\\Sistema2\\git\\ApoioTEA\\ApoioTEA\\src\\main\\webapp\\imagens",
		maxFileSize = 1024 * 1024 * 100
		)
public class PerfilControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AtividadeDAO atividadeDAO = new AtividadeDAO();
    
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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getServletPath();
		System.out.println(action);
		
		if(action.equals("/salvar-edicao-perfil-familia")) {
			salvarAlteracoesPerfilFamilia(request, response);
		} else if(action.equals("/salvar-edicao-perfil-voluntario")) {
			salvarAlteracoesPerfilVoluntario(request, response);
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
				Avaliacao avaliacao = new Avaliacao();
				List<Avaliacao> avaliacoes = avaliacao.selecionarAvaliacoesDeUmVoluntario(voluntario.getId());
				request.setAttribute("avaliacoes", avaliacoes);
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
				familia.setAtividades(atividadeDAO.selecionarAtividadesDeUmaFamilia(
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
			List<Atividade> atividades = atividadeDAO.selecionarTodasAsAtividades();
			request.setAttribute("atividades", atividades);
			RequestDispatcher rd = request.getRequestDispatcher("inicio-voluntario.jsp");
			rd.forward(request, response);
		}
	}
	
	protected void exibirPaginaInicialDaFamilia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessao = request.getSession(false);
		Familia familia = (Familia) sessao.getAttribute("usuario");
		
		if(familia != null) {
			familia.setAtividades(atividadeDAO.selecionarAtividadesDeUmaFamilia(
					familia.getId()));
			response.sendRedirect("inicio-familia.jsp");
		}
	}
	
	protected void salvarAlteracoesPerfilVoluntario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String dataNascimento = request.getParameter("data_nascimento");
		String cidade = request.getParameter("cidade");
		String estado = request.getParameter("estado");
		String descricao = request.getParameter("descricao");
		String experiencia = request.getParameter("experiencia");
		String habilidades = request.getParameter("habilidades");
		Part foto = request.getPart("foto");
		
		if(nome != null && email != null && dataNascimento != null && 
				cidade != null && estado != null && descricao != null && experiencia != null &&
				habilidades != null) {
			
			String nomeFoto = null;
			if(foto != null && foto.getSize() > 0) {
				nomeFoto = LocalDate.now() + foto.getSubmittedFileName();
				try {
					foto.write(nomeFoto);
				} catch(IOException e) {
					System.err.println(e);
				}
				
				if(nomeFoto != null)
					nomeFoto = "imagens/" + nomeFoto;
			}
			
			HttpSession sessao = request.getSession(false);
			Voluntario voluntario = (Voluntario) sessao.getAttribute("usuario");
			
			if(voluntario != null) {
				voluntario.setNome(nome);
				voluntario.setEmail(email);
				voluntario.setDataNascimento(LocalDate.parse(dataNascimento));
				voluntario.setCidade(cidade);
				voluntario.setEstado(estado);
				voluntario.setDescricao(descricao);
				voluntario.setExperiencia(experiencia);
				voluntario.setHabilidades(habilidades);
				voluntario.setFoto(nomeFoto);
				
				voluntario.alterarDadosPerfil();
				
				response.sendRedirect("perfil-voluntario?id=" + voluntario.getId());
			}
		}
	}
	
	protected void salvarAlteracoesPerfilFamilia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String dataNascimento = request.getParameter("data_nascimento");
		String cidade = request.getParameter("cidade");
		String estado = request.getParameter("estado");
		String descricao = request.getParameter("descricao");
		Part foto = request.getPart("foto");
		
		System.out.println(foto);
		
		if(nome != null && email != null && dataNascimento != null && 
				cidade != null && estado != null && descricao != null) {
			
			String nomeFoto = null;
			if(foto != null && foto.getSize() > 0) {
				nomeFoto = LocalDate.now() + foto.getSubmittedFileName();
				try {
					foto.write(nomeFoto);
				} catch(IOException e) {
					System.err.println(e);
				}
				
				if(nomeFoto != null)
					nomeFoto = "imagens/" + nomeFoto;
			}
			
			HttpSession sessao = request.getSession(false);
			Familia familia = (Familia) sessao.getAttribute("usuario");
			
			if(familia != null) {
				familia.setNome(nome);
				familia.setEmail(email);
				familia.setDataNascimento(LocalDate.parse(dataNascimento));
				familia.setCidade(cidade);
				familia.setEstado(estado);
				familia.setDescricao(descricao);
				familia.setFoto(nomeFoto);
				
				familia.alterarDadosPerfil();
				
				response.sendRedirect("perfil-familia?id=" + familia.getId());
			}
		}
	}
	
	protected void sair(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessao = request.getSession(false);
		sessao.invalidate();
		response.sendRedirect("login.jsp");
	}
}