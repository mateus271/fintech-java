package br.com.fiap.fintech.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.fintech.dao.TransacaoDAO;
import br.com.fiap.fintech.dao.impl.OracleTransacaoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;
import br.com.fiap.fintech.model.Transacao;

@WebServlet("/TransacaoController")
public class TransacaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TransacaoDAO dao;
	
	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOFactory.getTransacaoDAO();
	}
       
    public TransacaoController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		response.setCharacterEncoding("UTF-8");
		
		switch (acao) {
			case "listar":
				listar(request, response);
				break;
			case "iniciar-edicao":
				iniciarEdicao(request, response);
				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		response.setCharacterEncoding("UTF-8");
		
		switch(acao) {
			case "editar":
				editar(request, response);
				break;
			case "cadastrar":
				cadastrar(request, response);
				break;
			case "excluir":
				excluir(request, response);
				break;
		}
	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OracleTransacaoDAO transacaoDAO = new OracleTransacaoDAO();
		
		List<Transacao> listaTransacoes = transacaoDAO.listar();

		request.setAttribute("listaTransacoes", listaTransacoes);
		request.getRequestDispatcher("listar-transacoes.jsp").forward(request, response);
	}
	
	private void iniciarEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idTransacaoParam = request.getParameter("idTransacao");
		int id = Integer.parseInt(idTransacaoParam);
		Transacao transacao = dao.buscar(id);
		request.setAttribute("transacao", transacao);
		request.getRequestDispatcher("editar-transacao.jsp").forward(request, response);
	}
	
	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int idConta = Integer.parseInt(request.getParameter("id_conta"));
			Double valorTransacao = Double.parseDouble(request.getParameter("valor_transacao"));
			String nomeTransacao = request.getParameter("nome_transacao");
			LocalDate dataOcorrencia = LocalDate.parse(request.getParameter("data_ocorrencia"));
			int periodicidade = Integer.parseInt(request.getParameter("periodicidade"));
			String receitaOuDebitoStr = request.getParameter("receita_ou_debito");
			boolean receitaOuDebito = "true".equals(receitaOuDebitoStr);
			int codigoTipo = Integer.parseInt(request.getParameter("cd_tipo"));
			
			Transacao transacao = new Transacao(0, idConta, valorTransacao, nomeTransacao, dataOcorrencia, periodicidade, receitaOuDebito, codigoTipo);
			dao.cadastrar(transacao);
			request.setAttribute("msg", "Transação cadastrada!");
		} catch (Exception exception) {
			exception.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		
		request.getRequestDispatcher("cadastrar-transacao.jsp").forward(request, response);
		
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int idTransacao = Integer.parseInt(request.getParameter("id_transacao"));
			int idConta = Integer.parseInt(request.getParameter("id_conta"));
			Double valorTransacao = Double.parseDouble(request.getParameter("valor_transacao"));
			String nomeTransacao = request.getParameter("nome_transacao");
			LocalDate dataOcorrencia = LocalDate.parse(request.getParameter("data_ocorrencia"));
			int periodicidade = Integer.parseInt(request.getParameter("periodicidade"));
			String receitaOuDebitoStr = request.getParameter("receita_ou_debito");
			boolean receitaOuDebito = "true".equals(receitaOuDebitoStr);
			int codigoTipo = Integer.parseInt(request.getParameter("cd_tipo"));
			
			Transacao transacao = new Transacao(idTransacao, idConta, valorTransacao, nomeTransacao, dataOcorrencia, periodicidade, receitaOuDebito, codigoTipo);
			dao.atualizar(transacao);
			request.setAttribute("msg", "Transação atualizada! Você será redirecionado à lista de transações");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		}catch (Exception exception) {
			exception.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		
		listar(request, response);
	}
	
	private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idTransacao = Integer.parseInt(request.getParameter("idTransacao"));
		
		try {
			dao.remover(idTransacao);
			request.setAttribute("msg", "Transação removida!");
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		}
		
		listar(request,response);
	}

}
