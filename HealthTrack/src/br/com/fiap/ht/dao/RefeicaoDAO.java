package br.com.fiap.ht.dao;

import java.util.List;
import br.com.fiap.ht.bean.Refeicao;
import br.com.fiap.ht.exception.DBException;


public interface RefeicaoDAO {
	
	public void gravar(Refeicao refeicao) throws DBException;
	public Refeicao buscarPorId(int idRefeicao);
	public List<Refeicao> mostrarLista(int idUsuario);
	public void atualizar(Refeicao refeicao) throws DBException;
	public void deletar(int idRefeicao) throws DBException;

}
