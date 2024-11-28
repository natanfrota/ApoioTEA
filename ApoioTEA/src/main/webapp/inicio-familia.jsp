<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ page import="modelo.Usuario" %>
<%@ page import="modelo.Familia" %>
<%@ page import="modelo.Atividade" %>
<%@page import="java.time.format.DateTimeFormatter"%>    
    
<%  Familia familia = (Familia) session.getAttribute("usuario");
	List<Atividade> atividades = null;
	if (familia != null && familia.getAtividades() != null) {
    	atividades = familia.getAtividades(); 
	}
%>    

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ApoioTEA</title>
    <link rel="stylesheet" href="css/atividade-familia.css">
</head>
<body>
    <div class="barraTarefas">
        <h1>ApoioTEA</h1>
        <nav>
            <ul>
                <li><a href="inicio-familia">Início</a></li>
                <li><a href="perfil-familia?id=<%= familia.getId()%>">Perfil</a></li>
                <li><a href="#">Atividades agendadas</a></li>
                <li><a href="#">Conversas</a></li>
                <li><a href="#">Notificações</a></li>
                <li><a href="sair">Sair</a></li>
            </ul>
        </nav>
    </div>
    <div class="conteudo">
        <header>
            <button class="botao-publicar" onclick="window.location.href='publicar-atividade.jsp'"
            >Publicar uma nova atividade</button>
        </header>
        <main>
            <h2>Atividades publicadas por você</h2>
           
           <% if(atividades != null) { 
           		DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
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
                
                
                	<div class="botoes">
                    	<button onclick="window.location.href='editar-atividade?atividadeId=<%=atividade.getId()%>'">
                    	Editar</button>
                    	<button>Voluntários</button>
                    	<button onclick="window.location.href='excluir-atividade?atividadeId=<%=atividade.getId()%>'">
                    	Excluir</button>
                	</div> 
          </div>  	
          		<% } %>
       	<% } %>
        </main>
    </div>
</body>
</html>
