<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Conversa" %>
<%@ page import="modelo.Usuario" %>
<% Usuario usuarioOnline = (Usuario) session.getAttribute("usuario"); 
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ApoioTEA - Conversas</title>
    <link rel="stylesheet" href="css/conversas1.css">
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
            <h2>Conversas</h2>
        </header>

        <main>
             <% List<Conversa> conversas = (List<Conversa>) request.getAttribute("conversas");
                if (conversas != null) {
                    for (Conversa conversa : conversas) { %>
                        <div class="conversa">
                            <div class="detalhes">
                                <a href="conversar?usuario2Id=<%= conversa.getUsuario2().getId() %>">
                                <h3><%= conversa.getUsuario2().getNome() %></h3>
                                </a>                                
                            </div>
                        </div>
            		<% } %>
           	<% } else { %>
                <p>Não há conversas disponíveis.</p>
            <% } %>
        </main>
    </div>
</body>
</html>
