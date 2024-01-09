package br.com.fiap.fintech.dao;

import java.util.List;

import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.ContaBancaria;

public interface ContaBancariaDAO {
	void cadastrar(ContaBancaria conta) throws DBException;
	
	void atualizar(ContaBancaria conta) throws DBException;
	
	void remover(int id_conta) throws DBException;
	
	ContaBancaria buscar(int id_conta);
	
	List<ContaBancaria> listar();
}
