<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="modelo.Familia"%>
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
		<h2>Lista de famílias</h2>
		<table>
			<tr>
				<td>Nome</td>
				<td>Email</td>
				<td>Estado da conta</td>
				<td>Ações</td>
			</tr>
			<% List<Familia> familias = (List<Familia>) request.getAttribute("familias"); 
			for(Familia familia: familias) { %>
			<tr>
				<td><%= familia.getNome() %></td>
				<td><%= familia.getEmail() %></td>
				<td><%= familia.getStatusConta() %></td>
			 	<td class="actions">
            		<button type="button" class="botao">Perfil</button>
            		<button type="button" class="botao" onclick="window.location.href='exibir-conversas?usuarioId=<%= familia.getId() %>'">Conversas</button>
            		<% if(familia.getStatusConta().equals("ativa")) { %>
            			<button type="button" class="botao" onclick="window.location.href='suspender-conta?usuarioId=<%= familia.getId() %>'">Suspender</button>
            		<%} else if (familia.getStatusConta().equals("suspensa")) { %>
            			<button type="button" class="botao" onclick="window.location.href='reativar-conta?usuarioId=<%= familia.getId() %>'">Reativar</button>
            		<% } %>
          		</td>
			</tr>
			<% } %>
		</table>
	
	</section>
</body>
</html>