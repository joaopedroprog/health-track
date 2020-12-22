<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
                <a class="nav-link" href="refeicao?acao=listar">Alimentação</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="exercicio?acao=listar">Exercícios Físicos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="peso?acao=listar">Peso</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="pressao?acao=listar">Pressão Arterial</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="logout.jsp">Logout</a>
            </li>
            </ul>
        </div>
    </nav>
    <div class="table-wrap topo">
    
            <c:if test="${not empty msg }">
				<div class="alert alert-success">${msg}</div>
			</c:if>
			<c:if test="${not empty erro }">
				<div class="alert alert-danger">${erro}</div>
			</c:if>
    
        <table class="table">
            <thead class="thead-light">
                <tr>
                <th scope="col">Data</th>
                <th scope="col">Diastólica</th>
                <th scope="col">Sistólica</th>
                <th scope="col"></th>
                <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${pressoes}" var="p">
                <tr>
                    <th scope="row">
                    	<fmt:formatDate value="${p.dataPressao.time}" pattern="dd/MM/yyyy"/>
                    </th>
                    <td>${p.pressaoSistolica}</td>
                    <td>${p.pressaoDiastolica}</td>
                    
                    <td>
	                    	<c:url value="pressao" var="link">
	                    		<c:param name="acao" value="abrir-form-edicao"/>
	                    		<c:param name="codigo" value="${p.idPressao}"/>
	                    	</c:url>
	                    	<a class="icon-edit" href="${link}"><i class="far fa-edit"></i></a>
	                    </td>

                    <td><button type="button" class="btn btn-danger btn-xs" data-toggle="modal" data-target="#excluirModal" onclick="codigoExcluir.value=${p.idPressao}">EXCLUIR</button></td>
                 </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <br><br><br>
    <div class="add-ref">
        <a href="pressao_add.jsp"><button type="button" class="btn btn-primary btn-lg">ADICIONAR PRESSÃO ARTERIAL</button></a>
    </div>

    <%@ include file="footer.jsp" %>
    
        <!-- Modal -->
<div class="modal fade" id="excluirModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Confirmação</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        		Deseja realmente excluir esta pressão?
      </div>
      <div class="modal-footer">
      	<form action="pressao" method="post">
      		<input type="hidden" name="acao" value="excluir">
      		<input type="hidden" name="codigo" id="codigoExcluir">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
	        <button type="submit" class="btn btn-danger">Excluir</button>
        </form>
      </div>
    </div>
  </div>
</div>
</body>
</html>