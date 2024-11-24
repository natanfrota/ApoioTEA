<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ApoioTEA</title>
    <link rel="stylesheet" href="css/conversas.css">
</head>
<body>
    <div class="barraTarefas">
        <h1>ApoioTEA</h1>
        <nav>
            <ul>
                <li><a href="#">Início</a></li>
                <li><a href="#">Perfil</a></li>
                <li><a href="#">Atividades</a></li>
                <li><a href="#">Conversas</a></li>
                <li><a href="#">Notificações</a></li>
                <li><a href="#">Sair</a></li>
            </ul>
        </nav>
    </div>
    <div class="conteudo">
        <header>
            <h2>Conversas</h2>
            <input type="text" placeholder="Buscar conversa...">
        </header>

        <main>
            <div class="conversa">
                <img src="imagens/img-perfil-padrao.jpeg" alt="Avatar">
                <div class="detalhes">
                    <h3>Valéria Albuquerque</h3>
                    <p>Última mensagem recebida...</p>
                </div>
                <span class="hora">10:30</span>
            </div>

            <div class="conversa">
                <img src="imagens/img-perfil-padrao.jpeg" alt="Avatar">
                <div class="detalhes">
                    <h3>João Silva</h3>
                    <p>Precisamos conversar sobre a atividade...</p>
                </div>
                <span class="hora">09:15</span>
            </div>

            <div class="conversa">
                <img src="imagens/img-perfil-padrao.jpeg" alt="Avatar">
                <div class="detalhes">
                    <h3>Maria Oliveira</h3>
                    <p>Tudo pronto para o evento de amanhã?</p>
                </div>
                <span class="hora">08:45</span>
            </div>
        </main>
    </div>
</body>
</html>
