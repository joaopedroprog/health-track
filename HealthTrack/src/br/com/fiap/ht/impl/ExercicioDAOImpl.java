package br.com.fiap.ht.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.ht.bean.Exercicio;
import br.com.fiap.ht.dao.ExercicioDAO;
import br.com.fiap.ht.exception.DBException;
import br.com.fiap.ht.singleton.ConnectionManager;


public class ExercicioDAOImpl implements ExercicioDAO{
	
	private Connection conexao;

	@Override
	public void gravar(Exercicio exercicio) throws DBException {
		PreparedStatement pstmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			pstmt = conexao.prepareStatement("INSERT INTO T_HT_EXERCICIO (cd_exercicio, dt_exercicio, nr_calorias, ds_exercicio, "
					+ "t_usuario_cd_usuario, nm_tipo_exercicio) "
					+ "VALUES (SQ_EXERCICIO.nextval, ?, ?, ?, ?, ?)");
			
			java.sql.Date data = new java.sql.Date(exercicio.getDataExercicio().getTimeInMillis());
			pstmt.setDate(1, data);
			
			pstmt.setFloat(2, exercicio.getCaloriasExercicio());
			pstmt.setString(3, exercicio.getDescricaoExercicio());
			pstmt.setInt(4, exercicio.getIdUsuario());
			pstmt.setString(5, exercicio.getTipoExercicio());
			
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
	public Exercicio buscarPorId(int idExercicio) {
		Exercicio exercicio = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			pstmt = conexao.prepareStatement("SELECT * FROM T_HT_EXERCICIO WHERE cd_exercicio = ?");
			
			pstmt.setInt(1, idExercicio);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int idUsuario = rs.getInt("T_USUARIO_CD_USUARIO");
				
				java.sql.Date data = rs.getDate("DT_EXERCICIO");
				Calendar dataExercicio = Calendar.getInstance();
				dataExercicio.setTimeInMillis(data.getTime());
				
				String tipoExercicio = rs.getString("NM_TIPO_EXERCICIO");
				String descricaoExercicio = rs.getString("DS_EXERCICIO");
				Float caloriasExercicio = rs.getFloat("NR_CALORIAS");

				
				
				exercicio = new Exercicio(idExercicio, idUsuario, dataExercicio, tipoExercicio, descricaoExercicio, caloriasExercicio);
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
		
		return exercicio;
	}

	@Override
	public List<Exercicio> mostrarLista(int idUsuario) {
		List<Exercicio> lista = new ArrayList<Exercicio>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			pstmt = conexao.prepareStatement("SELECT * FROM T_HT_EXERCICIO WHERE t_usuario_cd_usuario = ? ORDER BY dt_exercicio DESC, cd_exercicio DESC");
			pstmt.setInt(1, idUsuario);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int idExercicio = rs.getInt("CD_EXERCICIO");
				
				java.sql.Date data = rs.getDate("DT_EXERCICIO");
				Calendar dataExercicio = Calendar.getInstance();
				dataExercicio.setTimeInMillis(data.getTime());
				
				String tipoExercicio = rs.getString("NM_TIPO_EXERCICIO");
				String descricaoExercicio = rs.getString("DS_EXERCICIO");
				Float caloriasExercicio = rs.getFloat("NR_CALORIAS");
				
				Exercicio exercicio = new Exercicio(idExercicio, idUsuario, dataExercicio, tipoExercicio, descricaoExercicio, caloriasExercicio);
				lista.add(exercicio);
				
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
	public void atualizar(Exercicio exercicio) throws DBException {
		PreparedStatement pstmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			pstmt = conexao.prepareStatement("UPDATE T_HT_EXERCICIO SET NM_TIPO_EXERCICIO = ?, DS_EXERCICIO = ?, NR_CALORIAS = ?, DT_EXERCICIO = ? "
					+ "WHERE CD_EXERCICIO = ?");
			
			pstmt.setString(1, exercicio.getTipoExercicio());
			pstmt.setString(2, exercicio.getDescricaoExercicio());
			pstmt.setFloat(3, exercicio.getCaloriasExercicio());
			
			java.sql.Date data = new java.sql.Date(exercicio.getDataExercicio().getTimeInMillis());
			pstmt.setDate(4,  data);
			
			pstmt.setInt(5, exercicio.getIdExercicio());
			
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
	public void deletar(int idExercicio) throws DBException {
		PreparedStatement pstmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			pstmt = conexao.prepareStatement("DELETE FROM T_HT_EXERCICIO WHERE CD_EXERCICIO = ?");
			
			pstmt.setInt(1, idExercicio);
			
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
