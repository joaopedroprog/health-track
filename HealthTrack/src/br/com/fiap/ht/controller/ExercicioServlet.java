package br.com.fiap.ht.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.ht.bean.Exercicio;
import br.com.fiap.ht.dao.ExercicioDAO;
import br.com.fiap.ht.exception.DBException;
import br.com.fiap.ht.factory.DAOFactory;


@WebServlet("/exercicio")
public class ExercicioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private ExercicioDAO dao; 
    
    public void init() throws ServletException {
    	super.init();
    	dao = DAOFactory.getExercicioDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "listar":
			listar(request, response);
			break;
		case "abrir-form-edicao":
			abrirFormEdicao(request, response);
			break;
		}	
	}

	
	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Exercicio> lista = dao.mostrarLista(1);
		request.setAttribute("exercicios", lista);
		request.getRequestDispatcher("exercicio_home.jsp").forward(request, response);
	}
	
	private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("codigo"));
		Exercicio exercicio = dao.buscarPorId(id);
		request.setAttribute("exercicio", exercicio);
		request.getRequestDispatcher("exercicio_edit.jsp").forward(request, response);
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "cadastrar":
			cadastrar(request, response);
			break;
		case "editar":
			editar (request, response);
			break;
		case "excluir":
			excluir(request, response);
			break;
		}
	}
	
	protected void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			float caloriasExercicio = Float.parseFloat(request.getParameter("calorias"));
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Calendar dataExercicio = Calendar.getInstance();
			dataExercicio.setTime(format.parse(request.getParameter("data")));
			
			String tipoExercicio = request.getParameter("selecao");
			
			String descricaoExercicio = request.getParameter("descricao");
			
			Exercicio exercicio = new Exercicio(0, 1, dataExercicio, tipoExercicio, descricaoExercicio, caloriasExercicio) ;
			dao.gravar(exercicio);
			
			request.setAttribute("msg", "Exercicio Cadastrado!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados.");
		}
		
		request.getRequestDispatcher("exercicio_add.jsp").forward(request, response);
	}

	protected void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			float caloriasExercicio = Float.parseFloat(request.getParameter("calorias"));
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Calendar dataExercicio = Calendar.getInstance();
			dataExercicio.setTime(format.parse(request.getParameter("data")));
			
			String tipoExercicio = request.getParameter("selecao");
			
			String descricaoExercicio = request.getParameter("descricao");
			
			Exercicio exercicio = new Exercicio(codigo, 1, dataExercicio, tipoExercicio, descricaoExercicio, caloriasExercicio) ;
			dao.atualizar(exercicio);
			
			request.setAttribute("msg", "Exercício atualizado!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados.");
		}
		
		listar(request, response);
	}
	

	private void excluir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		try {
			dao.deletar(codigo);
			request.setAttribute("msg", "Exercicio removido!");
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao deletar.");
		}
		listar(request, response);
	}

}
