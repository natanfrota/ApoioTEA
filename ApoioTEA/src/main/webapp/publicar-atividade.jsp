<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="modelo.Familia"%>
<% Familia familia = (Familia) session.getAttribute("usuario"); %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ApoioTEA</title>
    <link rel="stylesheet" href="css/publicar-atividade.css">
</head>
<body>
    <div class="barraTarefas">
        <h1>ApoioTEA</h1>
        <nav>
            <ul>
                <li><a href="inicio-familia">Início</a></li>
                <li><a href="perfil-familia?id=<%= familia.getId()%>">Perfil</a></li>
                <li><a href="#">Atividades</a></li>
                <li><a href="#">Conversas</a></li>
                <li><a href="#">Notificações</a></li>
                <li><a href="#">Sair</a></li>
            </ul>
        </nav>
    </div>
    <div class="conteudo">
        <header>
            <h2>Publicar uma nova atividade</h2>
        </header>
        <main>
            <form class="formulario" action="publicar" method="get">
                <div class="form-grupo">
                    <label for="titulo">Título da atividade:</label>
                    <input type="text" id="titulo" name="titulo" placeholder="Digite o título da atividade" required>
                </div>

                <div class="form-grupo">
                    <label for="data">Data:</label>
                    <input type="date" id="data" name="data" 
                    min="<%= LocalDate.now() %>" max="<%= LocalDate.now().plusYears(3) %>" required>
                </div>

                <div class="form-grupo">
                    <label for="hora">Hora:</label>
                    <input type="time" id="hora" name="hora" required>
                </div>

                <div class="form-grupo">
                    <label for="localizacao">Localização:</label>
                    <input type="text" id="localizacao" name="localizacao" placeholder="Informe o local da atividade" required>
                </div>

                <div class="form-grupo">
                    <label for="descricao">Descrição:</label>
                    <textarea id="descricao" name="descricao" rows="5" placeholder="Descreva a atividade..." required></textarea>
                </div>

                <div class="form-grupo">
                    <label for="tipo">Tipo da atividade:</label>
                    <select id="tipo" name="tipo" required>
                    	<option value="">Selecione</option>
                        <option value="Saúde">Saúde</option>
                        <option value="Educação">Educação</option>
                        <option value="Evento">Evento</option>
                        <option value="Evento">Outro</option>
                    </select>
                </div>

                <div class="form-grupo botoes">
                    <button type="submit" class="btn-publicar">Publicar</button>
                    <button type="reset" class="btn-cancelar">Cancelar</button>
                </div>
            </form>
        </main>
    </div>
</body>
</html>
