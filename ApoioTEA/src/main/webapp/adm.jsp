<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Painel administrativo</title>
  <link rel="stylesheet" href="css/adm.css">
  <style>
    .section {
      display: none;
    }
    .section.active {
      display: block;
    }
  </style>
</head>
<body>

<header>
  <h1>Painel Administrativo</h1>
</header>

<nav>
  <a href="#voluntarios" onclick="mostrarSessao('voluntarios')">Voluntários</a>
  <a href="#familias" onclick="mostrarSessao('familias')">Famílias</a>
  <a href="#conversas" onclick="mostrarSessao('conversas')">Conversas</a>
</nav>

<div class="container">
  <div id="voluntarios" class="section">
    <h2>Lista de Voluntários</h2>
    <table>
      <thead>
        <tr>
          <th>Nome</th>
          <th>Email</th>
          <th>Status</th>
          <th>Data de Cadastro</th>
          <th>Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>João Silva</td>
          <td>joao@email.com</td>
          <td>Ativo</td>
          <td>2024-11-15</td>
          <td class="actions">
            <button class="view">Visualizar</button>
            <button class="suspend">Suspender</button>
            <button class="delete">Excluir</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>

  <div id="familias" class="section">
    <h2>Lista de Famílias</h2>
    <table>
      <thead>
        <tr>
          <th>Nome do Responsável</th>
          <th>Endereço</th>
          <th>Status</th>
          <th>Data de Cadastro</th>
          <th>Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>Maria Santos</td>
          <td>Rua das Flores, 123</td>
          <td>Ativo</td>
          <td>2024-10-20</td>
          <td class="actions">
            <button class="view">Visualizar</button>
            <button class="suspend">Suspender</button>
            <button class="delete">Excluir</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>

  <div id="conversas" class="section">
    <h2>Área de Conversas</h2>
    <p>Aqui você pode acessar e gerenciar conversas.</p>
  </div>
</div>

<script>
  function mostrarSessao(sectionId) {
    const sessoes = document.querySelectorAll('.section');
    sessoes.forEach(section => {
      section.classList.remove('active');
    });

    const alvo = document.getElementById(sectionId);
    alvo.classList.add('active');
  }
</script>

</body>
</html>
