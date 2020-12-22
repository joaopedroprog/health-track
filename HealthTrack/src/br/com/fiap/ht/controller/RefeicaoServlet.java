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

import br.com.fiap.ht.bean.Refeicao;
import br.com.fiap.ht.dao.RefeicaoDAO;
import br.com.fiap.ht.exception.DBException;
import br.com.fiap.ht.factory.DAOFactory;


@WebServlet("/refeicao")
public class RefeicaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
private RefeicaoDAO dao; 
    
    public void init() throws ServletException {
    	super.init();
    	dao = DAOFactory.getRefeicaoDAO();
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
		List<Refeicao> lista = dao.mostrarLista(1);
		request.setAttribute("refeicoes", lista);
		request.getRequestDispatcher("alimento_home.jsp").forward(request, response);
	}
	
	private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("codigo"));
		Refeicao refeicao = dao.buscarPorId(id);
		request.setAttribute("refeicao", refeicao);
		request.getRequestDispatcher("alimento_edit.jsp").forward(request, response);
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
			float caloriasRefeicao = Float.parseFloat(request.getParameter("calorias"));
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Calendar dataRefeicao = Calendar.getInstance();
			dataRefeicao.setTime(format.parse(request.getParameter("data")));
			
			String tipoRefeicao = request.getParameter("selecao");
			
			String descricaoRefeicao = request.getParameter("descricao");
			
			Refeicao refeicao = new Refeicao(0, 1, dataRefeicao, tipoRefeicao, descricaoRefeicao, caloriasRefeicao) ;
			dao.gravar(refeicao);
			
			request.setAttribute("msg", "Refeicao Cadastrado!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados.");
		}
		
		request.getRequestDispatcher("alimento_add.jsp").forward(request, response);
	}

	protected void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			float caloriasRefeicao = Float.parseFloat(request.getParameter("calorias"));
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Calendar dataRefeicao = Calendar.getInstance();
			dataRefeicao.setTime(format.parse(request.getParameter("data")));
			
			String tipoRefeicao = request.getParameter("selecao");
			
			String descricaoRefeicao = request.getParameter("descricao");
			
			Refeicao refeicao = new Refeicao(codigo, 1, dataRefeicao, tipoRefeicao, descricaoRefeicao, caloriasRefeicao) ;
			dao.atualizar(refeicao);
			
			request.setAttribute("msg", "Refeicao atualizado!");
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
			request.setAttribute("msg", "Refeicao removido!");
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao deletar.");
		}
		listar(request, response);
	}

}
