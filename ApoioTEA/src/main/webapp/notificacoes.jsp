<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="modelo.Notificacao"%>
<%@page import="modelo.Usuario"%>
<%@page import="java.util.List"%>
<% Usuario usuarioOnline = (Usuario) session.getAttribute("usuario"); 
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ApoioTEA</title>
    <link rel="stylesheet" href="css/notificacoes1.css">
</head>
<body>
    <div class="barraTarefas">
        <h1>ApoioTEA</h1>
        <nav>
            <ul>
                <li><a href="inicio-<%= usuarioOnline.getTipo() %>">Início</a></li>
				<li><a href="perfil-<%= usuarioOnline.getTipo() %>?id=<%= usuarioOnline.getId()%>">Perfil</a></li>
				<li><a href="atividades-agendadas-<%= usuarioOnline.getTipo() %>">Atividades agendadas</a></li>
				<li><a href="conversas">Conversas</a></li>
				<li><a href="notificacoes">Notificações</a></li>
				<li><a href="sair">Sair</a></li>
            </ul>
        </nav>
    </div>
    <div class="conteudo">
        <header>
            <h2>Notificações</h2>
        </header>
        <main>
        <% List<Notificacao> notificacoes = (List<Notificacao>) request.getAttribute("notificacoes");
            DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        	for(Notificacao notificacao : notificacoes){ %>
	            <div class="notificacao">
	                <h3><%= notificacao.getDescricao() %></h3>
	                <span class="horario"><%= notificacao.getData().format(dt) %></span>
	            </div>
	    <% } %>
        </main>
    </div>
</body>
</html>
