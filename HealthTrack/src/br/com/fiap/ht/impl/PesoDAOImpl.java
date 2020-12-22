package br.com.fiap.ht.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.ht.bean.Peso;
import br.com.fiap.ht.dao.PesoDAO;
import br.com.fiap.ht.exception.DBException;
import br.com.fiap.ht.singleton.ConnectionManager;


public class PesoDAOImpl implements PesoDAO{
	
	private Connection conexao;

	@Override
	public void gravar(Peso peso) throws DBException{
		PreparedStatement pstmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			pstmt = conexao.prepareStatement("INSERT INTO T_HT_PESO" 
					+ "(cd_peso, dt_pesagem, nr_peso, t_usuario_cd_usuario)" 
					+ "VALUES (SQ_PESO.nextval, ?, ?, ?)");
			
			java.sql.Date data = new java.sql.Date(peso.getDataPeso().getTimeInMillis());
			pstmt.setDate(1, data);

			pstmt.setFloat(2, peso.getValorPeso());
			pstmt.setInt(3, peso.getIdUsuario());

			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastrar.");
		} finally {
				try {
					pstmt.close();
					conexao.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}

	@Override
	public Peso buscarPorId(int idPeso) {
		Peso peso = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			pstmt = conexao.prepareStatement("SELECT * FROM T_HT_PESO WHERE cd_peso = ?");
			
			pstmt.setInt(1, idPeso);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int idUsuario = rs.getInt("T_USUARIO_CD_USUARIO");
				Float valorPeso = rs.getFloat("NR_PESO");
				
				java.sql.Date data = rs.getDate("DT_PESAGEM");
				Calendar dataPeso = Calendar.getInstance();
				dataPeso.setTimeInMillis(data.getTime());
				
				peso = new Peso(idPeso, idUsuario, dataPeso, valorPeso);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					pstmt.close();
					conexao.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return peso;
	}

	@Override
	public List<Peso> mostrarLista(int idUsuario) {
		List<Peso> lista = new ArrayList<Peso>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			pstmt = conexao.prepareStatement("SELECT * FROM T_HT_PESO WHERE t_usuario_cd_usuario = ? ORDER BY dt_pesagem DESC, cd_peso DESC");
			pstmt.setInt(1, idUsuario);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int idPeso = rs.getInt("CD_PESO");
				Float valorPeso = rs.getFloat("NR_PESO");
				
				java.sql.Date data = rs.getDate("DT_PESAGEM");
				Calendar dataPeso = Calendar.getInstance();
				dataPeso.setTimeInMillis(data.getTime());
				
				Peso peso = new Peso(idPeso, idUsuario, dataPeso, valorPeso);
				lista.add(peso);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return lista;
	}

	@Override
	public void atualizar(Peso peso) throws DBException{
		PreparedStatement pstmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			pstmt = conexao.prepareStatement("UPDATE T_HT_PESO SET NR_PESO = ?, DT_PESAGEM = ? "
					+ "WHERE CD_PESO = ?");
			
			pstmt.setFloat(1, peso.getValorPeso());
			java.sql.Date data = new java.sql.Date(peso.getDataPeso().getTimeInMillis());
			pstmt.setDate(2,  data);
			pstmt.setInt(3,  peso.getIdPeso());
			
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao atualizar.");
		} finally {
				try {
					pstmt.close();
					conexao.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}

	@Override
	public void deletar(int idPeso) throws DBException {
		PreparedStatement pstmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			pstmt = conexao.prepareStatement("DELETE FROM T_HT_PESO WHERE CD_PESO = ?");
			
			pstmt.setInt(1, idPeso);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao excluir.");
		} finally {
				try {
					pstmt.close();
					conexao.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}	

}
