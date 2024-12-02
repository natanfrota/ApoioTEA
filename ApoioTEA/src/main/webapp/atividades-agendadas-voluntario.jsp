<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="modelo.Atividade"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="modelo.Voluntario"%>
<% Voluntario voluntario = (Voluntario) session.getAttribute("usuario");
	List<Atividade> atividadesAgendadas = (List<Atividade>) request.getAttribute("atividadesAgendadas");
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ApoioTEA</title>
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
				<li><a href="notificacoes">Notificações</a></li>
				<li><a href="sair">Sair</a></li>
			</ul>
		</nav>
	</div>
	<div class="main-content">
		<h2>Atividades agendadas</h2>
		<% if(atividadesAgendadas != null && atividadesAgendadas.size() > 0) {
	        DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
	        for(Atividade atividade : atividadesAgendadas) { %>
			<div class="atividade-cartao">
				<h3><%= atividade.getTitulo() %></h3>
				<h4>
					Postado por: <a
						href="perfil-familia?id=<%=atividade.getFamilia().getId() %>"
						class="link-familia"> <%= atividade.getFamilia().getNome() %>
					</a>
				</h4>
				<p>
					<strong>Data:</strong>
					<%= atividade.getData().format(dt) %>
				</p>
				<p>
					<strong>Hora:</strong>
					<%= atividade.getHora() %></p>
				<p>
					<strong>Localização:</strong>
					<%= atividade.getLocalizacao() %></p>
				<p>
					<strong>Tipo:</strong>
					<%= atividade.getCategoria() %></p>
				<p class="descricao">
					<strong>Descrição: </strong><%= atividade.getDescricao() %></p>
	
	
				
				    <button class="botao-voluntariar" type="button"
				        onclick="window.location.href='cancelar-candidatura-confirmada?atividadeId=<%= atividade.getId() %>'">
				        Cancelar presença
				    </button>
				    <button class="botao-voluntariar" type="button" 
				    	onclick="window.location.href='conversar?usuario2Id=<%= atividade.getFamilia().getId() %>'">
        				Conversar
        			</button>
			</div>
			<% } %>
		<% } %>
	</div>
</body>
</html>

