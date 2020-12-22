<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

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
        <div class="container-fluid text-center">
            <h2 id="frase-logout">Você saiu da sua conta. Até a próxima!</h2>
            <p id="lead">Logar novamente? <a href="login.jsp"> Fazer login.</a></p>     
        </div>

        <%@ include file="footer.jsp" %>
</body>
</html>