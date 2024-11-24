<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="modelo.Atividade"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% List<Atividade> atividades = (List<Atividade>) request.getAttribute("atividades");%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ApoioTEA</title>
    <link rel="stylesheet" href="css/atividades.css">
</head>
<body>
    <div class="barraTarefas">
        <h1>ApoioTEA</h1>
        <nav>
            <ul>
                <li><a href="inicio-voluntario">Início</a></li>
                <li><a href="#">Perfil</a></li>
                <li><a href="#">Atividades</a></li>
                <li><a href="#">Conversas</a></li>
                <li><a href="#">Notificações</a></li>
                <li><a href="#">Sair</a></li>
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
            <h4>Postado por: <a href="perfil_familia.html" class="link-familia"> </a></h4>
            <p><strong>Data:</strong> <%= atv.getData().format(dt) %> <strong>Hora:</strong> <%= atv.getHora() %></p>
            <p><strong>Localização:</strong> <%= atv.getLocalizacao() %></p>
            <p class="descricao"><%= atv.getDescricao() %></p>
            <button class="botao-voluntariar" onclick="voluntariar('<%= atv.getId() %>')">
            Voluntariar-se</button>
        </div>        	
		<% } %>
    </div>

	<script src="js/voluntariar.js"></script>
</body>
</html>

