package br.com.fiap.fintech.model;

import java.time.LocalDate;

public class Transacao {
	private int id_transacao;
	private int id_conta;
	private double nr_valor;
	private String nome_transacao;
	private LocalDate dt_ocorrencia; 
	private int nr_periodicidade;
	private boolean cd_receita_ou_debito;
	private int cd_tipo;
	
	public Transacao(int id_transacao, int id_conta, double nr_valor, String nome_transacao, LocalDate dt_ocorrencia,
			int nr_periodicidade, boolean cd_receita_ou_debito, int cd_tipo) {
		super();
		this.id_transacao = id_transacao;
		this.id_conta = id_conta;
		this.nr_valor = nr_valor;
		this.nome_transacao = nome_transacao;
		this.dt_ocorrencia = dt_ocorrencia;
		this.nr_periodicidade = nr_periodicidade;
		this.cd_receita_ou_debito = cd_receita_ou_debito;
		this.cd_tipo = cd_tipo;
	}

	public Transacao() {
		super();
	}

	public int getId_transacao() {
		return id_transacao;
	}

	public void setId_transacao(int id_transacao) {
		this.id_transacao = id_transacao;
	}

	public int getId_conta() {
		return id_conta;
	}

	public void setId_conta(int id_conta) {
		this.id_conta = id_conta;
	}

	public double getNr_valor() {
		return nr_valor;
	}

	public void setNr_valor(double nr_valor) {
		this.nr_valor = nr_valor;
	}

	public String getNome_transacao() {
		return nome_transacao;
	}

	public void setNome_transacao(String nome_transacao) {
		this.nome_transacao = nome_transacao;
	}

	public int getNr_periodicidade() {
		return nr_periodicidade;
	}

	public void setNr_periodicidade(int nr_periodicidade) {
		this.nr_periodicidade = nr_periodicidade;
	}

	public boolean getCd_receita_ou_debito() {
		return cd_receita_ou_debito;
	}

	public void setCd_receita_ou_debito(boolean cd_receita_ou_debito) {
		this.cd_receita_ou_debito = cd_receita_ou_debito;
	}

	public int getCd_tipo() {
		return cd_tipo;
	}

	public void setCd_tipo(int cd_tipo) {
		this.cd_tipo = cd_tipo;
	}

	public LocalDate getDt_ocorrencia() {
		return dt_ocorrencia;
	}

	public void setDt_ocorrencia(LocalDate dt_ocorrencia) {
		this.dt_ocorrencia = dt_ocorrencia;
	}
}
