package br.com.fiap.fintech.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.fintech.dao.LancamentoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;
import br.com.fiap.fintech.model.Lancamento;

@WebServlet("/LancamentoController")
public class LancamentoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LancamentoDAO dao;
	
	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOFactory.getLancamentoDAO();
	}
    
    public LancamentoController() {
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
	
	private void iniciarEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idLancamentoParam = request.getParameter("idLancamento");
		int id = Integer.parseInt(idLancamentoParam);
		Lancamento lancamento = dao.buscar(id);
		request.setAttribute("lancamento", lancamento);
		request.getRequestDispatcher("editar-lancamento.jsp").forward(request, response);
	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Lancamento> listaLancamentos = dao.listar();

		request.setAttribute("listaLancamentos", listaLancamentos);
		request.getRequestDispatcher("listar-lancamentos.jsp").forward(request, response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int idLancamento = Integer.parseInt(request.getParameter("id_lancamento"));
			int idFatura = Integer.parseInt(request.getParameter("id_fatura"));
			String nomeLancamento = request.getParameter("nome_lancamento");
			Double valorLancamento = Double.parseDouble(request.getParameter("valor_lancamento"));
			int periodicidade = Integer.parseInt(request.getParameter("periodicidade"));
			String pagamentoOuEstornoStr = request.getParameter("pagamento_ou_estorno");
			boolean pagamentoOuEstorno = "true".equals(pagamentoOuEstornoStr);
			LocalDate dataOcorrencia = LocalDate.parse(request.getParameter("data_ocorrencia"));
			int codigoTipo = Integer.parseInt(request.getParameter("cd_tipo"));
			
			Lancamento lancamento = new Lancamento(idLancamento, idFatura, nomeLancamento, valorLancamento, periodicidade, pagamentoOuEstorno, dataOcorrencia, codigoTipo);
			dao.atualizar(lancamento);
			request.setAttribute("msg", "Lançamento atualizado! Você será redirecionado à lista de lançamentos");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		}catch (Exception exception) {
			exception.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		
		listar(request, response);
	}
	
	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int idFatura = Integer.parseInt(request.getParameter("id_fatura"));
			String nomeLancamento = request.getParameter("nome_lancamento");
			Double valorLancamento = Double.parseDouble(request.getParameter("valor_lancamento"));
			int periodicidade = Integer.parseInt(request.getParameter("periodicidade"));
			String pagamentoOuEstornoStr = request.getParameter("pagamento_ou_estorno");
			boolean pagamentoOuEstorno = "true".equals(pagamentoOuEstornoStr);
			LocalDate dataOcorrencia = LocalDate.parse(request.getParameter("data_ocorrencia"));
			int codigoTipo = Integer.parseInt(request.getParameter("cd_tipo"));
			
			Lancamento lancamento = new Lancamento(0, idFatura, nomeLancamento, valorLancamento, periodicidade, pagamentoOuEstorno, dataOcorrencia, codigoTipo);
			dao.cadastrar(lancamento);
			request.setAttribute("msg", "Lançamento cadastrado!");
		} catch (Exception exception) {
			exception.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
	
		request.getRequestDispatcher("cadastrar-lancamento.jsp").forward(request, response);
	}
	
	private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idLancamento = Integer.parseInt(request.getParameter("idLancamento"));
		
		try {
			dao.remover(idLancamento);
			request.setAttribute("msg", "Lançamento removido!");
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		}
		
		listar(request,response);
	}

}
