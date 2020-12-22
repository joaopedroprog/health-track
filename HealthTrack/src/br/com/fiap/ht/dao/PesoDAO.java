package br.com.fiap.ht.dao;

import java.util.List;
import br.com.fiap.ht.bean.Peso;
import br.com.fiap.ht.exception.DBException;


public interface PesoDAO {

	public void gravar(Peso peso) throws DBException;
	public Peso buscarPorId(int idPeso);
	public List<Peso> mostrarLista(int idUsuario);
	public void atualizar(Peso peso) throws DBException;
	public void deletar(int idPeso) throws DBException;
	
}
