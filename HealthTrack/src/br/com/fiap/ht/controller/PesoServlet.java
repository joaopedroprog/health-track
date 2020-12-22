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

import br.com.fiap.ht.bean.Peso;
import br.com.fiap.ht.dao.PesoDAO;
import br.com.fiap.ht.exception.DBException;
import br.com.fiap.ht.factory.DAOFactory;


@WebServlet("/peso")
public class PesoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private PesoDAO dao; 
    
    public void init() throws ServletException {
    	super.init();
    	dao = DAOFactory.getPesoDAO();
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
		List<Peso> lista = dao.mostrarLista(1);
		request.setAttribute("pesos", lista);
		request.getRequestDispatcher("peso_home.jsp").forward(request, response);
	}
	
	private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("codigo"));
		Peso peso = dao.buscarPorId(id);
		request.setAttribute("peso", peso);
		request.getRequestDispatcher("peso_edit.jsp").forward(request, response);
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
			float pesoUsuario = Float.parseFloat(request.getParameter("peso"));
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Calendar dataPeso = Calendar.getInstance();
			dataPeso.setTime(format.parse(request.getParameter("data")));
			
			Peso peso = new Peso(0, 1, dataPeso, pesoUsuario);
			dao.gravar(peso);
			
			request.setAttribute("msg", "Peso Cadastrado!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados.");
		}
		
		request.getRequestDispatcher("peso_add.jsp").forward(request, response);
	}

	protected void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			float pesoUsuario = Float.parseFloat(request.getParameter("peso"));
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Calendar dataPeso = Calendar.getInstance();
			dataPeso.setTime(format.parse(request.getParameter("data")));
			
			Peso peso = new Peso(codigo, 1, dataPeso, pesoUsuario);
			dao.atualizar(peso);
			
			request.setAttribute("msg", "Peso atualizado!");
			
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
			request.setAttribute("msg", "Peso removido!");
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao deletar.");
		}
		listar(request, response);
	}
}
