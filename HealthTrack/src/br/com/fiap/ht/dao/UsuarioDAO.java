package br.com.fiap.ht.dao;

import br.com.fiap.ht.bean.Usuario;
import br.com.fiap.ht.exception.DBException;


public interface UsuarioDAO {

	public void gravar(Usuario usuario) throws DBException;
	public Usuario buscarPorId(int idUsuario);
	public void atualizar(Usuario usuario) throws DBException;
	public void deletar(int idUsuario) throws DBException;
	
}
