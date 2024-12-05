<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="modelo.Usuario"%>
<%@ page import="modelo.Voluntario"%>
<%@ page import="modelo.Avaliacao"%>
<%  Usuario usuarioDaSessao = (Usuario) session.getAttribute("usuario");

	Voluntario voluntario = (Voluntario) request.getAttribute("voluntario");  
	List<Avaliacao> avaliacoes = (List<Avaliacao>) request.getAttribute("avaliacoes");
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ApoioTEA</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/perfil1.css">
</head>
<body>
	<div class="barraTarefas">
		<h1>ApoioTEA</h1>
		<nav>
			<ul>
				<li><a href="inicio-<%= usuarioDaSessao.getTipo()%>">Início</a></li>
				<li><a href="perfil-<%=usuarioDaSessao.getTipo() %>?id=<%= usuarioDaSessao.getId()%>">Perfil</a></li>
				<li><a href="atividades-agendadas-voluntario">Atividades agendadas</a></li>
				<li><a href="conversas">Conversas</a></li>
				<li><a href="notificacoes">Notificações</a></li>
				<li><a href="sair">Sair</a></li>			
			</ul>
		</nav>
	</div>
	<div class="conteudo">
		<div class="perfil-container">
			<div class="perfil-header">
				<div class="img-container">
					<img src=<%= voluntario.getFoto() == null 
								? "imagens/img-perfil-padrao.jpeg" : voluntario.getFoto() %> 
								alt="Foto de Perfil"
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
				<li><strong>Descrição: </strong><%= voluntario.getDescricao() %></li>
				<li><strong>Habilidades:</strong> <%= voluntario.getHabilidades() %></li>
				<li><strong>Experiência:</strong> <%= voluntario.getExperiencia() %></li>
			</ul>
		</div>
		<% if(usuarioDaSessao != null && voluntario.getId() 
                    		== usuarioDaSessao.getId()){ %>
		<button type="button" class="botao-editarperfil" onclick="window.location.href='editar-perfil-voluntario.jsp'">
		Editar perfil</button>
		<% } %>
		<header>
			<h2>Avaliações</h2>
		</header>
		
		
		<div>
			<% 
			DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
			for (Avaliacao avaliacao: avaliacoes) { %>
            	<div class="atividade">
            		<div class="info">
						<p>Publicada por: <%= avaliacao.getFamilia().getNome() %> </p>
						<p>Data: <%= avaliacao.getData().format(dt) %>
						<p> Nota: 
						<% int nota = avaliacao.getNota();
						for (int i = 1; i <= 5; i++) { 
							if (i <= nota) { %>
								<span class="fa fa-star checked"></span>
							<% } else { %>
								<span class="fa fa-star"></span> 
							<%} 
						} %>
						</p>
						<p><%= avaliacao.getComentario() %> </p>
					</div>
				</div>
			<% } %>
		</div>
		
	</div>
</body>
</html>