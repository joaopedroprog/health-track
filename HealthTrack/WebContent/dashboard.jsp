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
                <a class="nav-link active" href="dashboard">Home</a>
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
            <li class="nav-item">
                <a class="nav-link" href="pressao?acao=listar">Pressão Arterial</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="logout.jsp">Logout</a>
            </li>
            </ul>
        </div>
    </nav>
    	<div class="background">
		    <div class="container-fluid padding indicadores">
		   	 	
		        <div class="row text-center padding">
		          <div class="col-md-3">
		            <i class="fas fa-ruler"></i>
		            <h3>IMC</h3>
		            <p class="lead IMC" id="indicadores-text">IMC: <%=request.getAttribute("imc")%><br><%=request.getAttribute("faixa")%></p>
		          </div>
		          <div class="col-md-3">
		            <i class="fas fa-balance-scale"></i>
		            <h3>PESO</h3>
		            <p class="lead" id="indicadores-text" >Última pesagem em <%=request.getAttribute("ultimaDataPeso")%> com valor de <%=request.getAttribute("ultimoValorPeso")%> kg</p>
		          </div>
		          <div class="col-md-3">
		            <i class="fas fa-dumbbell"></i>
		            <h3>Exercício</h3>
		            <p class="lead" id="indicadores-text">Último exercício realizado em <%=request.getAttribute("ultimaDataExercicio")%>. Tipo: <%=request.getAttribute("ultimoTipoExercicio")%></p>
		          </div>          
		          <div class="col-md-3">
		            <i class="fas fa-heartbeat"></i>
		            <h3>PRESSÃO ARTERIAL</h3>
		            <p class="lead" id="indicadores-text">Último pressão aferida em <%=request.getAttribute("ultimaDataPressao")%> com valor de <%=request.getAttribute("ultimoValorPressaoSistolica")%>x<%=request.getAttribute("ultimoValorPressaoDistolica")%></p>
		          </div>
		        </div>
		    </div>
	    
	   <!--  
	    <div class="container-fluid padding">
	        <p class="lead text-center">Olá Allana, aqui estão alguns dos seus resultados:</p>
	    </div>
	    -->
	    
		<!--
	    <div class="container-fluid padding">
	        <div class="row peso text-center">
	            <div class="col-12">
	                <hr class="dark">
	                <h1 class="display-4">1.4 kg</h1>
	                <div class="col-12">
	                    <p class="lead">perdidos no mês de Maio</p>
	                </div>
	                <hr class="dark">
	            </div>
	        </div>
	    </div>
		-->
	   
		<footer class="bg-white">
			<div class="container">
				<div class="row justify-content-center text-center text-dark">
					<div class="col-md-8">
						<hr class="bg-light">
						<p class="lead">O Health Track é o seu maior aliado para consquistar um estilo de vida saudável. Adicione dados das suas refeições, seu peso, as atividades físicas realizadas, suas medidas corporais e até mesmo sua pressão arterial.</p>
						<hr class="bg-light">
					</div>
				</div>
			</div>
		</footer>

	</div>

	<div class="socket text-white text-center py-4">
		Cynthia Oka<br>João Pedro Dutra<br>&copy 2020 Health Track.Todos os Direitos Reservados.
	</div>
	
	<%@ include file="footer.jsp" %>

</body>
</html>