<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ page import="modelo.Usuario" %>
<%@ page import="modelo.Familia" %>
<%@ page import="modelo.Atividade" %>
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
<title><%=familia.getNome() %></title>
<link rel="stylesheet" href="css/perfil.css">
</head>
<body>
	<div class="barraTarefas">
        <a href="">
            <h1>ApoioTEA</h1>
        </a>
        <nav>
            <ul>
                <li><a href="inicio-<%=usuarioDaSessao.getTipo() %>">Início</a></li>
                <li><a href="perfil-<%=usuarioDaSessao.getTipo() %>?id=<%= usuarioDaSessao.getId()%>">Perfil</a></li>
                <li><a href="#">Atividades agendadas</a></li>
                <li><a href="#">Conversas</a></li>
                <li><a href="#">Notificações</a></li>
                <li><a href="#">Sair</a></li>
            </ul>
        </nav>
    </div>
    <div class="main-content">
        <div class="perfil-secao">
            <div class="perfil-cartao">
                <img src="imagens/img-perfil-padrao.jpeg" alt="Foto da família" class="perfil-foto">
                <div class="perfil-info">
                    <h2>Responsável: <%=familia.getNome() %></h2>
                    <p>Cidade: <%=familia.getCidade() %>, <%= familia.getEstado() %></p>
                   	
                    <p class="descricao">
                        Descrição: <%= familia.getDescricao()%>
                    </p>
                    
                    <% if(usuarioDaSessao != null && familia.getId() 
                    		== usuarioDaSessao.getId()){ %>
                    	<button class="botao-editarperfil">Editar perfil</button>
                	<% } %>
                </div>
            </div>
        </div>
  		
  		<% if(usuarioDaSessao != null && familia.getId() == usuarioDaSessao.getId()){ %>
        <div class="nova-atividade">
            <img src="img-perfil-padrao.jpeg" alt="Foto da família" class="publicarAtividade-foto">
            <div class="conteudo-texto-botao">
                <textarea placeholder="Publicar uma nova atividade:"></textarea>
                <button class="botao-publicar">Publicar</button>
            </div>
        </div>
        <% } %>
        
        <div class="atividades-secao">
            <h3>Atividades</h3>
            
            
           <%
           DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
           for(Atividade atividade : atividades) { %>
            <div class="atividade-cartao">
                <h4><%=atividade.getTitulo() %></h4>
                <p>Data: <%=atividade.getData().format(dt) %> 
                   Horário: <%=atividade.getHora() %></p>
                <p>Localização: <%= atividade.getLocalizacao() %></p>
                <p class="atividade-descricao">
                    Descrição: <%=atividade.getDescricao() %>
                </p>
                
                <% if(usuarioDaSessao != null && familia.getId() == usuarioDaSessao.getId()){ %>
                	<div class="atividade-botoes">
                    	<button onclick="window.location.href='editar-atividade?atividadeId=<%= atividade.getId()%>'">Editar</button>
                    	<button>Voluntários</button>
                    	<button >Excluir</button>
                	</div> 
               	<% } %>
            </div>
            <% } %>
        </div>
	</div>
</body>
</html>