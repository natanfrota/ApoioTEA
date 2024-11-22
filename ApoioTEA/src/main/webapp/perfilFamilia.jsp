<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="modelo.Familia" %>
<%@ page import="modelo.Atividade" %>

<% Familia familia = (Familia) session.getAttribute("familia");
	if(familia == null){
		familia = new Familia();
	}
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
                <li><a href="#">Início</a></li>
                <li><a href="#">Perfil</a></li>
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
                    <a href=#><button class="botao-editarperfil">Editar perfil</button></a>
                </div>
            </div>
        </div>
  
        <div class="nova-atividade">
            <img src="WhatsApp Image 2024-11-20 at 15.57.03.jpeg" alt="Foto da família" class="publicarAtividade-foto">
            <div class="conteudo-texto-botao">
                <textarea placeholder="Publicar uma nova atividade:"></textarea>
                <button class="botao-publicar">Publicar</button>
            </div>
        </div>
        <div class="atividades-secao">
            <h3>Suas atividades</h3>
            
            <%  Atividade atividade = new Atividade();
            
            %>
            
            
            <div class="atividade-cartao">
                <h4><%=atividade.getTitulo() %></h4>
                <p>Data: <%=atividade.getData() %> 
                   Horário: <%=atividade.getHora() %></p>
                <p>Localização: <%= atividade.getLocalizacao() %></p>
                <p class="atividade-descricao">
                    Descrição: <%=atividade.getDescricao() %>
                </p>
                <div class="atividade-botoes">
                    <button>Editar</button>
                    <button>Voluntários</button>
                    <button>Cancelar</button>
                    <button>Excluir</button>
                </div>
            </div>
        </div>
	</div>
</body>
</html>