<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="modelo.Usuario" %>
<%@ page import="modelo.Voluntario" %>

<%  Usuario usuarioDaSessao = (Usuario) session.getAttribute("usuario");

	Voluntario voluntario = (Voluntario) request.getAttribute("voluntario");  
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ApoioTEA</title>
    <link rel="stylesheet" href="css/perfil.css">
</head>
<body>
    <div class="barraTarefas">
        <h1>ApoioTEA</h1>
        <nav>
            <ul>
                <li><a href="inicio-<%= usuarioDaSessao.getTipo()%>">Início</a></li>
                <li><a href="perfil-<%=usuarioDaSessao.getTipo() %>?id=<%= usuarioDaSessao.getId()%>">Perfil</a></li>
                <li>Atividades agendadas</li>
                <li>Conversas</li>
                <li>Notificações</li>
                <li><a href="sair">Sair</a></li>
            </ul>
        </nav>
    </div>
    <div class="conteudo">
         <div class="perfil-container">
			<div class="perfil-header">
				<div class="img-container">
					<img src="imagens/img-perfil-padrao.jpeg" alt="Foto de Perfil"
						class="perfil-photo">
				</div>
				<div class="perfil-info">
					<h1 class="perfil-name"><%= voluntario.getNome() %></h1>
					<ul class="attributes">
						<li><strong>Cidade:</strong> <%= voluntario.getCidade() %></li>
						<li><strong>Estado:</strong> <%= voluntario.getEstado() %></li>
						<li><strong>Idade:</strong> <%= voluntario.calcularIdade() %></li>
						<li><strong>Avaliação média:</strong> <%= voluntario.getAvaliacaoMedia() %></li>
					</ul>
				</div>
			</div>
			<ul class="attributes">
				<li><strong>Descrição:</strong><%= voluntario.getDescricao() %></li>
				<li><strong>Habilidades:</strong> <%= voluntario.getHabilidades() %></li>
				<li><strong>Experiência:</strong> <%= voluntario.getExperiencia() %></li>
			</ul>
		</div>
		<% if(usuarioDaSessao != null && voluntario.getId() 
                    		== usuarioDaSessao.getId()){ %>
                    	<button class="botao-editarperfil">Editar perfil</button>
                	<% } %>
	<header>
		<h2>Avaliações</h2>
	</header>
	</div>

</body>
</html>