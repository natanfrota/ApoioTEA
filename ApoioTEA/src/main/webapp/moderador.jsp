<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ApoioTEA</title>
<link rel="stylesheet" href="css/login1.css">
</head>
<body>
	<header>
		<div class="barra topo">
			<a href="inicio-moderador"><h1>ApoioTEA</h1></a>
		</div>
	</header>
	<div class="conteiner">
		<div class="caixa" style="width:35%;">

			<div class="direita">
				<h2>Área de moderação</h2>
				<form action="login-moderador" method="POST">
					<input type="email" name="email" placeholder="Email"> 
					<input type="password" name="senha" placeholder="Senha">
					<button type="submit">Entrar</button>
				</form>

			</div>
		</div>
	</div>
	<div class="caixa-mensagem" id="caixaMensagem">Email ou senha
		incorretos!</div>
	<footer>
		<div class="barra rodape"></div>
	</footer>
</body>
</html>
