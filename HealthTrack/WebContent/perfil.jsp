<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
	<%@ include file="header.jsp" %>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light fixed-top">
        <a class="navbar-brand" href="dashboard.html">
            <img src="resources/images/logo_HT.png" alt="imagem logo" width="120px">
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="dashboard">Home</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link action" href="usuario?acao=exibir">Perfil</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="refeicao?acao=listar">Alimentação</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="exercicio?acao=listar">Exercícios Físicos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="peso?acao=listar">Peso</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="pressao?acao=listar">Pressão Arterial</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="logout.jsp">Logout</a>
            </li>
            </ul>
        </div>
    </nav>

    <div class="container-fluid topo">
        <div class="row">
            <div class="col-md12 col-lg-6">
                <img src="resources/images/lemon.jpg" class="img-fluid padding" alt="Foto ilustrativa limões" width="600px">
            </div>
            <div class="col-lg-6">
                <h1 class="display-4">Informações do usuário</h1>
                <br>
                <p class="lead">Nome: <%=request.getAttribute("nome")%></p>
                <p class="lead">Data nascimento: <%=request.getAttribute("dataNascimento")%></p>
                <p class="lead">Gênero: <%=request.getAttribute("genero")%></p>
                <p class="lead">Altura: <%=request.getAttribute("altura")%> cm</p>
                <p class="lead">Email: <%=request.getAttribute("email")%></p>
                <br><br><br>

                <div class="centraliza-button">
                    <a href="usuario?acao=abrir-form-edicao"><button type="button" class="btn btn-outline-primary btn-lg">EDITAR PERFIL</button></a>
                </div>
            </div>
          </div>
    </div>

	<div class="socket text-white text-center py-1">
		Cynthia Oka<br>João Pedro Dutra<br>&copy 2020 Health Track.Todos os Direitos Reservados.
	</div>
 

    <%@ include file="footer.jsp" %>
</body>
</html>