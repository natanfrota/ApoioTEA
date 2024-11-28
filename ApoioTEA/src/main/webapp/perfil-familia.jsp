<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ page import="modelo.Usuario" %>
<%@ page import="modelo.Familia" %>
<%@ page import="modelo.Atividade" %>
<%@ page import="modelo.Voluntario" %>
<%@page import="java.time.format.DateTimeFormatter"%>

<%  Usuario usuarioDaSessao = (Usuario) session.getAttribute("usuario");

	Familia familia = (Familia) request.getAttribute("familia"); 
	List<Atividade> atividades = familia.getAtividades(); 
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ApoioTEA - <%=familia.getNome() %></title>
    <link rel="stylesheet" href="css/perfil1.css">
</head>
<body>
    <div class="barraTarefas">
        <h1>ApoioTEA</h1>
        <nav>
            <ul>
                <li><a href="inicio-<%= usuarioDaSessao.getTipo()%>">Início</a></li>
                <li><a href="perfil-<%=usuarioDaSessao.getTipo() %>?id=<%= usuarioDaSessao.getId()%>">Perfil</a></li>
                <li><a href="#">Atividades agendadas</a></li>
                <li><a href="#">Conversas</a></li>
                <li><a href="#">Notificações</a></li>
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
					<h1 class="perfil-name"><%=familia.getNome()%></h1>
					<ul class="attributes">
						<li><strong>Cidade:</strong> <%=familia.getCidade()%></li>
						<li><strong>Estado:</strong> <%=familia.getEstado()%></li>
					</ul>
				</div>
			</div>
			<ul class="attributes">
				<li><strong>Descrição:</strong> <%=familia.getDescricao()%></li>
			</ul>
			 
		</div>
		<% if(usuarioDaSessao != null && familia.getId() 
                    		== usuarioDaSessao.getId()){ %>
                    	<button class="botao-editarperfil">Editar perfil</button>
                	<% } %>
		<% if(usuarioDaSessao != null && familia.getId() == usuarioDaSessao.getId()){ %>
		<header>
            <div class="header-botao">
                <button class="botao-publicar" onclick="window.location.href='publicar-atividade.jsp'">
                Publicar uma nova atividade</button>
            </div>
        </header>
        <% } %>
        <main>
            <h2>Atividades</h2>
            
             <%
           DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
           int indice = 0;
           for(Atividade atividade : atividades) { %>
            
            <div class="atividade">
                <div class="info">
                    <h3><%=atividade.getTitulo() %></h3>
                    <p><strong>Data:</strong><%=atividade.getData().format(dt) %></p>
                    <p><strong>Hora:</strong> <%=atividade.getHora() %></p>
                    <p><strong>Localização:</strong> <%= atividade.getLocalizacao() %></p>
                    <p><strong>Tipo:</strong> <%=atividade.getCategoria() %></p>
                    <p><strong>Descrição:</strong>  <%=atividade.getDescricao() %></p>
                </div>
                
                <% if(usuarioDaSessao != null && familia.getId() == usuarioDaSessao.getId()){ %>
                	<div class="botoes">
                    	<button type="button" onclick="window.location.href='editar-atividade?atividadeId=<%=atividade.getId()%>'">
                    	Editar</button>
                    	<% if(atividade.getVoluntariosCandidatos() != null && 
                    			atividade.getVoluntariosCandidatos().size() > 0){%>
                    	<button type="button" onclick="exibirVoluntarios(<%= indice %>)">Voluntários</button>
                    	<% } %>
                    	<button type="button" onclick="window.location.href='excluir-atividade?atividadeId=<%=atividade.getId()%>'">
                    	Excluir</button>
                	</div> 
               	<% indice++; } %>
               	
               	<div class="voluntarios">
               	<% if(atividade.getVoluntariosCandidatos() != null){%>
               		<h4>Voluntários</h4>
            	 	<% for(Voluntario v : atividade.getVoluntariosCandidatos()){ %>
            	 		
					<div class="exibicao-voluntarios">
						<span class="nome"><%=v.getNome()%></span> 
							<span class="botoes">
								<button type="button">Conversar</button>
								<button type="button">Aceitar</button>
							</span>
					</div>
            	 	
            	 <% }	
            } %> 
            	 </div>
            	 
            </div>
            <% } %>
        </main>
    </div>
    <script src="js/exibicaoVoluntarios.js"></script>
</body>
</html>
