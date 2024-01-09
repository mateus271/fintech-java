package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.fintech.dao.FaturaCartaoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.FaturaCartao;
import br.com.fiap.fintech.singleton.ConnectionManager;

public class OracleFaturaCartaoDAO implements FaturaCartaoDAO {
private Connection conexao;
	
	@Override
	public void cadastrar(FaturaCartao fatura) throws DBException {

		PreparedStatement statement = null;

		try {

			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "INSERT INTO T_FATURA_CARTAO (ID_FATURA, ID_CONTA, NR_VALOR_FATURA, DT_DATA_FATURA, DT_FECHAMENTO_FATURA) "
				+ "VALUES (sq_fatura.nextval, ?, ?, ?, ?)";

			statement = conexao.prepareStatement(sql);

			statement.setInt(1, fatura.getId_conta());
			statement.setDouble(2, fatura.getNr_valor_fatura());

			Date dataFatura = Date.valueOf(fatura.getDt_fatura());
			statement.setDate(3, dataFatura);

			Date dataFechamento = Date.valueOf(fatura.getDt_fechamento_fatura());
			statement.setDate(4, dataFechamento);

			statement.executeUpdate();

			System.out.println("Fatura cadastrada com sucesso!");

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
	public void atualizar(FaturaCartao fatura) throws DBException {
		
		PreparedStatement statement = null;

		try {

			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "UPDATE T_FATURA_CARTAO SET ID_CONTA = ?, NR_VALOR_FATURA = ?, DT_DATA_FATURA = ?, DT_FECHAMENTO_FATURA = ?"
					+ "WHERE ID_FATURA = ?";

			statement = conexao.prepareStatement(sql);

			statement.setInt(1, fatura.getId_conta());
			statement.setDouble(2, fatura.getNr_valor_fatura());
			
			Date dataFatura = Date.valueOf(fatura.getDt_fatura());
			statement.setDate(3, dataFatura);
			
			Date dataFechamento = Date.valueOf(fatura.getDt_fechamento_fatura());
			statement.setDate(4, dataFechamento);
			
			statement.setInt(5, fatura.getId_fatura());

			statement.executeUpdate();

			System.out.println("Fatura atualizada com sucesso!");

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
	public void remover(int id_fatura) throws DBException {

		PreparedStatement statement = null;

		try {

			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "DELETE FROM T_FATURA_CARTAO WHERE ID_FATURA = ?";

			statement = conexao.prepareStatement(sql);

			statement.setInt(1, id_fatura);

			statement.executeUpdate();

			System.out.println("Fatura removida com sucesso!");

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
	public FaturaCartao buscar(int id_fatura) {
		FaturaCartao fatura = null;

		PreparedStatement statement = null;

		ResultSet result = null;

		try {

			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "SELECT * FROM T_FATURA_CARTAO WHERE ID_FATURA = ?";

			statement = conexao.prepareStatement(sql);

			statement.setInt(1, id_fatura);

			result = statement.executeQuery();

			if (result.next()) {
				int idFatura = result.getInt("ID_FATURA");
				int idConta = result.getInt("ID_CONTA");
				double valorFatura = result.getInt("NR_VALOR_FATURA");
				
				Date dataFaturaBanco = result.getDate("DT_DATA_FATURA");
				LocalDate dataFatura = dataFaturaBanco.toLocalDate();
				
				Date dataFechamentoBanco = result.getDate("DT_FECHAMENTO_FATURA");
				LocalDate dataFechamento = dataFechamentoBanco.toLocalDate();

				fatura = new FaturaCartao(idFatura, idConta, valorFatura, dataFatura, dataFechamento);

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

		return fatura;
	}

	@Override
	public List<FaturaCartao> listar() {
		
		List<FaturaCartao> lista = new ArrayList<FaturaCartao>();
		
		PreparedStatement statement = null;
		
		ResultSet result = null;
		
		try {

			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "SELECT * FROM T_FATURA_CARTAO";

			statement = conexao.prepareStatement(sql);

			result = statement.executeQuery();
			
			while (result.next()) {
				FaturaCartao fatura = new FaturaCartao();
				fatura.setId_fatura(result.getInt("ID_FATURA"));
				fatura.setId_conta(result.getInt("ID_CONTA"));
				fatura.setNr_valor_fatura(result.getInt("NR_VALOR_FATURA"));
				
				Date dataFatura = result.getDate("DT_DATA_FATURA");
				fatura.setDt_fatura(dataFatura.toLocalDate());
				
				Date dataFechamentoFatura = result.getDate("DT_FECHAMENTO_FATURA");
				fatura.setDt_fechamento_fatura(dataFechamentoFatura.toLocalDate());
				
				lista.add(fatura);
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
