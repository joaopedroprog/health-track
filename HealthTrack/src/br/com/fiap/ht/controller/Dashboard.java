package br.com.fiap.ht.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.ht.bean.Exercicio;
import br.com.fiap.ht.bean.Peso;
import br.com.fiap.ht.bean.Pressao;
import br.com.fiap.ht.bean.Usuario;
import br.com.fiap.ht.dao.ExercicioDAO;
import br.com.fiap.ht.dao.PesoDAO;
import br.com.fiap.ht.dao.PressaoDAO;
import br.com.fiap.ht.dao.UsuarioDAO;
import br.com.fiap.ht.factory.DAOFactory;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private PesoDAO daoPeso;
	private UsuarioDAO daoUsuario;
	private PressaoDAO daoPressao;
	private ExercicioDAO daoExercicio;

    
    public void init() throws ServletException {
    	super.init();
    	daoPeso = DAOFactory.getPesoDAO();
    	daoUsuario = DAOFactory.getUsuarioDAO();
    	daoPressao = DAOFactory.getPressaoDAO();
    	daoExercicio = DAOFactory.getExercicioDAO();    	
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		exibirDashboard(request, response);
		
	}


	private void exibirDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obter objeto Usuario da sessão
		Usuario usuario = daoUsuario.buscarPorId(1);
		// Obter último objeto Peso
		List <Peso> listaPeso = daoPeso.mostrarLista(1);
		Peso peso = listaPeso.get(0);
		// Obter último objeto Pressão
		List <Pressao> listaPressao = daoPressao.mostrarLista(1);
		Pressao pressao = listaPressao.get(0);
		// Obter último objeto Exercicio
		List <Exercicio> listaExercicio = daoExercicio.mostrarLista(1);
		Exercicio exercicio = listaExercicio.get(0);
		
		// Formatar data
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
		
		// Obter dados do objeto peso
		request.setAttribute("ultimaDataPeso", sdf.format(peso.getDataPeso().getTime()));
		request.setAttribute("ultimoValorPeso", peso.getValorPeso());
		
		// Obter dados do objeto pressao
		request.setAttribute("ultimaDataPressao", sdf.format(pressao.getDataPressao().getTime()));
		request.setAttribute("ultimoValorPressaoSistolica", pressao.getPressaoSistolica());
		request.setAttribute("ultimoValorPressaoDistolica", pressao.getPressaoDiastolica());
		
		// Obter dadois do objeto exercicio
		request.setAttribute("ultimaDataExercicio", sdf.format(exercicio.getDataExercicio().getTime()));
		request.setAttribute("ultimoTipoExercicio", exercicio.getTipoExercicio());
		
		// Calcular IMC
		Float imc = (peso.getValorPeso() / (usuario.getAltura()*usuario.getAltura())) * 10000;
		String faixa = new String();
		
		 if (imc < 16){
			 faixa = "BAIXO PESO GRAU III";
	    }else if (16 <= imc && imc < 16.99){
	         faixa = "BAIXO PESO GRAU II";
	    }else if (17 <= imc  && imc < 18.49){
	         faixa = "BAIXO PESO GRAU I";
	    }else if (18.5 <= imc && imc < 24.99){
	         faixa = "PESO IDEAL";
	    }else if (25 <= imc && imc < 29.99){
	        faixa = "SOBREPESO";
	    }else if (30 <= imc && imc < 34.99){
	        faixa = "OBESIDADE GRAU I";
	    }else if (35 <= imc && imc < 39.99){
	        faixa = "OBESIDADE GRAU II";
	    }else{
	         faixa = "OBESIDADE GRAU III";
	    }
		 
		request.setAttribute("imc", String.format("%.2f", imc));
		request.setAttribute("faixa", faixa);
		
		
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
		
	}
	
	
}