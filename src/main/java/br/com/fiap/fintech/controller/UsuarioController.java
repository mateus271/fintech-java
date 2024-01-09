package br.com.fiap.fintech.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.dao.impl.OracleUsuarioDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;
import br.com.fiap.fintech.model.Usuario;

@WebServlet("/UsuarioController")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO dao;
	
	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOFactory.getUsuarioDAO();
	}
	
    public UsuarioController() {
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
		OracleUsuarioDAO usuarioDAO = new OracleUsuarioDAO();
		
		List<Usuario> listaUsuarios = usuarioDAO.listar();

		request.setAttribute("listaUsuarios", listaUsuarios);
		request.getRequestDispatcher("listar-usuarios.jsp").forward(request, response);
	}

	
	private void iniciarEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUsuarioParam = request.getParameter("idUsuario");
		int id = Integer.parseInt(idUsuarioParam);
		Usuario usuario = dao.buscar(id);
		request.setAttribute("usuario", usuario);
		request.getRequestDispatcher("editar-usuario.jsp").forward(request, response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			try {
				int idUsuario = Integer.parseInt(request.getParameter("id_usuario"));
				String nome = request.getParameter("nome");
				String email = request.getParameter("email");
				String senha = request.getParameter("senha");
				
				Usuario usuario = new Usuario(idUsuario, nome, email, senha);
				dao.atualizar(usuario);
				request.setAttribute("msg", "Usuário atualizado! Você será redirecionado à lista de usuários");
			} catch (Exception exception) {
				exception.printStackTrace();
				request.setAttribute("erro", "Por favor, valide os dados");
			}
			
			listar(request, response);
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	
		try {
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			
			Usuario usuario = new Usuario(0, nome, email, senha);
			dao.cadastrar(usuario);
			request.setAttribute("msg", "Usuário cadastrado!");
		} catch (Exception exception) {
			exception.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		
		request.getRequestDispatcher("cadastrar-usuario.jsp").forward(request, response);

	}
	
	private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		
		try {
			dao.remover(idUsuario);
			request.setAttribute("msg", "Usuário removido!");
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		}
		
		listar(request,response);
	}

}
