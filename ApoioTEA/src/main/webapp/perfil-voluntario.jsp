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
                <li>Início</li>
                <li>Perfil</li>
                <li>Atividades</li>
                <li>Conversas</li>
                <li>Notificações</li>
                <li>Sair</li>
            </ul>
        </nav>
    </div>
    <div class="main-content">
        <div class="perfil-secao">
            <div class="perfil-cartao">
                <img src="imagens/img-perfil-padrao.jpeg" alt="Foto da família" class="perfil-foto">
                <div class="perfil-info">
                    <h2>Nome: <%= voluntario.getNome() %></h2>
                    <p>Cidade: <%=voluntario.getCidade() %>, <%= voluntario.getEstado() %></p>
                    <p>Idade: <%= voluntario.calcularIdade() %></p>
                    <p>Avaliação média: <%= voluntario.getAvaliacaoMedia() %></p>
                    <p class="descricao">
                        Descrição:
                        <%= voluntario.getDescricao() %>
                    </p>
                    <p> Habilidades: <%= voluntario.getHabilidades() %></p>
                    <p> Experiências: <%= voluntario.getExperiencia() %></p>
                    
                    <% if(usuarioDaSessao != null && voluntario.getId() 
                    		== usuarioDaSessao.getId()){ %>
                    	<button class="botao-editarperfil">Editar perfil</button>
                	<% } %>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
