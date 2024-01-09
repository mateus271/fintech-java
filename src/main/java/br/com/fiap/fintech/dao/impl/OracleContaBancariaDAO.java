package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.fintech.dao.ContaBancariaDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.ContaBancaria;
import br.com.fiap.fintech.singleton.ConnectionManager;

public class OracleContaBancariaDAO implements ContaBancariaDAO {
	private Connection conexao;

	@Override
	public void cadastrar(ContaBancaria conta) throws DBException {

		PreparedStatement statement = null;

		try {

			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "INSERT INTO T_CONTA_BANCARIA (ID_CONTA, ID_USUARIO, CD_BANCO, NR_VALOR_SALDO, CD_TIPO_CONTA, NR_LIMITE_CREDITO) "
					+ "VALUES (sq_conta.nextval, ?, ?, ?, ?, ?)";

			statement = conexao.prepareStatement(sql);

			statement.setInt(1, conta.getId_usuario());
			statement.setInt(2, conta.getCd_banco());
			statement.setDouble(3, conta.getNr_valor_saldo());
			statement.setInt(4, conta.getCd_tipo_conta());
			statement.setDouble(5, conta.getNr_limite_credito());

			statement.executeUpdate();

			System.out.println("Conta cadastrada com sucesso!");

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DBException("Erro ao cadastrar.");

		} finally {

			try {

				statement.close();

				conexao.close();

			} catch (SQLException e) {

				e.printStackTrace();

			}
		}

	}

	@Override
	public void atualizar(ContaBancaria conta) throws DBException {

		PreparedStatement statement = null;

		try {

			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "UPDATE T_CONTA_BANCARIA SET ID_USUARIO = ?, CD_BANCO = ?, NR_VALOR_SALDO = ?, CD_TIPO_CONTA = ?, NR_LIMITE_CREDITO = ?"
					+ "WHERE ID_CONTA = ?";

			statement = conexao.prepareStatement(sql);

			statement.setInt(1, conta.getId_usuario());
			statement.setInt(2, conta.getCd_banco());
			statement.setDouble(3, conta.getNr_valor_saldo());
			statement.setInt(4, conta.getCd_tipo_conta());
			statement.setDouble(5, conta.getNr_limite_credito());
			statement.setInt(6, conta.getId_conta());

			statement.executeUpdate();

			System.out.println("Conta atualizada com sucesso!");

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DBException("Erro ao atualizar.");

		} finally {

			try {

				statement.close();

				conexao.close();

			} catch (SQLException e) {

				e.printStackTrace();

			}
		}

	}

	@Override
	public void remover(int id_conta) throws DBException {

		PreparedStatement statement = null;

		try {

			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "DELETE FROM T_CONTA_BANCARIA WHERE ID_CONTA = ?";

			statement = conexao.prepareStatement(sql);

			statement.setInt(1, id_conta);

			statement.executeUpdate();

			System.out.println("Conta removida com sucesso!");

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DBException("Erro ao remover.");

		} finally {

			try {

				statement.close();

				conexao.close();

			} catch (SQLException e) {

				e.printStackTrace();

			}
		}

	}

	@Override
	public ContaBancaria buscar(int id_conta) {
		ContaBancaria conta = null;

		PreparedStatement statement = null;

		ResultSet result = null;

		try {

			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "SELECT * FROM T_CONTA_BANCARIA WHERE ID_CONTA = ?";

			statement = conexao.prepareStatement(sql);

			statement.setInt(1, id_conta);

			result = statement.executeQuery();

			if (result.next()) {
				int idConta = result.getInt("ID_CONTA");
				int idUsuario = result.getInt("ID_USUARIO");
				int cdBanco = result.getInt("CD_BANCO");
				double valorSaldo = result.getDouble("NR_VALOR_SALDO");
				int cdTipoConta = result.getInt("CD_Tipo_Conta");
				double limiteCredito = result.getDouble("NR_LIMITE_CREDITO");

				conta = new ContaBancaria(idConta, idUsuario, cdBanco, valorSaldo, cdTipoConta, limiteCredito);

			}

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			try {

				statement.close();

				result.close();

				conexao.close();

			} catch (SQLException e) {

				e.printStackTrace();

			}
		}

		return conta;
	}

	@Override
	public List<ContaBancaria> listar() {
		List<ContaBancaria> lista = new ArrayList<ContaBancaria>();
		
		PreparedStatement statement = null;
		
		ResultSet result = null;
		
		try {

			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "SELECT * FROM T_CONTA_BANCARIA";

			statement = conexao.prepareStatement(sql);

			result = statement.executeQuery();
			
			while (result.next()) {
				int idConta = result.getInt("ID_CONTA");
				int idUsuario = result.getInt("ID_USUARIO");
				int cdBanco = result.getInt("CD_BANCO");
				double valorSaldo = result.getDouble("NR_VALOR_SALDO");
				int cdTipoConta = result.getInt("CD_Tipo_Conta");
				double limiteCredito = result.getDouble("NR_LIMITE_CREDITO");
				
				ContaBancaria conta = new ContaBancaria(idConta, idUsuario, cdBanco, valorSaldo, cdTipoConta, limiteCredito);
				
				lista.add(conta);
			}

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			try {

				statement.close();

				result.close();

				conexao.close();

			} catch (SQLException e) {

				e.printStackTrace();

			}
		}
		
		return lista;
	}
}
