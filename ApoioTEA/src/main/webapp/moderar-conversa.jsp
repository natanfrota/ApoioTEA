<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="modelo.Conversa"%>
<%@page import="modelo.Mensagem"%>
<%@page import="modelo.Usuario"%>
<%@page import="java.util.List" %>
<% Conversa conversa = (Conversa) request.getAttribute("conversa"); %>
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
	<div class="caixa-conversa">
		<h2>Conversa entre <%= conversa.getUsuario1().getNome() %> e <%= conversa.getUsuario2().getNome() %></h2>
		<% List<Mensagem> mensagens = conversa.getMensagens();
		DateTimeFormatter dt = DateTimeFormatter.ofPattern("hh:mm dd/MM/yyyy");
		for(Mensagem mensagem : mensagens) { %>
		<div>
			<span class="autor"><%= mensagem.getRemetente().getNome() %></span> 
			<span class="data-hora"><%= mensagem.getDataHoraDeEnvio().format(dt) %></span>
			<p><%= mensagem.getConteudo() %></p>
			<hr>
		</div>
		<% } %>
	</div>
</body>
</html>