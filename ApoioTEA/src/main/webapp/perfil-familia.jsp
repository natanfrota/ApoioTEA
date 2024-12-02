<%@page import="java.time.LocalTime"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ page import="modelo.Usuario"%>
<%@ page import="modelo.Familia"%>
<%@ page import="modelo.Atividade"%>
<%@ page import="modelo.Voluntario"%>
<%@page import="java.time.format.DateTimeFormatter"%>

<%  Usuario usuarioDaSessao = (Usuario) session.getAttribute("usuario");

	Familia familia = (Familia) request.getAttribute("familia"); 
	List<Atividade> atividades = familia.getAtividades(); 
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ApoioTEA - <%=familia.getNome() %></title>
<link rel="stylesheet" href="css/perfil1.css">
</head>
<body>
	<div class="barraTarefas">
		<h1>ApoioTEA</h1>
		<nav>
			<ul>
				<li><a href="inicio-<%= usuarioDaSessao.getTipo()%>">Início</a></li>
				<li><a href="perfil-<%=usuarioDaSessao.getTipo() %>?id=<%= usuarioDaSessao.getId()%>">Perfil</a></li>
				<li><a href="atividades-agendadas-familia">Atividades agendadas</a></li>
				<li><a href="conversas">Conversas</a></li>
				<li><a href="notificacoes">Notificações</a></li>
				<li><a href="sair">Sair</a></li>
			</ul>
		</nav>
	</div>
	<div class="conteudo">
		<div class="perfil-container">
			<div class="perfil-header">
				<div class="img-container">
					<img src="imagens/img-perfil-padrao.jpeg" alt="Foto de Perfil"
						class="perfil-photo">
				</div>
				<div class="perfil-info">
					<h1 class="perfil-name"><%=familia.getNome()%></h1>
					<ul class="attributes">
						<li><strong>Cidade:</strong> <%=familia.getCidade()%></li>
						<li><strong>Estado:</strong> <%=familia.getEstado()%></li>
					</ul>
				</div>
			</div>
			<ul class="attributes">
				<li><strong>Descrição:</strong> <%=familia.getDescricao()%></li>
			</ul>
		</div>
		<% if(usuarioDaSessao != null && familia.getId() 
                    		== usuarioDaSessao.getId()){ %>
			<button class="botao-editarperfil" type="button"onclick="window.location.href='editar-perfil-familia.jsp'">
			Editar perfil</button>
		<% } %>
		<% if(usuarioDaSessao != null && familia.getId() == usuarioDaSessao.getId()){ %>
			<header>
				<div class="header-botao">
					<button class="botao-publicar"
					onclick="window.location.href='publicar-atividade.jsp'">
					Publicar uma nova atividade</button>
				</div>
			</header>
		<% } %>
		<main>
			<h2>Atividades</h2>

			<%
           	DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
            int indice = 0;
            for(Atividade atividade : atividades) { %>
            	<div class="atividade">
            		<div class="info">
						<h3><%=atividade.getTitulo() %></h3>
						<p>
							<strong>Data: </strong><%=atividade.getData().format(dt) %></p>
						<p>
							<strong>Hora:</strong>
							<%=atividade.getHora() %></p>
						<p>
							<strong>Localização:</strong>
							<%= atividade.getLocalizacao() %></p>
						<p>
							<strong>Tipo:</strong>
							<%=atividade.getCategoria() %></p>
						<p>
							<strong>Descrição:</strong>
							<%=atividade.getDescricao() %></p>
							
						<% if(atividade.getVoluntarioEscolhido() != null && 
						usuarioDaSessao != null && familia.getId() == usuarioDaSessao.getId()) {%>
							<p><strong>Voluntário(a) escolhido(a): </strong>
								<a href="perfil-voluntario?id=<%= atividade.getVoluntarioEscolhido().getId() %>">
								<%= atividade.getVoluntarioEscolhido().getNome() %>
								</a>
							</p>
						<% } %>
            		</div> <%-- fechamento da div .info --%>
            		<% if(usuarioDaSessao != null && familia.getId() == usuarioDaSessao.getId()){ %>
						<div class="botoes">
							<button type="button"
								onclick="window.location.href='editar-atividade?atividadeId=<%=atividade.getId()%>'">
								Editar</button>
							<% if(atividade.getVoluntariosCandidatos() != null && 
		                    			atividade.getVoluntariosCandidatos().size() > 0){%>
								<button type="button" onclick="exibirVoluntarios(<%= indice %>)">Voluntários</button>
							<% } %>
							<% indice++; %>
								<button type="button"
									onclick="window.location.href='excluir-atividade?atividadeId=<%=atividade.getId()%>'">
									Excluir</button>
							
								<%
								LocalDate hoje = LocalDate.now();
								LocalTime agora = LocalTime.now();
								LocalDate diaAtividade = atividade.getData();
								LocalTime horaAtividade = atividade.getHora();
							
								if(atividade.getVoluntarioEscolhido() != null && (hoje.isBefore(diaAtividade) || (hoje.equals(diaAtividade) && agora.isBefore(horaAtividade)))){ %>
										<button type="button"
										onclick="window.location.href='remover-voluntario-escolhido?atividadeId=<%=atividade.getId()%>&voluntarioId=<%= atividade.getVoluntarioEscolhido().getId() %>'">
										Remover voluntário</button>
								<% } else if(atividade.getVoluntarioEscolhido() != null && ((hoje.isEqual(diaAtividade) && agora.isAfter(horaAtividade) || 
										hoje.isAfter(diaAtividade)))) { %>
										<button type="button"
										onclick="window.location.href='marcar-como-realizada?atividadeId=<%=atividade.getId()%>&voluntarioId=<%= atividade.getVoluntarioEscolhido().getId() %>'">
										Marcar como concluída</button>
								<% } %>
						<% } %>
						</div> <%-- fechamento da div .botoes --%>
            			<div class="voluntarios">
						<% if(atividade.getVoluntariosCandidatos() != null){%>
							<h4>Voluntários</h4>
							<% for(Voluntario v : atividade.getVoluntariosCandidatos()){ %>
								<div class="exibicao-voluntarios">
									<span class="nome"><a href="perfil-voluntario?id=<%= v.getId() %>"> 
										<%=v.getNome()%> </a></span> 
										<% if(atividade.getVoluntarioEscolhido() == null) { %>
											<span class="botoes">
												<button type="button" onclick="window.location.href='conversar?usuario2Id=<%= v.getId() %>'">
	        									Conversar</button>
	    										<button type="button" onclick="window.location.href='aceitar-voluntario?atividadeId=<%= atividade.getId() %>&voluntarioId=<%= v.getId() %>'">
	        									Aceitar</button>
											</span>
										<%} else if(atividade.getVoluntarioEscolhido() != null && atividade.getVoluntarioEscolhido().getId()
												== v.getId()) { %>
											<span class="botoes">
												<button type="button" onclick="window.location.href='conversar?usuario2Id=<%= v.getId() %>'">
	        									Conversar</button>
	        								</span>
	        				   			<%}%>
								</div>
							
							<% } %>
						<% } %>
            		   </div> <%-- fechamento da div voluntarios --%>
            		   <%if(usuarioDaSessao.getTipo().equals("voluntario")){ 
							
							boolean candidatoNaoEscolhido = atividade.getVoluntarioEscolhido() == null 
							                                || atividade.getVoluntarioEscolhido().getId() != usuarioDaSessao.getId();
							boolean podeVoluntariar = !atividade.buscarCandidato(usuarioDaSessao.getId());
							boolean candidatoAtivo = atividade.buscarCandidato(usuarioDaSessao.getId());
							%>
				
							<% if (podeVoluntariar) { %>
							    <button class="botao-voluntariar" type="button"
							        onclick="window.location.href='adicionar-candidato?atividadeId=<%= atividade.getId() %>'">
							        Voluntariar-se
							    </button>
							<% } else if (!candidatoNaoEscolhido) { %>
							    <button class="botao-voluntariar" type="button"
							        onclick="window.location.href='cancelar-candidatura?atividadeId=<%= atividade.getId() %>'">
							        Cancelar presença
							    </button>
							    <button class="botao-voluntariar" type="button" onclick="window.location.href='conversar?usuario2Id=<%= familia.getId() %>'">
        						Conversar</button>
							<% } else if (candidatoAtivo) { %>
							    <button class="botao-voluntariar" type="button"
							        onclick="window.location.href='cancelar-candidatura-confirmada?atividadeId=<%= atividade.getId() %>'">
							        Cancelar candidatura
							    </button>
							<% } %>
						<% } %> 
            	</div> <%-- fechamento da div .atividade --%>
			<% } %>
		</main>
	</div> <%-- fechamento da div .conteudo --%>
	<script src="js/exibicaoVoluntarios.js"></script>
</body>
</html>