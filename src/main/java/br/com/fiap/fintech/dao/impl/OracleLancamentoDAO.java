package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.fintech.dao.LancamentoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.Lancamento;
import br.com.fiap.fintech.singleton.ConnectionManager;

public class OracleLancamentoDAO implements LancamentoDAO {
	private Connection conexao;

	@Override
	public void cadastrar(Lancamento lancamento) throws DBException {

		PreparedStatement statement = null;

		try {

			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "INSERT INTO T_LANCAMENTO (ID_LANCAMENTO, ID_FATURA, NOME_LANCAMENTO, NR_VALOR_LANCAMENTO, DS_PERIODICIDADE, CD_PAGAMENTO_OU_ESTORNO, DT_OCORRENCIA, CD_TIPO)"
					+ "VALUES (sq_lancamento.nextval, ?, ?, ?, ?, ?, ?, ?)";

			statement = conexao.prepareStatement(sql);

			statement.setInt(1, lancamento.getId_fatura());

			statement.setString(2, lancamento.getNome_lancamento());

			statement.setDouble(3, lancamento.getNr_valor_lancamento());

			statement.setInt(4, lancamento.getDs_periodicidade());

			int pagamentoOuEstorno = lancamento.isCd_pagamento_ou_estorno() ? 1 : 0;
			statement.setInt(5, pagamentoOuEstorno);

			Date dataOcorrencia = Date.valueOf(lancamento.getDt_ocorrencia());
			statement.setDate(6, dataOcorrencia);

			statement.setInt(7, lancamento.getCd_tipo());

			statement.executeUpdate();

			System.out.println("Lançamento cadastrado com sucesso!");

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
	public void atualizar(Lancamento lancamento) throws DBException {

		PreparedStatement statement = null;

		try {

			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "UPDATE T_LANCAMENTO SET ID_FATURA = ?, NOME_LANCAMENTO = ?, NR_VALOR_LANCAMENTO = ?, DS_PERIODICIDADE = ?, CD_PAGAMENTO_OU_ESTORNO = ?, DT_OCORRENCIA = ?, CD_TIPO = ?"
					+ "WHERE ID_LANCAMENTO = ?";

			statement = conexao.prepareStatement(sql);

			statement.setInt(1, lancamento.getId_fatura());

			statement.setString(2, lancamento.getNome_lancamento());

			statement.setDouble(3, lancamento.getNr_valor_lancamento());

			statement.setInt(4, lancamento.getDs_periodicidade());

			int pagamentoOuEstorno = lancamento.isCd_pagamento_ou_estorno() ? 1 : 0;
			statement.setInt(5, pagamentoOuEstorno);

			Date dataOcorrencia = Date.valueOf(lancamento.getDt_ocorrencia());
			statement.setDate(6, dataOcorrencia);

			statement.setInt(7, lancamento.getCd_tipo());

			statement.setInt(8, lancamento.getId_lancamento());

			statement.executeUpdate();

			System.out.println("Lançamento atualizado com sucesso!");

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
	public void remover(int id_lancamento) throws DBException {

		PreparedStatement statement = null;

		try {

			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "DELETE FROM T_LANCAMENTO WHERE ID_LANCAMENTO = ?";

			statement = conexao.prepareStatement(sql);

			statement.setInt(1, id_lancamento);

			statement.executeUpdate();

			System.out.println("Lançamento removido com sucesso!");

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
	public Lancamento buscar(int id_lancamento) {
		Lancamento lancamento = null;

		PreparedStatement statement = null;

		ResultSet result = null;

		try {

			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "SELECT * FROM T_LANCAMENTO WHERE ID_LANCAMENTO = ?";

			statement = conexao.prepareStatement(sql);

			statement.setInt(1, id_lancamento);

			result = statement.executeQuery();

			if (result.next()) {
				int idLancamento = result.getInt("ID_LANCAMENTO");
				int idFatura = result.getInt("ID_FATURA");
				String nomeLancamento = result.getString("NOME_LANCAMENTO");
				double valorLancamento = result.getDouble("NR_VALOR_LANCAMENTO");
				;
				int periodicidade = result.getInt("DS_PERIODICIDADE");

				int pagamentoOuEstornoBanco = result.getInt("CD_PAGAMENTO_OU_ESTORNO");
				boolean pagamentoOuEstorno;
				if (pagamentoOuEstornoBanco == 1) {
					pagamentoOuEstorno = true;
				} else {
					pagamentoOuEstorno = false;
				}

				Date dataOcorrenciaBanco = result.getDate("DT_OCORRENCIA");
				LocalDate dataOcorrencia = dataOcorrenciaBanco.toLocalDate();

				int cdTipo = result.getInt("CD_TIPO");

				lancamento = new Lancamento(idLancamento, idFatura, nomeLancamento, valorLancamento, periodicidade,
						pagamentoOuEstorno, dataOcorrencia, cdTipo);

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

		return lancamento;
	}

	@Override
	public List<Lancamento> listar() {

		List<Lancamento> lista = new ArrayList<Lancamento>();

		PreparedStatement statement = null;

		ResultSet result = null;

		try {

			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "SELECT * FROM T_LANCAMENTO";

			statement = conexao.prepareStatement(sql);

			result = statement.executeQuery();

			while (result.next()) {
				Lancamento lancamento = new Lancamento();

				lancamento.setId_lancamento(result.getInt("ID_LANCAMENTO"));
				lancamento.setId_fatura(result.getInt("ID_FATURA"));
				lancamento.setNome_lancamento(result.getString("NOME_LANCAMENTO"));
				lancamento.setNr_valor_lancamento(result.getDouble("NR_VALOR_LANCAMENTO"));
				lancamento.setDs_periodicidade(result.getInt("DS_PERIODICIDADE"));

				int pagamentoOuEstorno = result.getInt("CD_PAGAMENTO_OU_ESTORNO");
				if (pagamentoOuEstorno == 1) {
					lancamento.setCd_pagamento_ou_estorno(true);
				} else {
					lancamento.setCd_pagamento_ou_estorno(false);
				}

				Date dataOcorrencia = result.getDate("DT_OCORRENCIA");
				lancamento.setDt_ocorrencia(dataOcorrencia.toLocalDate());

				lancamento.setCd_tipo(result.getInt("CD_TIPO"));

				lista.add(lancamento);
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
