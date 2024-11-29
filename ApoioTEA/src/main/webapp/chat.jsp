<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Mensagem" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ApoioTEA - Chat</title>
    <link rel="stylesheet" href="css/chat.css">
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
        <header class="chat-header">
            <img src="<%= request.getAttribute("avatarContato") %>" alt="Avatar do Contato">
            <h2><%= request.getAttribute("nomeContato") %></h2>
        </header>

        <main class="chat-area">
            <%
                List<Mensagem> mensagens = (List<Mensagem>) request.getAttribute("mensagens");
                int usuarioAtualId = (int) session.getAttribute("usuarioId");
                if (mensagens != null) {
                    for (Mensagem mensagem : mensagens) {
            %>
                        <div class="mensagem <%= mensagem.getUsuarioId() == usuarioAtualId ? "enviada" : "recebida" %>">
                            <p><%= mensagem.getConteudo() %></p>
                            <span><%= mensagem.getDataHoraDeEnvio() %></span>
                        </div>
            <%
                    }
                } else {
            %>
                <p>Nenhuma mensagem ainda.</p>
            <%
                }
            %>
        </main>

        <footer class="caixa-enviar">
            <form action="chat" method="post">
                <input type="hidden" name="conversaId" value="<%= request.getParameter("conversaId") %>">
                <input type="text" name="conteudo" placeholder="Digite sua mensagem..." required>
                <button type="submit">Enviar</button>
            </form>
        </footer>
    </div>
</body>
</html>
