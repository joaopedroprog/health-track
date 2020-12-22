package br.com.fiap.ht.bean;

import java.util.Calendar;

public class Refeicao {

	private int idRefeicao;
	private int idUsuario;
	private Calendar dataRefeicao;
	private String tipoRefeicao;
	private String descricaoRefeicao;
	private float caloriasRefeicao;
	
	public Refeicao(){
		
	}

	public Refeicao(int idRefeicao, int idUsuario, Calendar dataRefeicao, String tipoRefeicao, 
			String descricaoRefeicao, float caloriasRefeicao) {
		this.idRefeicao = idRefeicao;
		this.idUsuario = idUsuario;
		this.dataRefeicao = dataRefeicao;
		this.tipoRefeicao = tipoRefeicao;
		this.descricaoRefeicao = descricaoRefeicao;
		this.caloriasRefeicao = caloriasRefeicao;
	}

	public int getIdRefeicao() {
		return idRefeicao;
	}

	public void setIdRefeicao(int idRefeicao) {
		this.idRefeicao = idRefeicao;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Calendar getDataRefeicao() {
		return dataRefeicao;
	}

	public void setDataRefeicao(Calendar dataRefeicao) {
		this.dataRefeicao = dataRefeicao;
	}

	public String getTipoRefeicao() {
		return tipoRefeicao;
	}

	public void setTipoRefeicao(String tipoRefeicao) {
		this.tipoRefeicao = tipoRefeicao;
	}

	public String getDescricaoRefeicao() {
		return descricaoRefeicao;
	}

	public void setDescricaoRefeicao(String descricaoRefeicao) {
		this.descricaoRefeicao = descricaoRefeicao;
	}

	public float getCaloriasRefeicao() {
		return caloriasRefeicao;
	}

	public void setCaloriasRefeicao(float caloriasRefeicao) {
		this.caloriasRefeicao = caloriasRefeicao;
	}
	
	
}
