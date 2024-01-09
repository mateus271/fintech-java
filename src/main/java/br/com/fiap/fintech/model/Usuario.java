package br.com.fiap.fintech.model;

import br.com.fiap.fintech.util.CriptografiaUtils;

public class Usuario {
	private int id_usuario;
	private String nome_usuario;
	private String ds_email_usuario;
	private String ds_senha;
	
	public Usuario(int id_usuario, String nome_usuario, String ds_email_usuario, String ds_senha) {
		super();
		this.id_usuario = id_usuario;
		this.nome_usuario = nome_usuario;
		this.ds_email_usuario = ds_email_usuario;
		setDs_senha(ds_senha);
	}
	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNome_usuario() {
		return nome_usuario;
	}

	public void setNome_usuario(String nome_usuario) {
		this.nome_usuario = nome_usuario;
	}

	public String getDs_email_usuario() {
		return ds_email_usuario;
	}

	public void setDs_email_usuario(String ds_email_usuario) {
		this.ds_email_usuario = ds_email_usuario;
	}
	
	public String getDs_senha() {
		return ds_senha;
	}

	public void setDs_senha(String ds_senha) {
		try {
			this.ds_senha = CriptografiaUtils.criptografar(ds_senha);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
