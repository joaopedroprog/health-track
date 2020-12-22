package br.com.fiap.ht.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.ht.bean.Pressao;
import br.com.fiap.ht.dao.PressaoDAO;
import br.com.fiap.ht.exception.DBException;
import br.com.fiap.ht.singleton.ConnectionManager;


public class PressaoDAOImpl implements PressaoDAO{
	
	private Connection conexao;

	@Override
	public void gravar(Pressao pressao) throws DBException {
		PreparedStatement pstmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			pstmt = conexao.prepareStatement("INSERT INTO T_HT_PRESSAO" 
					+ "(cd_pressao, dt_pressao, nr_sistolica, nr_diastolica, t_usuario_cd_usuario)" 
					+ "VALUES (SQ_PRESSAO.nextval, ?, ?, ?, ?)");
			
			java.sql.Date data = new java.sql.Date(pressao.getDataPressao().getTimeInMillis());
			pstmt.setDate(1, data);
			
			pstmt.setInt(2, pressao.getPressaoSistolica());
			pstmt.setInt(3, pressao.getPressaoDiastolica());
			pstmt.setInt(4, pressao.getIdUsuario());

			
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
	public Pressao buscarPorId(int idPressao) {
		Pressao pressao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			pstmt = conexao.prepareStatement("SELECT * FROM T_HT_PRESSAO WHERE cd_pressao = ?");
			
			pstmt.setInt(1, idPressao);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int idUsuario = rs.getInt("T_USUARIO_CD_USUARIO");
				int pressaoSistolica = rs.getInt("NR_SISTOLICA");
				int pressaoDiastolica = rs.getInt("NR_DIASTOLICA");
				
				java.sql.Date data = rs.getDate("DT_PRESSAO");
				Calendar dataPressao = Calendar.getInstance();
				dataPressao.setTimeInMillis(data.getTime());
				
				pressao = new Pressao(idPressao, idUsuario, dataPressao, pressaoSistolica, pressaoDiastolica);
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
		
		return pressao;
	}

	@Override
	public List<Pressao> mostrarLista(int idUsuario) {
		List<Pressao> lista = new ArrayList<Pressao>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			pstmt = conexao.prepareStatement("SELECT * FROM T_HT_PRESSAO WHERE t_usuario_cd_usuario = ? ORDER BY dt_pressao DESC, cd_pressao DESC");
			pstmt.setInt(1, idUsuario);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int idPressao = rs.getInt("CD_PRESSAO");
				int pressaoSistolica = rs.getInt("NR_SISTOLICA");
				int pressaoDiastolica = rs.getInt("NR_DIASTOLICA");

				java.sql.Date data = rs.getDate("DT_PRESSAO");
				Calendar dataPressao = Calendar.getInstance();
				dataPressao.setTimeInMillis(data.getTime());
				
				Pressao pressao = new Pressao(idPressao, idUsuario, dataPressao, pressaoSistolica, pressaoDiastolica);
			
				lista.add(pressao);
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
	public void atualizar(Pressao pressao) throws DBException {
		PreparedStatement pstmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			pstmt = conexao.prepareStatement("UPDATE T_HT_PRESSAO SET NR_SISTOLICA = ?, NR_DIASTOLICA = ?, DT_PRESSAO = ? "
					+ "WHERE CD_PRESSAO = ?");
			
			pstmt.setInt(1, pressao.getPressaoSistolica());
			pstmt.setInt(2,  pressao.getPressaoDiastolica());
			java.sql.Date data = new java.sql.Date(pressao.getDataPressao().getTimeInMillis());
			pstmt.setDate(3,  data);
			pstmt.setInt(4, pressao.getIdPressao());
			
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
	public void deletar(int idPressao) throws DBException {
		PreparedStatement pstmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			pstmt = conexao.prepareStatement("DELETE FROM T_HT_PRESSAO WHERE CD_PRESSAO = ?");
			
			pstmt.setInt(1, idPressao);
			
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
