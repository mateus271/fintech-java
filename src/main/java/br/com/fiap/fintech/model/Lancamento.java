package br.com.fiap.fintech.model;

import java.time.LocalDate;

public class Lancamento {
	private int id_lancamento;
	private int id_fatura;
	private String nome_lancamento;
	private double nr_valor_lancamento;
	private int ds_periodicidade;
	private boolean cd_pagamento_ou_estorno;
	private LocalDate dt_ocorrencia;
	private int cd_tipo;
	
	public Lancamento(int id_lancamento, int id_fatura, String nome_lancamento, double nr_valor_lancamento,
			int ds_periodicidade, boolean cd_pagamento_ou_estorno, LocalDate dt_ocorrencia, int cd_tipo) {
		super();
		this.id_lancamento = id_lancamento;
		this.id_fatura = id_fatura;
		this.nome_lancamento = nome_lancamento;
		this.nr_valor_lancamento = nr_valor_lancamento;
		this.ds_periodicidade = ds_periodicidade;
		this.cd_pagamento_ou_estorno = cd_pagamento_ou_estorno;
		this.dt_ocorrencia = dt_ocorrencia;
		this.cd_tipo = cd_tipo;
	}
	
	public Lancamento() {
		super();
	}

	public int getId_lancamento() {
		return id_lancamento;
	}

	public void setId_lancamento(int id_lancamento) {
		this.id_lancamento = id_lancamento;
	}

	public int getId_fatura() {
		return id_fatura;
	}

	public void setId_fatura(int id_fatura) {
		this.id_fatura = id_fatura;
	}

	public String getNome_lancamento() {
		return nome_lancamento;
	}

	public void setNome_lancamento(String nome_lancamento) {
		this.nome_lancamento = nome_lancamento;
	}

	public double getNr_valor_lancamento() {
		return nr_valor_lancamento;
	}

	public void setNr_valor_lancamento(double nr_valor_lancamento) {
		this.nr_valor_lancamento = nr_valor_lancamento;
	}

	public int getDs_periodicidade() {
		return ds_periodicidade;
	}

	public void setDs_periodicidade(int ds_periodicidade) {
		this.ds_periodicidade = ds_periodicidade;
	}

	public boolean isCd_pagamento_ou_estorno() {
		return cd_pagamento_ou_estorno;
	}

	public void setCd_pagamento_ou_estorno(boolean cd_pagamento_ou_estorno) {
		this.cd_pagamento_ou_estorno = cd_pagamento_ou_estorno;
	}

	public LocalDate getDt_ocorrencia() {
		return dt_ocorrencia;
	}

	public void setDt_ocorrencia(LocalDate dt_ocorrencia) {
		this.dt_ocorrencia = dt_ocorrencia;
	}

	public int getCd_tipo() {
		return cd_tipo;
	}

	public void setCd_tipo(int cd_tipo) {
		this.cd_tipo = cd_tipo;
	}
}
