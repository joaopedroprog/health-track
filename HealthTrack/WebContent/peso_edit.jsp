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
            <li class="nav-item">
                <a class="nav-link" href="refeicao?acao=listar">Alimenta��o</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="exercicio?acao=listar">Exerc�cios F�sicos</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="peso?acao=listar">Peso</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="pressao?acao=listar">Press�o Arterial</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="logout.jsp">Logout</a>
            </li>
            </ul>
        </div>
    </nav>
 
    <div class="container-fluid padding"> 
        <form class="main-form topo" method="post" id="formulario_edit_ref" action="peso">
        	<input type="hidden" value="editar" name="acao">
        	<input type="hidden" value="${peso.idPeso}" name="codigo">
            <fieldset>
                <legend>Edite o seu peso</legend>
                <div class="form-group">
                    <label class="textlabel" for="pesoInput" >Peso (Kg)</label>
                    <input id="pesoInput" name="peso" type="number" min="0" step="0.01" class="form-control" value="${peso.valorPeso}" required>
                </div>
                <div class="form-group">
                    <label class="textlabel" for="dataInput">Data da pesagem</label>
                    <input id="dataInput" name="data" type="date" class="form-control form-peq" value="${peso.dataPeso}" required>
                </div>
                
                <div class="add-ref">
            		<input type="submit" class="btn btn-primary btn-lg" id ="btn_peso" value="ATUALIZAR PESO" >
            		<a href="peso?acao=listar" class="btn btn-danger btn-lg">CANCELAR</a>
        		</div>
            </fieldset>
        </form>
    </div>

    <%@ include file="footer.jsp" %>
</body>
</html>