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

            <div class="container-fluid padding">
                <div class="row justify-content-center">
                    <div class="col-lg-6 text-center padding">
                        <form class="form-login" name="form_login" method="POST" id="login">
                            <div class="form-group">
                                <input id="email_acesso" type="text" autofocus="autofocus" class="form-control" placeholder="Email">
                            </div>
                            <div class="form-group">
                                <input id="senha_acesso" type="password" class="form-control" placeholder="Senha" maxlength="8">
                            </div>
                            <a href="dashboard" id="btn_login" class="btn btn-primary btn-lg">LOGIN</a>
                        </form>
                    </div>
                </div>
                <div class="text-center padding ">
                    <p>Esqueceu sua senha? Clique aqui para recuperar.</p>
                    <p>Ainda não faz parte do nosso time? <a href="cadastro.jsp">Cadastre-se.</a></p>
                </div>
            </div>
            
        <footer>
            <%@ include file="footer_div.jsp" %>
        </footer>
        
        <%@ include file="footer.jsp" %>
</body>
</html>