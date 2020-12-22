package br.com.fiap.ht.bean;

import java.util.Calendar;

public class Peso {
	
	private int idPeso;
	private int idUsuario;
	private Calendar dataPeso;
	private float valorPeso;
	
	public Peso() {
		
	}
	
	public Peso(int idPeso, int idUsuario, Calendar dataPeso, float valorPeso) {
		this.idPeso = idPeso;
		this.idUsuario = idUsuario;
		this.dataPeso = dataPeso;
		this.valorPeso = valorPeso;	
	}

	public int getIdPeso() {
		return idPeso;
	}

	public void setIdPeso(int idPeso) {
		this.idPeso = idPeso;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public float getValorPeso() {
		return valorPeso;
	}
	
	public Calendar getDataPeso() {
		return dataPeso;
	}

	public void setDataPeso(Calendar dataPeso) {
		this.dataPeso = dataPeso;
	}

	public void setValorPeso(float valorPeso) {
		this.valorPeso = valorPeso;
	}

}
