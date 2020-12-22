<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
   <%@ include file="header.jsp" %>
</head>
<body>
    <div>
        <div id="logo">
            <img src="resources/images/logo_HT.png" alt="Logo Health Track" width="400px">
        </div>
        <h1 id="frase">Acompanhe seu estilo de vida e crie hábitos saudáveis</h1>
    </div>
    <hr>
    <div class="container-fluid padding">
        <div class="row justify-content-center">
            <div class="col-lg-8 padding">
                <form class="main-form padding" action="usuario" method="post" id="formulario_cadastro">
        <input type="hidden" value="cadastrar" name="acao">
            <fieldset>
                <legend>Informações pessoais</legend>
                
                    <c:if test="${not empty msg }">
						<div class="alert alert-success">${msg}</div>
					</c:if>
					<c:if test="${not empty erro }">
						<div class="alert alert-danger">${erro}</div>
					</c:if>
                        	
                        <div class="form-group">
                            <label class="textlabel" for="nomeInput">Nome Completo</label>
                            <input id="nomeInput" name="nome" type="text" autofocus="autofocus" class="form-control" maxlength="70" required>
                        </div>
                        <div class="form-group">
                            <label class="textlabel" for="dataInput">Data de nascimento</label>
                            <input id="dataInput" name="data" type="date" class="form-control form-peq" required>
                        </div>

                        <div class="form-group">
                            <label class="textlabel">Selecione seu gênero</label><br>
                            <div id="escolhe-genero">
                                <input type="radio" id="masculino" name="genero" value="masculino" checked="checked">
                                    <label for="masculino">Masculino</label><br>
                                <input type="radio" id="feminino" name="genero" value="feminino">
                                    <label for="feminino">Feminino</label><br>
                                <input type="radio" id="outro" name="genero" value="outro">
                                    <label for="outro">Outro</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="textlabel" for="alturaInput">Altura (em centímetros)</label>
                            <input id="alturaInput" name="altura" type="number" min = "100" max = "250" class="form-control form-peq" required>
                        </div>

                    </br></br>

                        <legend>Informações de Acesso</legend>
                        <div class="form-group">
                            <label class="textlabel" for="emailInput">E-mail</label>
                            <input id="emailInput" name="email" type="email" class="form-control" required>
                            <small id="emailHelp" class="form-text text-muted">Nunca iremos compartilhar seu e-mail com ninguém.</small>
                        </div>
                        <div class="form-group">
                            <label class="textlabel" for="senhaInput">Senha (sua senha deve possuir 8 caracteres com letras e números)</label>
                            <input id="senhaInput" name="senha" type="password" class="form-control" maxlength="8" required>
                        </div>
                        <div class="form-group">
                            <label class="textlabel" for="senha_confirma_Input">Digite sua senha novamente para confirmação.</label>
                            <input id="senha_confirma_Input" name="senha_confirma" type="password" class="form-control" maxlength="8" required>
                        </div>
                    </br>
                    <div class="centraliza-button">
                        <input type="submit" id="btn_cadastrar" class="btn btn-outline-primary btn-lg" value="Cadastrar">
                    </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
        <br><br>

    <footer>
        <%@ include file="footer_div.jsp" %>
    </footer>

    <%@ include file="footer.jsp" %>
</body>
</html>