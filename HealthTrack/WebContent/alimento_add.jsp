<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
            <li class="nav-item">
                <a class="nav-link" href="usuario?acao=exibir">Perfil</a>
            </li>
            <li class="nav-item active">
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

    <div class="container-fluid padding">
    <form class="main-form topo" method="post" id="formulario_edit_ref" action="refeicao">
    <input type="hidden" value="cadastrar" name="acao">
        <fieldset>
            <legend>Edite as informações da sua refeição</legend>
            
                    <c:if test="${not empty msg }">
						<div class="alert alert-success">${msg}</div>
					</c:if>
					<c:if test="${not empty erro }">
						<div class="alert alert-danger">${erro}</div>
					</c:if>
					
            <div class="form-group">
                <label class="textlabel">Tipo de refeição</label>
                <select class="custom-select mr-sm-2" id="inlineFormCustomSelect" name="selecao">
                    <option value="CAFE DA MANHA">Café da manhã</option>
                    <option value="LANCHE DA MANHA">Lanche da manhã</option>
                    <option value="ALMOCO">Almoço</option>
                    <option value="LANCHE DA TARDE">Lanche da tarde</option>
                    <option value="JANTAR">Jantar</option>
                    <option value="CEIA">Ceia</option>
                </select>
            </div>
            
            <div class="form-group">
                <label class="textlabel">Descrição da refeição</label>
                <textarea class="form-control" id="descricao-ref" rows="4" name="descricao"></textarea>
            </div>
            
            <div class="form-group">
                <label class="textlabel">Calorias ingeridas</label>
                <input id="calRefInput" type="number" class="form-control" name="calorias" required>
            </div>
            
            <div class="form-group">
                <label class="textlabel" for="dataInput">Data da refeição</label>
                <input id="dataInput" name="data" type="date" class="form-control form-peq" required>
            </div>
        </fieldset>
        
    	<div class="add-ref">
        	<input type="submit" class="btn btn-primary btn-lg" id="btn_alimentos" value="ADICIONAR REFEIÇÃO">
    	</div>
    </form>

    </div>
    
    <%@ include file="footer.jsp" %>
</body>
</html>