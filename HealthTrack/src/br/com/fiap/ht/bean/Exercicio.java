package br.com.fiap.ht.bean;

import java.util.Calendar;

public class Exercicio {

	private int idExercicio;
	private int idUsuario;
	private Calendar dataExercicio;
	private String tipoExercicio;
	private String descricaoExercicio;
	private float caloriasExercicio;
	
	public Exercicio() {
		
	}
	
	public Exercicio(int idExercicio, int idUsuario, Calendar dataExercicio, String tipoExercicio,
			String descricaoExercicio, float caloriasExercicio) {
		this.idExercicio = idExercicio;
		this.idUsuario = idUsuario;
		this.dataExercicio = dataExercicio;
		this.tipoExercicio = tipoExercicio;
		this.descricaoExercicio = descricaoExercicio;
		this.caloriasExercicio = caloriasExercicio;
	}

	public int getIdExercicio() {
		return idExercicio;
	}

	public void setIdExercicio(int idExercicio) {
		this.idExercicio = idExercicio;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Calendar getDataExercicio() {
		return dataExercicio;
	}

	public void setDataExercicio(Calendar dataExercicio) {
		this.dataExercicio = dataExercicio;
	}

	public String getTipoExercicio() {
		return tipoExercicio;
	}

	public void setTipoExercicio(String tipoExercicio) {
		this.tipoExercicio = tipoExercicio;
	}

	public String getDescricaoExercicio() {
		return descricaoExercicio;
	}

	public void setDescricaoExercicio(String descricaoExercicio) {
		this.descricaoExercicio = descricaoExercicio;
	}

	public float getCaloriasExercicio() {
		return caloriasExercicio;
	}

	public void setCaloriasExercicio(float caloriasExercicio) {
		this.caloriasExercicio = caloriasExercicio;
	}
	
	
}
