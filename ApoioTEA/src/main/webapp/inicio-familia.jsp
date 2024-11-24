<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ApoioTEA</title>
    <link rel="stylesheet" href="css/atividadeFamilia.css">
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
            <button class="botao-publicar">Publicar uma nova atividade</button>
        </header>
        <main>
            <h2>Atividades publicadas por você</h2>
            <div class="filtro">
                <label for="filtro-status">Filtrar por:</label>
                <select id="filtro-status">
                	<option value="">Status</option>
                    <option value="aberta">Aberta</option>
                    <option value="concluida">Concluida</option>
                    <option value="confirmada">Confirmada</option>
                    <option value="cancelada">Cancelada</option>
                </select>
            </div>
            <div class="atividade">
                <div class="info">
                    <h3>Acompanhamento em consulta médica</h3>
                    <p><strong>Data:</strong> 15 de novembro</p>
                    <p><strong>Hora:</strong> 10h</p>
                    <p><strong>Tipo:</strong> Saúde</p>
                    <p><strong>Localização:</strong> Centro de Guanambi</p>
                    <p><strong>Descrição:</strong> Precisamos de um voluntário para acompanhar Aurora em uma consulta médica.</p>
                    <p><strong>Voluntário:</strong> Valéria Albuquerque</p>
                    <p><strong>Status:</strong> Confirmada</p>
                </div>
                <div class="botoes">
                    <button>Editar</button>
                    <button>Voluntários</button>
                    <button>Cancelar</button>
                    <button>Excluir</button>
                </div>
            </div>
            <div class="atividade">
                <div class="info">
                    <h3>Ajuda em evento escolar</h3>
                    <p><strong>Data:</strong> 25 de novembro</p>
                    <p><strong>Hora:</strong> 9h</p>
                    <p><strong>Tipo:</strong> Evento</p>
                    <p><strong>Localização:</strong> Escola Joaquim Dias, Guanambi</p>
                    <p><strong>Descrição:</strong> Estamos buscando voluntários para ajudar durante o evento escolar.</p>
                    <p><strong>Status:</strong> Aberta</p>
                </div>
                <div class="botoes">
                    <button>Editar</button>
                    <button>Voluntários</button>
                    <button>Cancelar</button>
                    <button>Excluir</button>
                </div>
            </div>
        </main>
    </div>
</body>
</html>
