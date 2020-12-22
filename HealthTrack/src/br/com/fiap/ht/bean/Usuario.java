package br.com.fiap.ht.bean;

import java.util.Calendar;

public class Usuario {
	
	private int idUsuario;
	private String nome;
	private Calendar dataNascimento;
	private String sexo;
	private int altura;
	private String email;
	private String senha;
	
	public Usuario() {
		
	}
	
	public Usuario(int idUsuario, String nome, Calendar dataNascimento, String sexo, int altura, 
			String email, String senha) {
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.altura = altura;
		this.email = email;
		this.senha = senha;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
