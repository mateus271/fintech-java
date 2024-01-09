package br.com.fiap.fintech.model;

public class ContaBancaria {
	private int id_conta;
	private int id_usuario;
	private int cd_banco;
	private double nr_valor_saldo;
	private int cd_tipo_conta;
	private double nr_limite_credito;
	
	public ContaBancaria(int id_conta, int id_usuario, int cd_banco, double nr_valor_saldo, int cd_tipo_conta,
			double nr_limite_credito) {
		super();
		this.id_conta = id_conta;
		this.id_usuario = id_usuario;
		this.cd_banco = cd_banco;
		this.nr_valor_saldo = nr_valor_saldo;
		this.cd_tipo_conta = cd_tipo_conta;
		this.nr_limite_credito = nr_limite_credito;
	}

	public ContaBancaria() {
		super();
	}

	public int getId_conta() {
		return id_conta;
	}

	public void setId_conta(int id_conta) {
		this.id_conta = id_conta;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public int getCd_banco() {
		return cd_banco;
	}

	public void setCd_banco(int cd_banco) {
		this.cd_banco = cd_banco;
	}

	public double getNr_valor_saldo() {
		return nr_valor_saldo;
	}

	public void setNr_valor_saldo(double nr_valor_saldo) {
		this.nr_valor_saldo = nr_valor_saldo;
	}

	public int getCd_tipo_conta() {
		return cd_tipo_conta;
	}

	public void setCd_tipo_conta(int cd_tipo_conta) {
		this.cd_tipo_conta = cd_tipo_conta;
	}

	public double getNr_limite_credito() {
		return nr_limite_credito;
	}

	public void setNr_limite_credito(double nr_limite_credito) {
		this.nr_limite_credito = nr_limite_credito;
	}
}
