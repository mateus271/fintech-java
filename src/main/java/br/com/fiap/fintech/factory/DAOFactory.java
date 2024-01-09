package br.com.fiap.fintech.factory;

import br.com.fiap.fintech.dao.ContaBancariaDAO;
import br.com.fiap.fintech.dao.FaturaCartaoDAO;
import br.com.fiap.fintech.dao.LancamentoDAO;
import br.com.fiap.fintech.dao.TransacaoDAO;
import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.dao.impl.OracleContaBancariaDAO;
import br.com.fiap.fintech.dao.impl.OracleFaturaCartaoDAO;
import br.com.fiap.fintech.dao.impl.OracleLancamentoDAO;
import br.com.fiap.fintech.dao.impl.OracleTransacaoDAO;
import br.com.fiap.fintech.dao.impl.OracleUsuarioDAO;

public class DAOFactory {
	public static ContaBancariaDAO getContaBancariaDAO () {
		return new OracleContaBancariaDAO();
	}
	
	public static FaturaCartaoDAO getFaturaCartaoDAO () {
		return new OracleFaturaCartaoDAO();
	}
	
	public static LancamentoDAO getLancamentoDAO () {
		return new OracleLancamentoDAO();
	}
	
	public static TransacaoDAO getTransacaoDAO () {
		return new OracleTransacaoDAO();
	}
	
	public static UsuarioDAO getUsuarioDAO () {
		return new OracleUsuarioDAO();
	}
}
