<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ApoioTEA</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
    <header>
        <div class="barra topo">
            <a href=""><h1>ApoioTEA</h1></a>
        </div>
    </header>
    <div class="conteiner">
        <div class="caixa">
            <div class="esquerda">
                <img src="imagens/TEA.jpg" alt="Quebra-cabeça em forma de coração">
            </div>
            <div class="direita">
                <h2>Entre na plataforma</h2>
                <form action="login" method="POST">
                    <input type="email" name="email" placeholder="Email">
                    <input type="password" name="senha" placeholder="Senha">
                    <button type="submit">Entrar</button>
                </form>
                <div class="cadastro">
                    Não tem conta? <a href="cadastro.jsp">Cadastre-se</a>
                </div>
            </div>
        </div>
    </div>    
    <footer>
        <div class="barra rodape"></div>
    </footer>
</body>
</html>
