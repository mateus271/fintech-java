package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.fintech.dao.TransacaoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.Transacao;
import br.com.fiap.fintech.singleton.ConnectionManager;

public class OracleTransacaoDAO implements TransacaoDAO {
	private Connection conexao;

	@Override
	public void cadastrar(Transacao transacao) throws DBException {

		PreparedStatement statement = null;

		try {

			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "INSERT INTO T_TRANSACAO (ID_TRANSACAO, ID_CONTA, NR_VALOR, NOME_TRANSACAO, DT_OCORRENCIA, NR_PERIODICIDADE, CD_RECEITA_OU_DEBITO, CD_TIPO)"
					+ "VALUES (sq_transacao.nextval, ?, ?, ?, ?, ?, ?, ?)";

			statement = conexao.prepareStatement(sql);

			statement.setInt(1, transacao.getId_conta());

			statement.setDouble(2, transacao.getNr_valor());

			statement.setString(3, transacao.getNome_transacao());

			Date dataOcorrencia = Date.valueOf(transacao.getDt_ocorrencia());
			statement.setDate(4, dataOcorrencia);

			statement.setInt(5, transacao.getNr_periodicidade());

			int receitaOuDebito = transacao.getCd_receita_ou_debito() ? 1 : 0;
			statement.setInt(6, receitaOuDebito);

			statement.setInt(7, transacao.getCd_tipo());

			statement.executeUpdate();

			System.out.println("Transação cadastrada com sucesso!");

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
	public void atualizar(Transacao transacao) throws DBException {

		PreparedStatement statement = null;

		try {

			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "UPDATE T_TRANSACAO SET ID_CONTA = ?, NR_VALOR = ?, NOME_TRANSACAO = ?, DT_OCORRENCIA = ?, NR_PERIODICIDADE = ?, CD_RECEITA_OU_DEBITO = ?, CD_TIPO = ?"
					+ "WHERE ID_TRANSACAO = ?";

			statement = conexao.prepareStatement(sql);

			statement.setInt(1, transacao.getId_conta());

			statement.setDouble(2, transacao.getNr_valor());

			statement.setString(3, transacao.getNome_transacao());

			Date dataOcorrencia = Date.valueOf(transacao.getDt_ocorrencia());
			statement.setDate(4, dataOcorrencia);

			statement.setInt(5, transacao.getNr_periodicidade());

			int receitaOuDebito = transacao.getCd_receita_ou_debito() ? 1 : 0;
			statement.setInt(6, receitaOuDebito);

			statement.setInt(7, transacao.getCd_tipo());

			statement.setInt(8, transacao.getId_transacao());

			statement.executeUpdate();

			System.out.println("Transação atualizada com sucesso!");

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
	public void remover(int id_transacao) throws DBException {

		PreparedStatement statement = null;

		try {

			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "DELETE FROM T_TRANSACAO WHERE ID_TRANSACAO = ?";

			statement = conexao.prepareStatement(sql);

			statement.setInt(1, id_transacao);

			statement.executeUpdate();

			System.out.println("Transação removida com sucesso!");

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
	public Transacao buscar(int id_transacao) {

		Transacao transacao = null;

		PreparedStatement statement = null;

		ResultSet result = null;

		try {

			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "SELECT * FROM T_TRANSACAO WHERE ID_TRANSACAO = ?";

			statement = conexao.prepareStatement(sql);

			statement.setInt(1, id_transacao);

			result = statement.executeQuery();

			if (result.next()) {

				// ID_TRANSACAO, ID_CONTA, NR_VALOR, NOME_TRANSACAO, DT_OCORRENCIA,
				// NR_PERIODICIDADE, CD_RECEITA_OU_DEBITO, CD_TIPO

				int idTransacao = result.getInt("ID_TRANSACAO");
				int idConta = result.getInt("ID_CONTA");
				Double valor = result.getDouble("NR_VALOR");
				String nomeTransacao = result.getString("NOME_TRANSACAO");

				Date dataOcorrenciaBanco = result.getDate("DT_OCORRENCIA");
				LocalDate dataOcorrencia = dataOcorrenciaBanco.toLocalDate();

				int periodicidade = result.getInt("NR_PERIODICIDADE");

				int receitaOuDebitoBanco = result.getInt("CD_RECEITA_OU_DEBITO");
				boolean receitaOuDebito;
				if (receitaOuDebitoBanco == 1) {
					receitaOuDebito = true;
				} else {
					receitaOuDebito = false;
				}

				int tipo = result.getInt("CD_TIPO");

				transacao = new Transacao(idTransacao, idConta, valor, nomeTransacao, dataOcorrencia, periodicidade,
						receitaOuDebito, tipo);

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

		return transacao;
	}

	@Override
	public List<Transacao> listar() {
		List<Transacao> lista = new ArrayList<Transacao>();

		PreparedStatement statement = null;

		ResultSet result = null;

		try {

			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "SELECT * FROM T_TRANSACAO";

			statement = conexao.prepareStatement(sql);

			result = statement.executeQuery();

			while (result.next()) {

				Transacao transacao = new Transacao();

				transacao.setId_transacao(result.getInt("ID_TRANSACAO"));
				transacao.setId_conta(result.getInt("ID_CONTA"));
				transacao.setNr_valor(result.getDouble("NR_VALOR"));
				transacao.setNome_transacao(result.getString("NOME_TRANSACAO"));

				Date dataOcorrencia = result.getDate("DT_OCORRENCIA");
				transacao.setDt_ocorrencia(dataOcorrencia.toLocalDate());

				int receitaOuDebito = result.getInt("CD_RECEITA_OU_DEBITO");
				if (receitaOuDebito == 1) {
					transacao.setCd_receita_ou_debito(true);
				} else {
					transacao.setCd_receita_ou_debito(false);
				}

				transacao.setCd_tipo(result.getInt("CD_TIPO"));

				lista.add(transacao);
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
