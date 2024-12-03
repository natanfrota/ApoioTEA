<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="modelo.Voluntario"%>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Painel administrativo</title>
    <link rel="stylesheet" href="css/moderador1.css">
</head>

<body>

    <header>
        <h1><a href="inicio-moderador">Painel de moderação</a></h1>
    </header>
	<section class="container">
		<h2>Lista de voluntários</h2>
		<table>
			<tr>
				<td>Nome</td>
				<td>Email</td>
				<td>Avaliação média</td>
				<td>Estado da conta</td>
				<td>Ações</td>
			</tr>
			<% List<Voluntario> voluntarios = (List<Voluntario>) request.getAttribute("voluntarios"); 
			for(Voluntario voluntario: voluntarios) { %>
			<tr>
				<td><%= voluntario.getNome() %></td>
				<td><%= voluntario.getEmail() %></td>
				<td><%= voluntario.getAvaliacaoMedia() %></td>
				<td><%= voluntario.getStatusConta() %></td>
			 	<td class="actions">
            		<button type="button" class="botao">Perfil</button>
            		<button type="button" class="botao" onclick="window.location.href='exibir-conversas?usuarioId=<%= voluntario.getId() %>'">Conversas</button>
            		<% if(voluntario.getStatusConta().equals("ativa")) { %>
            			<button type="button" class="botao" onclick="window.location.href='suspender-conta?usuarioId=<%= voluntario.getId() %>'">Suspender</button>
            		<%} else if (voluntario.getStatusConta().equals("suspensa")) { %>
            			<button type="button" class="botao" onclick="window.location.href='reativar-conta?usuarioId=<%= voluntario.getId() %>'">Reativar</button>
            		<% } %>
          		</td>
			</tr>
			<% } %>
		</table>
	
	</section>
</body>
</html>