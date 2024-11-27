<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ApoioTEA</title>
    <link rel="stylesheet" href="css/notificacoes.css">
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
                <li><a href="#" class="ativo">Notificações</a></li>
                <li><a href="sair">Sair</a></li>
            </ul>
        </nav>
    </div>
    <div class="conteudo">
        <header>
            <h2>Notificações</h2>
        </header>
        <main>
            <div class="notificacao">
                <h3>Nova atividade criada</h3>
                <p>Você criou a atividade "Ajuda em evento escolar" em 25 de novembro às 9h.</p>
                <span class="horario">Há 2 horas</span>
            </div>
            <div class="notificacao">
                <h3>Voluntário confirmado</h3>
                <p>Valéria Albuquerque confirmou presença na atividade "Acompanhamento em consulta médica".</p>
                <span class="horario">Há 5 horas</span>
            </div>
            <div class="notificacao">
                <h3>Atividade concluída</h3>
                <p>A atividade "Assistência em consulta médica" foi marcada como concluída.</p>
                <span class="horario">Há 1 dia</span>
            </div>
        </main>
    </div>
</body>
</html>
