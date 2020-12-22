package br.com.fiap.ht.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.ht.bean.Refeicao;
import br.com.fiap.ht.dao.RefeicaoDAO;
import br.com.fiap.ht.exception.DBException;
import br.com.fiap.ht.singleton.ConnectionManager;


public class RefeicaoDAOImpl implements RefeicaoDAO{
	
	private Connection conexao;

	@Override
	public void gravar(Refeicao refeicao) throws DBException {
		PreparedStatement pstmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			pstmt = conexao.prepareStatement("INSERT INTO T_HT_REFEICAO (cd_refeicao, dt_refeicao, "
					+ "nr_calorias, ds_refeicao, t_usuario_cd_usuario, nm_tipo_ref)" 
					+ "VALUES (SQ_REFEICAO.nextval, ?, ?, ?, ?, ?)");
			
			java.sql.Date data = new java.sql.Date(refeicao.getDataRefeicao().getTimeInMillis());
			pstmt.setDate(1, data);
			
			pstmt.setFloat(2, refeicao.getCaloriasRefeicao());
			pstmt.setString(3, refeicao.getDescricaoRefeicao());
			pstmt.setInt(4, refeicao.getIdUsuario());
			pstmt.setString(5, refeicao.getTipoRefeicao());
			
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
	public Refeicao buscarPorId(int idRefeicao) {
		Refeicao refeicao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			pstmt = conexao.prepareStatement("SELECT * FROM T_HT_REFEICAO WHERE cd_refeicao = ?");
			
			pstmt.setInt(1, idRefeicao);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int idUsuario = rs.getInt("T_USUARIO_CD_USUARIO");
				
				java.sql.Date data = rs.getDate("DT_REFEICAO");
				Calendar dataRefeicao = Calendar.getInstance();
				dataRefeicao.setTimeInMillis(data.getTime());
				
				String tipoRefeicao = rs.getString("NM_TIPO_REF");
				String descricaoRefeicao = rs.getString("DS_REFEICAO");
				Float caloriasRefeicao = rs.getFloat("NR_CALORIAS");

				
				
				refeicao = new Refeicao (idRefeicao, idUsuario, dataRefeicao, tipoRefeicao, descricaoRefeicao, caloriasRefeicao);
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
		
		return refeicao;
	}

	@Override
	public List<Refeicao> mostrarLista(int idUsuario) {
		List<Refeicao> lista = new ArrayList<Refeicao>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			pstmt = conexao.prepareStatement("SELECT * FROM T_HT_REFEICAO WHERE t_usuario_cd_usuario = ? ORDER BY dt_refeicao DESC, cd_refeicao DESC");
			pstmt.setInt(1, idUsuario);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int idRefeicao = rs.getInt("CD_REFEICAO");
				
				java.sql.Date data = rs.getDate("DT_REFEICAO");
				Calendar dataRefeicao = Calendar.getInstance();
				dataRefeicao.setTimeInMillis(data.getTime());
				
				String tipoRefeicao = rs.getString("NM_TIPO_REF");
				String descricaoRefeicao = rs.getString("DS_REFEICAO");
				Float caloriasRefeicao = rs.getFloat("NR_CALORIAS");
				
				Refeicao refeicao = new Refeicao(idRefeicao, idUsuario, dataRefeicao, tipoRefeicao, descricaoRefeicao, caloriasRefeicao);
				lista.add(refeicao);
				
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
	public void atualizar(Refeicao refeicao) throws DBException {
		PreparedStatement pstmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			pstmt = conexao.prepareStatement("UPDATE T_HT_REFEICAO SET NM_TIPO_REF = ?, DS_REFEICAO = ?, NR_CALORIAS = ?, DT_REFEICAO = ? "
					+ "WHERE CD_REFEICAO = ?");
			
			pstmt.setString(1, refeicao.getTipoRefeicao());
			pstmt.setString(2, refeicao.getDescricaoRefeicao());
			pstmt.setFloat(3, refeicao.getCaloriasRefeicao());
			
			java.sql.Date data = new java.sql.Date(refeicao.getDataRefeicao().getTimeInMillis());
			pstmt.setDate(4,  data);
			
			pstmt.setInt(5, refeicao.getIdRefeicao());
			
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
	public void deletar(int idRefeicao) throws DBException {
		PreparedStatement pstmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			pstmt = conexao.prepareStatement("DELETE FROM T_HT_REFEICAO WHERE CD_REFEICAO = ?");
			
			pstmt.setInt(1, idRefeicao);
			
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
