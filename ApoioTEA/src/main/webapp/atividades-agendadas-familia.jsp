<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.time.LocalTime"%>
<%@page import="java.time.LocalDate"%>
<%@ page import="java.util.List"%>
<%@ page import="modelo.Usuario"%>
<%@ page import="modelo.Familia"%>
<%@ page import="modelo.Atividade"%>
<%@ page import="modelo.Voluntario"%>
<%@ page import="java.time.format.DateTimeFormatter"%>

<%  Familia familia = (Familia) session.getAttribute("usuario");
	List<Atividade> atividadesAgendadas = (List<Atividade>) request.getAttribute("atividadesAgendadas");
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ApoioTEA</title>
<link rel="stylesheet" href="css/atividade-familia.css">
</head>
<body>
	<div class="barraTarefas">
		<h1>ApoioTEA</h1>
		<nav>
			<ul>
				<li><a href="inicio-familia">Início</a></li>
				<li><a href="perfil-familia?id=<%= familia.getId()%>">Perfil</a></li>
				<li><a href="#">Atividades agendadas</a></li>
				<li><a href="#">Conversas</a></li>
				<li><a href="#">Notificações</a></li>
				<li><a href="sair">Sair</a></li>
			</ul>
		</nav>
	</div>
	<div class="conteudo">
		<main>
			<h2>Atividades agendadas</h2>

			<% if(atividadesAgendadas != null && atividadesAgendadas.size() > 0) { 
           		DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
           		int indice = 0;
           		for(Atividade atividade : atividadesAgendadas) { %>

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
					
					<% if(atividade.getVoluntarioEscolhido() != null) {%>
						<p><strong>Voluntário(a) escolhido(a): </strong>
						<a href="perfil-voluntario?id=<%= atividade.getVoluntarioEscolhido().getId() %>">
						<%= atividade.getVoluntarioEscolhido().getNome() %>
						</a>
						</p>
					<% } %>
				</div>


				<div class="botoes">
					<button type="button"
						onclick="window.location.href='editar-atividade?atividadeId=<%=atividade.getId()%>'">
						Editar</button>
					<% if(atividade.getVoluntariosCandidatos() != null && 
                    			atividade.getVoluntariosCandidatos().size() > 0){%>
					<button type="button" onclick="exibirVoluntarios(<%= indice %>)">Voluntários</button>
					<% } %>
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
							Marcar como realizada</button>
					<% } %>

				</div>
				<% indice++;  %>

				<div class="voluntarios">
					<% if(atividade.getVoluntariosCandidatos() != null){%>
					<h4>Voluntários</h4>
					<% for(Voluntario v : atividade.getVoluntariosCandidatos()){ %>

					<div class="exibicao-voluntarios">
						<span class="nome"><a href="perfil-voluntario?id=<%= v.getId() %>"> 
						<%=v.getNome()%> </a></span> 
						<% if(atividade.getVoluntarioEscolhido() == null) { %>
							<span class="botoes">
								<button type="button" onclick="window.location.href='conversar?voluntarioId=<%= v.getId() %>'">
        						Conversar</button>
    							<button type="button" onclick="window.location.href='aceitar-voluntario?atividadeId=<%= atividade.getId() %>&voluntarioId=<%= v.getId() %>'">
        						Aceitar</button>
							</span>
						<%} else if(atividade.getVoluntarioEscolhido() != null && atividade.getVoluntarioEscolhido().getId()
						== v.getId()) { %>
							<span class="botoes">
								<button type="button" onclick="window.location.href='conversar?voluntarioId=<%= v.getId() %>'">
        						Conversar</button>
        					</span>
        				<%}%>
					</div>

					<% }	
            } %>
				</div>

			</div>
			<% } %>
			<% } %>
		</main>
	</div>
	<script src="js/exibicaoVoluntarios.js"></script>
</body>
</html>
