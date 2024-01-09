package br.com.fiap.fintech.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.fintech.dao.ContaBancariaDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;
import br.com.fiap.fintech.model.ContaBancaria;

@WebServlet("/ContaController")
public class ContaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ContaBancariaDAO dao;
	
	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOFactory.getContaBancariaDAO();
	}
       
    public ContaController() {
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
		List<ContaBancaria> listaContas = dao.listar();
		request.setAttribute("listaContas", listaContas);
		request.getRequestDispatcher("listar-contas.jsp").forward(request, response);
	}
	
	private void iniciarEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idContaParam = request.getParameter("idConta");
		int id = Integer.parseInt(idContaParam);
		ContaBancaria conta = dao.buscar(id);
		request.setAttribute("conta", conta);
		request.getRequestDispatcher("editar-conta.jsp").forward(request, response);
	}
	
	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int idUsuario = Integer.parseInt(request.getParameter("id_usuario"));
			int cdBanco = Integer.parseInt(request.getParameter("cd_banco"));
			Double valorSaldo = Double.parseDouble(request.getParameter("valor_saldo"));
			int tipoConta = Integer.parseInt(request.getParameter("cd_tipo_conta"));
			Double limiteCredito = Double.parseDouble(request.getParameter("limite_credito"));
			
			ContaBancaria conta = new ContaBancaria(0, idUsuario, cdBanco, valorSaldo, tipoConta, limiteCredito);
			dao.cadastrar(conta);
			request.setAttribute("msg", "Conta cadastrada!");
		} catch (Exception exception) {
			exception.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		
		request.getRequestDispatcher("cadastrar-conta.jsp").forward(request, response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int idConta = Integer.parseInt(request.getParameter("id_conta"));
			int idUsuario = Integer.parseInt(request.getParameter("id_usuario"));
			int cdBanco = Integer.parseInt(request.getParameter("cd_banco"));
			Double valorSaldo = Double.parseDouble(request.getParameter("valor_saldo"));
			int tipoConta = Integer.parseInt(request.getParameter("cd_tipo_conta"));
			Double limiteCredito = Double.parseDouble(request.getParameter("limite_credito"));
			
			ContaBancaria conta = new ContaBancaria(idConta, idUsuario, cdBanco, valorSaldo, tipoConta, limiteCredito);
			dao.atualizar(conta);
			request.setAttribute("msg", "Conta atualizada! Você será redirecionado à lista de contas");
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
		int idConta = Integer.parseInt(request.getParameter("idConta"));
		
		try {
			dao.remover(idConta);
			request.setAttribute("msg", "Conta removida!");
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		}
		
		listar(request,response);
	}
}
