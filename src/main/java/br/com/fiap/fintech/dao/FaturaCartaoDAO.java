package br.com.fiap.fintech.dao;

import java.util.List;

import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.FaturaCartao;

public interface FaturaCartaoDAO {	
void cadastrar(FaturaCartao fatura) throws DBException;
	
	void atualizar(FaturaCartao fatura) throws DBException;
	
	void remover(int id_fatura) throws DBException;
	
	FaturaCartao buscar(int id_fatura);
	
	List<FaturaCartao> listar();
}
