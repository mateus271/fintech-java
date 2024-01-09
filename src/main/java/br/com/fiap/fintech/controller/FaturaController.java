package br.com.fiap.fintech.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.fintech.dao.FaturaCartaoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;
import br.com.fiap.fintech.model.FaturaCartao;

@WebServlet("/FaturaController")
public class FaturaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FaturaCartaoDAO dao;
	
	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOFactory.getFaturaCartaoDAO();
	}
    
    public FaturaController() {
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
		List<FaturaCartao> listaFaturas = dao.listar();
		
		request.setAttribute("listaFaturas", listaFaturas);
		request.getRequestDispatcher("listar-faturas.jsp").forward(request, response);
	}
	
	private void iniciarEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idFaturaParam = request.getParameter("idFatura");
		int id = Integer.parseInt(idFaturaParam);
		FaturaCartao fatura = dao.buscar(id);
		request.setAttribute("fatura", fatura);
		request.getRequestDispatcher("editar-fatura.jsp").forward(request, response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int idFatura = Integer.parseInt(request.getParameter("id_fatura"));
			int idConta = Integer.parseInt(request.getParameter("id_conta"));
			Double valorFatura = Double.parseDouble(request.getParameter("valor_fatura"));
			LocalDate mesReferencia = LocalDate.parse(request.getParameter("data_fatura"));
			LocalDate mesFechamento = LocalDate.parse(request.getParameter("data_fechamento"));
			FaturaCartao fatura = new FaturaCartao(idFatura, idConta, valorFatura, mesReferencia, mesFechamento);
			dao.atualizar(fatura);
			request.setAttribute("msg", "Fatura atualizada!");
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
			int idConta = Integer.parseInt(request.getParameter("id_conta"));
			Double valorFatura = Double.parseDouble(request.getParameter("valor_fatura"));
			LocalDate mesReferencia = LocalDate.parse(request.getParameter("data_fatura"));
			LocalDate mesFechamento = LocalDate.parse(request.getParameter("data_fechamento"));
			FaturaCartao fatura = new FaturaCartao(0, idConta, valorFatura, mesReferencia, mesFechamento);
			dao.cadastrar(fatura);
			request.setAttribute("msg", "Fatura cadastrada!");
		} catch (Exception exception) {
			exception.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		
		request.getRequestDispatcher("cadastrar-fatura.jsp").forward(request, response);
	}
	
	private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idFatura = Integer.parseInt(request.getParameter("idFatura"));
		
		try {
			dao.remover(idFatura);
			request.setAttribute("msg", "Fatura removida!");
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		}
		
		listar(request,response);
	}
	
}
