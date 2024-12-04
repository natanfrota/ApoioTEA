<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="modelo.Conversa"%>
<%@page import="modelo.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Mensagem" %>
<% Usuario usuarioOnline = (Usuario) session.getAttribute("usuario"); 
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ApoioTEA - Chat</title>
    <link rel="stylesheet" href="css/chat1.css">
</head>
<body>
    <main class="chat-area">
            <%	DateTimeFormatter dt = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
            	List<Mensagem> mensagens = (List<Mensagem>) request.getAttribute("mensagens") ;
                if (mensagens != null && !mensagens.isEmpty()) {
                    for (Mensagem mensagem : mensagens) {
            %>
                        <div class="mensagem <%= mensagem.getRemetente().getId() == usuarioOnline.getId() ? "enviada" : "recebida" %>">
                            <p><%= mensagem.getConteudo() %></p>
                            <span><%= mensagem.getDataHoraDeEnvio().format(dt) %></span>
                        </div>
            <%
                    }
                } else {
            %>
                <p>Não há mensagens para exibir.</p>
            <%
                }
            %>
    </main>
</body>
</html>
