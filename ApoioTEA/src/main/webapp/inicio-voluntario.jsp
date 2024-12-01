<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="modelo.Atividade"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="modelo.Voluntario"%>
<% Voluntario voluntario = (Voluntario) session.getAttribute("usuario");
	List<Atividade> atividades = (List<Atividade>) request.getAttribute("atividades");%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ApoioTEA - Atividades</title>
<link rel="stylesheet" href="css/atividade.css">
</head>
<body>
	<div class="barraTarefas">
		<h1>ApoioTEA</h1>
		<nav>
			<ul>
				<li><a href="inicio-voluntario">Início</a></li>
				<li><a href="perfil-voluntario?id=<%= voluntario.getId()%>">Perfil</a></li>
				<li><a href="atividades-agendadas-voluntario">Atividades agendadas</a></li>
				<li><a href="conversas">Conversas</a></li>
				<li><a href="#">Notificações</a></li>
				<li><a href="sair">Sair</a></li>
			</ul>
		</nav>
	</div>
	<div class="main-content">
		<h2>Atividades</h2>
		<%
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
        for(Atividade atv : atividades) { %>
		<div class="atividade-cartao">
			<h3><%= atv.getTitulo() %></h3>
			<h4>
				Postado por: <a
					href="perfil-familia?id=<%=atv.getFamilia().getId() %>"
					class="link-familia"> <%= atv.getFamilia().getNome() %>
				</a>
			</h4>
			<p>
				<strong>Data:</strong>
				<%= atv.getData().format(dt) %>
			</p>
			<p>
				<strong>Hora:</strong>
				<%= atv.getHora() %></p>
			<p>
				<strong>Localização:</strong>
				<%= atv.getLocalizacao() %></p>
			<p>
				<strong>Tipo:</strong>
				<%= atv.getCategoria() %></p>
			<p class="descricao">
				<strong>Descrição: </strong><%= atv.getDescricao() %></p>


			
			<%
			boolean candidatoNaoEscolhido = atv.getVoluntarioEscolhido() == null 
			                                || atv.getVoluntarioEscolhido().getId() != voluntario.getId();
			boolean podeVoluntariar = !atv.buscarCandidato(voluntario.getId());
			boolean candidatoAtivo = atv.buscarCandidato(voluntario.getId());
			%>

			<% if (podeVoluntariar) { %>
			    <button class="botao-voluntariar" type="button"
			        onclick="window.location.href='adicionar-candidato?atividadeId=<%= atv.getId() %>'">
			        Voluntariar-se
			    </button>
			<% } else if (!candidatoNaoEscolhido) { %>
			    <button class="botao-voluntariar" type="button"
			        onclick="window.location.href='cancelar-candidatura-confirmada?atividadeId=<%= atv.getId() %>'">
			        Cancelar presença
			    </button>
			    <button class="botao-voluntariar" type="button" onclick="window.location.href='conversar?usuario2Id=<%= atv.getFamilia().getId() %>'">
        		Conversar</button>
			<% } else if (candidatoAtivo) { %>
			    <button class="botao-voluntariar" type="button"
			        onclick="window.location.href='cancelar-candidatura?atividadeId=<%= atv.getId() %>'">
			        Cancelar candidatura
			    </button>
			<% } %>
		</div>
		<% } %>
	</div>
</body>
</html>

