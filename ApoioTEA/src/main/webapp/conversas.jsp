<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Conversa" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ApoioTEA - Conversas</title>
    <link rel="stylesheet" href="css/conversas.css">
</head>
<body>
    <div class="barraTarefas">
        <h1>ApoioTEA</h1>
        <nav>
            <ul>
                <li><a href="inicio">Início</a></li>
                <li><a href="perfil">Perfil</a></li>
                <li><a href="atividades">Atividades agendadas</a></li>
                <li><a href="conversas">Conversas</a></li>
                <li><a href="notificacoes">Notificações</a></li>
                <li><a href="sair">Sair</a></li>
            </ul>
        </nav>
    </div>
    <div class="conteudo">
        <header>
            <h2>Conversas</h2>
            <input type="text" placeholder="Buscar conversa...">
        </header>

        <main>
            <%
                List<Conversa> conversas = (List<Conversa>) request.getAttribute("conversas");
                if (conversas != null) {
                    for (Conversa conversa : conversas) {
            %>
                        <div class="conversa">
                            <img src="<%= conversa.getAvatar() != null ? conversa.getAvatar() : "imagens/img-perfil-padrao.jpeg" %>" alt="Avatar">
                            <div class="detalhes">
                                <h3><%= conversa.getTitulo() %></h3>
                                <p><%= conversa.getUltimaMensagem() != null ? conversa.getUltimaMensagem() : "Sem mensagens ainda" %></p>
                            </div>
                            <span class="hora"><%= conversa.getUltimaAtualizacao() != null ? conversa.getUltimaAtualizacao() : "" %></span>
                            <a href="chat?conversaId=<%= conversa.getId() %>">Abrir</a>
                        </div>
            <%
                    }
                } else {
            %>
                <p>Não há conversas disponíveis.</p>
            <%
                }
            %>
        </main>
    </div>
</body>
</html>
