<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="modelo.Voluntario" %>

<% Voluntario voluntario = (Voluntario) session.getAttribute("voluntario");
	if(voluntario == null){
		voluntario = new Voluntario();
	}
%>


<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= (voluntario.getNome() != null ? voluntario.getNome() : "") %></title>
    <link rel="stylesheet" href="visaoF.css">
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
                <img src=<%= (voluntario.getFoto() != null ? voluntario.getFoto() : 
                	"imagens/img-perfil-padrao.jpeg") %>      
                alt="Foto do voluntário" class="perfil-foto">
				<div class="perfil-info">
					<h2>
						Nome:
						<%=(voluntario.getNome() != null ? voluntario.getNome() : "")%></h2>
					<p>Cidade:
						<%=(voluntario.getCidade() != null ? voluntario.getCidade() : "")%>
					</p>
					<p>Estado: 
						<%=voluntario.getEstado() != null ? voluntario.getEstado() : ""%>
					</p>
					<p>Idade:</p>
					<p>Avaliação média:</p>
					<div class="descricao">
						<p>Descrição:
						<%=voluntario.getDescricao() != null ? voluntario.getDescricao() : ""%>
						</p>
					</div>
				</div>
			</div>
        </div>
    </div>   
</body>
</html>