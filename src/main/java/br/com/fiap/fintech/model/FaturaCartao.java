package br.com.fiap.fintech.model;

import java.time.LocalDate;

public class FaturaCartao {
	private int id_fatura;
	private int id_conta;
	private double nr_valor_fatura;
	private LocalDate dt_fatura;
	private LocalDate dt_fechamento_fatura;
	
	public FaturaCartao(int id_fatura, int id_conta, double nr_valor_fatura, LocalDate dt_fatura,
			LocalDate dt_fechamento_fatura) {
		super();
		this.id_fatura = id_fatura;
		this.id_conta = id_conta;
		this.nr_valor_fatura = nr_valor_fatura;
		this.dt_fatura = dt_fatura;
		this.dt_fechamento_fatura = dt_fechamento_fatura;
	}
	
	public FaturaCartao() {
		super();
	}

	public int getId_fatura() {
		return id_fatura;
	}

	public void setId_fatura(int id_fatura) {
		this.id_fatura = id_fatura;
	}

	public int getId_conta() {
		return id_conta;
	}

	public void setId_conta(int id_conta) {
		this.id_conta = id_conta;
	}

	public double getNr_valor_fatura() {
		return nr_valor_fatura;
	}

	public void setNr_valor_fatura(double nr_valor_fatura) {
		this.nr_valor_fatura = nr_valor_fatura;
	}

	public LocalDate getDt_fatura() {
		return dt_fatura;
	}

	public void setDt_fatura(LocalDate dt_fatura) {
		this.dt_fatura = dt_fatura;
	}

	public LocalDate getDt_fechamento_fatura() {
		return dt_fechamento_fatura;
	}

	public void setDt_fechamento_fatura(LocalDate dt_fechamento_fatura) {
		this.dt_fechamento_fatura = dt_fechamento_fatura;
	}
}
