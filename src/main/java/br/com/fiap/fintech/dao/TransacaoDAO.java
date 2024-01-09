package br.com.fiap.fintech.dao;

import java.util.List;

import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.Transacao;

public interface TransacaoDAO {
void cadastrar(Transacao transacao) throws DBException;
	
	void atualizar(Transacao transacao) throws DBException;
	
	void remover(int id_transacao) throws DBException;
	
	Transacao buscar(int id_transacao);
	
	List<Transacao> listar();
}
