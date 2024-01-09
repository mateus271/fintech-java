package br.com.fiap.fintech.dao;

import java.util.List;

import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.Usuario;

public interface UsuarioDAO {
void cadastrar(Usuario usuario) throws DBException;
	
	void atualizar(Usuario usuario) throws DBException;
	
	void remover(int id_usuario) throws DBException;
	
	Usuario buscar(int id_usuario);
	
	List<Usuario> listar();
	
	boolean validarUsuario(String email, String senha);
}
