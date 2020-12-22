package br.com.fiap.ht.bean;

import java.util.Calendar;

public class Pressao {

	private int idPressao;
	private int idUsuario;
	private Calendar dataPressao;
	private int pressaoSistolica;
	private int pressaoDiastolica;
	
	public Pressao() {
		
	}

	public Pressao(int idPressao, int idUsuario, Calendar dataPressao, int pressaoSistolica, int pressaoDiastolica) {
		this.idPressao = idPressao;
		this.idUsuario = idUsuario;
		this.dataPressao = dataPressao;
		this.pressaoSistolica = pressaoSistolica;
		this.pressaoDiastolica = pressaoDiastolica;
	}

	public int getIdPressao() {
		return idPressao;
	}

	public void setIdPressao(int idPressao) {
		this.idPressao = idPressao;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Calendar getDataPressao() {
		return dataPressao;
	}

	public void setDataPressao(Calendar dataPressao) {
		this.dataPressao = dataPressao;
	}

	public int getPressaoSistolica() {
		return pressaoSistolica;
	}

	public void setPressaoSistolica(int pressaoSistolica) {
		this.pressaoSistolica = pressaoSistolica;
	}

	public int getPressaoDiastolica() {
		return pressaoDiastolica;
	}

	public void setPressaoDiastolica(int pressaoDiastolica) {
		this.pressaoDiastolica = pressaoDiastolica;
	}
	
}
