package br.com.fiap.ht.dao;

import java.util.List;
import br.com.fiap.ht.bean.Pressao;
import br.com.fiap.ht.exception.DBException;


public interface PressaoDAO {

	public void gravar(Pressao pressao) throws DBException;
	public Pressao buscarPorId(int idPressao);
	public List<Pressao> mostrarLista(int idUsuario);
	public void atualizar(Pressao pressao) throws DBException;
	public void deletar(int idPressao) throws DBException;
	
}
