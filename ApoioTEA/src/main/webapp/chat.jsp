<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ApoioTEA - Chat</title>
    <link rel="stylesheet" href="css/chat.css">
</head>
<body>
    <div class="barraTarefas">
        <h1>ApoioTEA</h1>
        <nav>
            <ul>
                <li><a href="#">Início</a></li>
                <li><a href="#">Perfil</a></li>
                <li><a href="#">Atividades agendadas</a></li>
                <li><a href="#">Conversas</a></li>
                <li><a href="#">Notificações</a></li>
                <li><a href="sair">Sair</a></li>
            </ul>
        </nav>
    </div>
    <div class="conteudo">
        <header class="chat-header">
            <img src="imagens/img-perfil-padrao.jpeg" alt="Avatar do Contato">
            <h2>Valéria Albuquerque</h2>
        </header>

        <main class="chat-area">
            <div class="mensagem recebida">
                <p>Olá! Tudo bem?</p>
                <span>10:00</span>
            </div>
            <div class="mensagem enviada">
                <p>Oi, Valéria! Tudo ótimo e você?</p>
                <span>10:02</span>
            </div>
            <div class="mensagem recebida">
                <p>Eu estou bem! Precisamos conversar sobre a atividade da consulta médica.</p>
                <span>10:05</span>
            </div>
        </main>

        <footer class="caixa-enviar">
            <input type="text" placeholder="Digite sua mensagem...">
            <button>Enviar</button>
        </footer>
    </div>
</body>
</html>
