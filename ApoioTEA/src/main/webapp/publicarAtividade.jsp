<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ApoioTEA</title>
    <link rel="stylesheet" href="css/publicar.css">
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
            <h2>Publicar Nova Atividade</h2>
        </header>
        <main>
            <form class="formulario">
                <div class="form-grupo">
                    <label for="titulo">Título da Atividade:</label>
                    <input type="text" id="titulo" name="titulo" placeholder="Digite o título da atividade" required>
                </div>

                <div class="form-grupo">
                    <label for="data">Data:</label>
                    <input type="date" id="data" name="data" required>
                </div>

                <div class="form-grupo">
                    <label for="hora">Hora:</label>
                    <input type="time" id="hora" name="hora" required>
                </div>

                <div class="form-grupo">
                    <label for="local">Localização:</label>
                    <input type="text" id="local" name="local" placeholder="Informe o local da atividade" required>
                </div>

                <div class="form-grupo">
                    <label for="descricao">Descrição:</label>
                    <textarea id="descricao" name="descricao" rows="5" placeholder="Descreva a atividade..." required></textarea>
                </div>

                <div class="form-grupo">
                    <label for="tipo">Tipo da Atividade:</label>
                    <select id="tipo" name="tipo" required>
                    	<option value="">Selecione</option>
                        <option value="Saúde">Saúde</option>
                        <option value="Educação">Educação</option>
                        <option value="Evento">Evento</option>
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
