<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="modelo.Familia"%>
<%  Familia familia = (Familia) session.getAttribute("usuario");%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ApoioTEA</title>
<link rel="stylesheet" href="css/editar-perfil.css">
</head>
<body>
	<div class="barraTarefas">
		<h1>ApoioTEA</h1>
		<nav>
			<ul>
				<li><a href="inicio-familia">Início</a></li>
				<li><a href="perfil-familia?id=<%= familia.getId()%>">Perfil</a></li>
				<li><a href="atividades-agendadas-familia">Atividades agendadas</a></li>
				<li><a href="conversas">Conversas</a></li>
				<li><a href="notificacoes">Notificações</a></li>
				<li><a href="sair">Sair</a></li>
			</ul>
		</nav>
	</div>
	<div class="conteudo">
		<header>
			<h2>Editar perfil</h2>
		</header>
		<main>
			<form class="formulario" action="salvar-edicao-perfil-familia" method="post" enctype="multipart/form-data">
			
				<input type="hidden" id="id" name="id" value="<%= familia.getId() %>">
			
				<div class="form-grupo">
					<label for="foto">Escolha uma foto:</label> <input type="file"
						id="foto" name="foto" accept="image/*">
				</div>

				<div class="form-grupo">
					<label for="nome">Nome: </label> <input type="text" id="nome"
						name="nome" value="<%= familia.getNome() %>" required>
				</div>

				<div class="form-grupo">
					<label for="data_nascimento">Data de nascimento:</label> <input type="date"
						id="data_nascimento" name="data_nascimento" value="<%= familia.getDataNascimento() %>" required>
				</div>

				<div class="form-grupo">
					<label for="email">Email:</label> <input type="email" id="email"
						name="email" placeholder="Informe um novo Email"
						value="<%= familia.getEmail() %>" required>
				</div>

				<div class="form-grupo">
					<label for="cidade"><strong>Cidade:</strong></label> <input
						type="text" name="cidade" id="cidade"
						placeholder="Informe uma nova cidade"
						value="<%= familia.getCidade() %>" required>
				</div>
				<div class="form-grupo">
					<label for="estado"><strong>Estado:</strong></label> <input
						type="text" name="estado" id="estado"
						placeholder="Informe um novo estado"
						value="<%= familia.getEstado() %>" required>
				</div>

				<div class="form-grupo">
					<label for="descricao">Descrição:</label>
					<textarea id="descricao" name="descricao" rows="5"
						placeholder="Descreva a atividade..." required><%= familia.getDescricao() %></textarea>
				</div>


				<div class="form-grupo botoes">
					<button type="submit" class="btn-publicar">Salvar</button>
				</div>
			</form>
		</main>
	</div>
</body>
</html>