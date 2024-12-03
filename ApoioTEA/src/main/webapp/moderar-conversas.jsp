<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="modelo.Conversa"%>
<%@page import="modelo.Usuario"%>
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
	<%-- Usuario usuario = (Usuario) session.getAttribute("usuario"); --%>
	<% List<Conversa> conversas = (List<Conversa>) request.getAttribute("conversas"); %>
		<h2>Conversas do usuário <%--= usuario.getNome()  --%></h2>
		<table>
			<% for(Conversa conversa: conversas) { %>
			<tr>
				<td><%= conversa.getUsuario2().getNome() %></td>
			 	<td class="actions">
            		<button type="button" class="botao" onclick="window.location.href='exibir-conversa?conversaId=<%= conversa.getId() %>'">Abrir</button>
          		</td>
			</tr>
			<% } %>
		</table>
	</section>
</body>
</html>