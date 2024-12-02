<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="modelo.Familia"%>
<%@ page import="modelo.Voluntario"%>
<%  Familia familia = (Familia) session.getAttribute("usuario");
	Voluntario voluntario = (Voluntario) request.getAttribute("voluntario");
 %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>ApoioTEA</title>
<link rel="stylesheet" href="css/avaliacao.css">
</head>
<body>
	<div class="barraTarefas">
		<h1>ApoioTEA</h1>
		<nav>
			<ul>
				<li><a href="inicio-familia">Início</a></li>
				<li><a href="perfil-familia?id=<%= familia.getId()%>">Perfil</a></li>
				<li><a href="atividades-agendadas-familia">Atividades agendadas</a></li>
				<li><a href="conversas">Conversas</a></li>
				<li><a href="notificacoes">Notificações</a></li>
				<li><a href="sair">Sair</a></li>
			</ul>
		</nav>
	</div>
    <div class="centralizada">
        <h2>Avaliação do(a) voluntário(a) </h2>
        <div class="nome"><%= voluntario.getNome() %></div>
        
        <form action="salvar-avaliação" method="get">
            
            <label for="nota">Avaliação (1 a 5):</label>
            <select id="nota" name="nota" required>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select>

            <label for="comentario">Comentários:</label>
            <textarea id="comentario" name="comentario" rows="4"></textarea>
            
            <input type="hidden" name="voluntarioId" value="<%= voluntario.getId() %>">
            
            <button type="submit">Enviar avaliação</button>
        </form>
    </div>
</body>
</html>