package br.com.fiap.fintech.dao;

import java.util.List;

import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.Lancamento;

public interface LancamentoDAO {
void cadastrar(Lancamento lancamento) throws DBException;
	
	void atualizar(Lancamento lancamento) throws DBException;
	
	void remover(int id_lancamento) throws DBException;
	
	Lancamento buscar(int id_lancamento);
	
	List<Lancamento> listar();
}
