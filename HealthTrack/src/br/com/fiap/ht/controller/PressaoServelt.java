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

import br.com.fiap.ht.bean.Pressao;
import br.com.fiap.ht.dao.PressaoDAO;
import br.com.fiap.ht.exception.DBException;
import br.com.fiap.ht.factory.DAOFactory;


@WebServlet("/pressao")
public class PressaoServelt extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private PressaoDAO dao;

	public void init() throws ServletException {
    	super.init();
    	dao = DAOFactory.getPressaoDAO();
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
		List<Pressao> lista = dao.mostrarLista(1);
		request.setAttribute("pressoes", lista);
		request.getRequestDispatcher("pressao_home.jsp").forward(request, response);
	}
	
	private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("codigo"));
		Pressao pressao = dao.buscarPorId(id);
		request.setAttribute("pressao", pressao);
		request.getRequestDispatcher("pressao_edit.jsp").forward(request, response);
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
			
			int pressaoSistolica = Integer.parseInt(request.getParameter("sistolica"));
			int pressaoDiastolica = Integer.parseInt(request.getParameter("diastolica"));
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Calendar dataPressao = Calendar.getInstance();
			dataPressao.setTime(format.parse(request.getParameter("data")));
			
			Pressao pressao = new Pressao(0, 1, dataPressao, pressaoSistolica, pressaoDiastolica);
			dao.gravar(pressao);
			
			request.setAttribute("msg", "Pressao cadastrada!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados.");
		}
		
		request.getRequestDispatcher("pressao_add.jsp").forward(request, response);
	}

	protected void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			int pressaoSistolica = Integer.parseInt(request.getParameter("sistolica"));
			int pressaoDiastolica = Integer.parseInt(request.getParameter("diastolica"));
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Calendar dataPressao = Calendar.getInstance();
			dataPressao.setTime(format.parse(request.getParameter("data")));
			
			Pressao pressao = new Pressao(codigo, 1, dataPressao, pressaoSistolica, pressaoDiastolica);
			dao.atualizar(pressao);
			
			request.setAttribute("msg", "Pressao atualizada!");
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
			request.setAttribute("msg", "Pressao removido!");
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao deletar.");
		}
		listar(request, response);
	}

}
