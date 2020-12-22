package br.com.fiap.ht.dao;

import java.util.List;
import br.com.fiap.ht.bean.Exercicio;
import br.com.fiap.ht.exception.DBException;


public interface ExercicioDAO {

	public void gravar(Exercicio exercicio) throws DBException;
	public Exercicio buscarPorId(int idExercicio);
	public List<Exercicio> mostrarLista(int idUsuario);
	public void atualizar(Exercicio exercicio) throws DBException;
	public void deletar(int idExercicio) throws DBException;
	
}
