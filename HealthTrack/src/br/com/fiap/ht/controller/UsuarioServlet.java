package br.com.fiap.ht.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import br.com.fiap.ht.bean.Usuario;
import br.com.fiap.ht.dao.UsuarioDAO;
import br.com.fiap.ht.exception.DBException;
import br.com.fiap.ht.factory.DAOFactory;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO dao;
       
	public void init() throws ServletException {
    	super.init();
    	dao = DAOFactory.getUsuarioDAO();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "exibir":
			exibir(request, response);
			break;
		case "abrir-form-edicao":
			abrirFormEdicao(request, response);
			break;
		}	
		
	}
	
	private void exibir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = dao.buscarPorId(1);
		request.setAttribute("nome", usuario.getNome());
		SimpleDateFormat dataNascimento = new SimpleDateFormat("dd/MM/yyyy");
		request.setAttribute("dataNascimento", dataNascimento.format(usuario.getDataNascimento().getTime()));
		request.setAttribute("genero", usuario.getSexo());
		request.setAttribute("altura", usuario.getAltura());
		request.setAttribute("email", usuario.getEmail());
		request.getRequestDispatcher("perfil.jsp").forward(request, response);
	}
	
	private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = dao.buscarPorId(1);
		request.setAttribute("nome", usuario.getNome());
		SimpleDateFormat dataNascimento = new SimpleDateFormat("dd/MM/yyyy");
		request.setAttribute("dataNascimento", dataNascimento.format(usuario.getDataNascimento().getTime()));
		request.setAttribute("altura", usuario.getAltura());
		request.setAttribute("email", usuario.getEmail());
		request.setAttribute("senha", usuario.getSenha());
		request.getRequestDispatcher("perfil_edit.jsp").forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "cadastrar":
			cadastrarUsuario(request, response);
			break;
		case "editar":
			editarCadastro(request, response);
			break;
		}
	

	}	
	
	
	
	protected void cadastrarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
			try {
				
				String nomeUsuario = request.getParameter("nome");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar dataNascimento = Calendar.getInstance();
				dataNascimento.setTime(sdf.parse(request.getParameter("data")));
				String sexo = request.getParameter("genero");
				int altura = Integer.parseInt(request.getParameter("altura"));
				String email = request.getParameter("email");
				String senha = request.getParameter("senha");
				
				
				/*
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar dataNascimento = Calendar.getInstance();
				dataNascimento.setTime(sdf.parse(request.getParameter("data")));
				*/
				
				Usuario usuario = new Usuario(0, nomeUsuario, dataNascimento, sexo, altura, email, senha);
				dao.gravar(usuario);
				
				
				
			} catch (DBException db) {
				db.printStackTrace();
				request.setAttribute("erro", "Erro ao cadastrar!");
			} catch (Exception e) {
				request.setAttribute("erro", "Por favor, valide os dados.");
			}
			
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
	}
	
	protected void editarCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try {
			String nomeUsuario = request.getParameter("nome");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar dataNascimento = Calendar.getInstance();
			dataNascimento.setTime(sdf.parse(request.getParameter("data")));
			String sexo = request.getParameter("genero");
			int altura = Integer.parseInt(request.getParameter("altura"));
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			
			Usuario usuario = new Usuario(1, nomeUsuario, dataNascimento, sexo, altura, email, senha);
			dao.atualizar(usuario);
			
			
			
			
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar!");
		} catch (Exception e) {
			request.setAttribute("erro", "Por favor, valide os dados.");
		}
		
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
		
		
	}
		
		

		
	

}
