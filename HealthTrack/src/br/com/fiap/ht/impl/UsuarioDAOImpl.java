package br.com.fiap.ht.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import br.com.fiap.ht.bean.Usuario;
import br.com.fiap.ht.dao.UsuarioDAO;
import br.com.fiap.ht.exception.DBException;
import br.com.fiap.ht.singleton.ConnectionManager;


public class UsuarioDAOImpl implements UsuarioDAO {
	
	private Connection conexao;

	@Override
	public void gravar(Usuario usuario) throws DBException {
		PreparedStatement pstmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			pstmt = conexao.prepareStatement("INSERT INTO T_HT_USUARIO "
					+ "(cd_usuario, nm_usuario, ds_email, ds_senha, dt_nascimento, nr_altura, ds_genero) "
					+ "VALUES (SQ_USUARIO.nextval, ?, ?, ?, ?, ?, ?)");
			
			pstmt.setString(1, usuario.getNome());
			pstmt.setString(2, usuario.getEmail());
			pstmt.setString(3, usuario.getSenha());
			java.sql.Date data = new java.sql.Date(usuario.getDataNascimento().getTimeInMillis());
			pstmt.setDate(4, data);			
			pstmt.setInt(5, usuario.getAltura());
			pstmt.setString(6, usuario.getSexo());
			
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
	public Usuario buscarPorId(int idUsuario) {
		
		Usuario usuario = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			pstmt = conexao.prepareStatement("SELECT * FROM T_HT_USUARIO WHERE cd_usuario = ?");
			
			pstmt.setInt(1, idUsuario);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String nome = rs.getString("NM_USUARIO");
				String email = rs.getString("DS_EMAIL");
				String senha = rs.getString("DS_SENHA");
				java.sql.Date data = rs.getDate("DT_NASCIMENTO");
				Calendar dataNascimento = Calendar.getInstance();
				dataNascimento.setTimeInMillis(data.getTime());
				int altura = rs.getInt("NR_ALTURA");
				String sexo = rs.getString("DS_GENERO");
				
				usuario = new Usuario(idUsuario, nome, dataNascimento, sexo, altura, email, senha);
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
		
		return usuario;
	}

	@Override
	public void atualizar(Usuario usuario) throws DBException {

		PreparedStatement pstmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			pstmt = conexao.prepareStatement("UPDATE T_HT_USUARIO SET NM_USUARIO = ?, DS_EMAIL = ?,"
					+ "DS_SENHA = ?, DT_NASCIMENTO = ?, NR_ALTURA = ?, DS_GENERO = ? "
					+ "WHERE CD_USUARIO = ?");
			
			pstmt.setString(1, usuario.getNome());
			pstmt.setString(2,  usuario.getEmail());
			pstmt.setString(3,  usuario.getSenha());
			java.sql.Date data = new java.sql.Date(usuario.getDataNascimento().getTimeInMillis());
			pstmt.setDate(4, data);
			pstmt.setInt(5,  usuario.getAltura());
			pstmt.setString(6,  usuario.getSexo());
			pstmt.setInt(7, usuario.getIdUsuario());
			
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
	public void deletar(int idUsuario) throws DBException{

		PreparedStatement pstmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			pstmt = conexao.prepareStatement("DELETE FROM T_HT_USUARIO WHERE CD_USUARIO = ?");
			
			pstmt.setInt(1, idUsuario);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao deletar.");
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
