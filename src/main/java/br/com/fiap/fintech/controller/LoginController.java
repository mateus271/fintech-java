package br.com.fiap.fintech.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.factory.DAOFactory;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO dao;
       
    public LoginController() {
        super();
        dao = DAOFactory.getUsuarioDAO();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
	    String senha = request.getParameter("senha");
		
		if (dao.validarUsuario(email, senha)) {
			HttpSession session = request.getSession();
	        session.setAttribute("user", email);
	        response.sendRedirect("home.jsp");
		} else {
			request.setAttribute("erro", "Usuário e/ou senha inválido(s)");
	        request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
