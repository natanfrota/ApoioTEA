<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="modelo.Conversa"%>
<%@page import="modelo.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Mensagem" %>
<% Usuario usuarioOnline = (Usuario) session.getAttribute("usuario"); 
   Conversa conversa = (Conversa) request.getAttribute("conversa");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ApoioTEA - Chat</title>
    <link rel="stylesheet" href="css/chat.css">
</head>
<body onload="rolarBarra()">
    <div class="barraTarefas">
        <h1>ApoioTEA</h1>
        <nav>
            <ul>
                <li><a href="inicio-<%= usuarioOnline.getTipo() %>">Início</a></li>
				<li><a href="perfil-<%= usuarioOnline.getTipo() %>?id=<%= usuarioOnline.getId()%>">Perfil</a></li>
				<li><a href="atividades-agendadas-<%= usuarioOnline.getTipo() %>">Atividades agendadas</a></li>
				<li><a href="conversas">Conversas</a></li>
				<li><a href="#">Notificações</a></li>
				<li><a href="sair">Sair</a></li>
            </ul>
        </nav>
    </div>
    <div class="conteudo">
        <header class="chat-header">
            <img src="" alt="foto do destinatário">
            <h2><%= conversa.getUsuario2().getNome() %></h2>
        </header>

        <main class="chat-area">
            <%	DateTimeFormatter dt = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
            	List<Mensagem> mensagens = conversa.getMensagens();
                if (mensagens != null) {
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

        <footer class="caixa-enviar">
            <form action="enviar-mensagem" method="post">
                <input type="hidden" name="conversaId" value="<%= conversa.getId() %>">
                <input type="text" name="conteudo" placeholder="Digite sua mensagem..." required>
                <button type="submit">Enviar</button>
            </form>
        </footer>
    </div>
    <script> 
    	function rolarBarra(){
    		let area = document.getElementsByClassName('chat-area')[0];
    		area.scrollTop = area.scrollHeight;
    	}
    </script>
</body>
</html>
