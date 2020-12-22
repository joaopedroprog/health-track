package br.com.fiap.ht.factory;

import br.com.fiap.ht.dao.ExercicioDAO;
import br.com.fiap.ht.dao.PesoDAO;
import br.com.fiap.ht.dao.PressaoDAO;
import br.com.fiap.ht.dao.RefeicaoDAO;
import br.com.fiap.ht.dao.UsuarioDAO;
import br.com.fiap.ht.impl.ExercicioDAOImpl;
import br.com.fiap.ht.impl.PesoDAOImpl;
import br.com.fiap.ht.impl.PressaoDAOImpl;
import br.com.fiap.ht.impl.RefeicaoDAOImpl;
import br.com.fiap.ht.impl.UsuarioDAOImpl;

public class DAOFactory {
	
	public static ExercicioDAO getExercicioDAO(){
		return new ExercicioDAOImpl();
	}

	public static PesoDAO getPesoDAO() {
		return new PesoDAOImpl();
	}
	
	public static PressaoDAO getPressaoDAO() {
		return new PressaoDAOImpl();
	}
	
	public static RefeicaoDAO getRefeicaoDAO() {
		return new RefeicaoDAOImpl();
	}
	
	public static UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOImpl();
	}
}
