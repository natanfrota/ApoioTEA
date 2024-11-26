
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ApoioTEA</title>
    <link rel="stylesheet" href="css/NewFile.css">
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
             <div class="perfil-secao">
                <div class="perfil-cartao">
                    <img src="imagens/img-perfil-padrao.jpeg" alt="Foto da família" class="perfil-foto">
                    <div class="perfil-info">
                        <h2>Responsável: </h2>
                        <p>Cidade: </p>
                        <p class="descricao">Descrição:</p>
                        <button class="botao-editarperfil">Editar perfil</button>
                    </div>
                </div>
            </div>
            <div class="header-botao">
                <button class="botao-publicar">Publicar uma nova atividade</button>
            </div>
        </header>
        <main>
            <h2>Atividades</h2>
            <div class="atividade">
                <div class="info">
                    <h3>Acompanhamento em consulta médica</h3>
                    <p><strong>Data:</strong> 15 de novembro</p>
                    <p><strong>Hora:</strong> 10h</p>
                    <p><strong>Localização:</strong> Centro de Guanambi</p>
                    <p><strong>Descrição:</strong> Precisamos de um voluntário para acompanhar Aurora em uma consulta médica.</p>
                </div>
                <div class="botoes">
                    <button>Editar</button>
                    <button>Voluntários</button>
                    <button>Excluir</button>
                </div>
            </div>
            <div class="atividade">
                <div class="info">
                    <h3>Ajuda em evento escolar</h3>
                    <p><strong>Data:</strong> 25 de novembro</p>
                    <p><strong>Hora:</strong> 9h</p>
                    <p><strong>Localização:</strong> Escola Joaquim Dias, Guanambi</p>
                    <p><strong>Descrição:</strong> Estamos buscando voluntários para ajudar durante o evento escolar.</p>
                </div>
                <div class="botoes">
                    <button>Editar</button>
                    <button>Voluntários</button>
                    <button>Excluir</button>
                </div>
            </div>
        </main>
    </div>
</body>
</html>
