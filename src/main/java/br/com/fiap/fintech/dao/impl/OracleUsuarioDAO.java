package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.Usuario;
import br.com.fiap.fintech.singleton.ConnectionManager;

public class OracleUsuarioDAO implements UsuarioDAO {
	private Connection conexao;

	@Override
	public void cadastrar(Usuario usuario) throws DBException {

		PreparedStatement statement = null;

		try {

			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "INSERT INTO T_USUARIO (ID_USUARIO, NOME_USUARIO, DS_EMAIL_USUARIO, DS_SENHA)"
					+ "VALUES (sq_usuario.nextval, ?, ?, ?)";

			statement = conexao.prepareStatement(sql);

			statement.setString(1, usuario.getNome_usuario());

			statement.setString(2, usuario.getDs_email_usuario());
			
			statement.setString(3, usuario.getDs_senha());

			statement.executeUpdate();

			System.out.println("Usuário cadastrado com sucesso!");

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
	public void atualizar(Usuario usuario) throws DBException {

		PreparedStatement statement = null;

		try {

			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "UPDATE T_USUARIO SET NOME_USUARIO = ?, DS_EMAIL_USUARIO = ?, DS_SENHA = ?" + "WHERE ID_USUARIO = ?";

			statement = conexao.prepareStatement(sql);

			statement.setString(1, usuario.getNome_usuario());

			statement.setString(2, usuario.getDs_email_usuario());
			
			statement.setString(3, usuario.getDs_senha());

			statement.setInt(4, usuario.getId_usuario());

			statement.executeUpdate();

			System.out.println("Usuário atualizado com sucesso!");

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
	public void remover(int id_usuario) throws DBException {

		PreparedStatement statement = null;

		try {

			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "DELETE FROM T_USUARIO WHERE ID_USUARIO = ?";

			statement = conexao.prepareStatement(sql);

			statement.setInt(1, id_usuario);

			statement.executeUpdate();

			System.out.println("Usuário removido com sucesso!");

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
	public Usuario buscar(int id_usuario) {

		Usuario usuario = null;

		PreparedStatement statement = null;

		ResultSet result = null;

		try {

			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "SELECT * FROM T_USUARIO WHERE ID_USUARIO = ?";

			statement = conexao.prepareStatement(sql);

			statement.setInt(1, id_usuario);

			result = statement.executeQuery();

			if (result.next()) {
				int idUsuario = id_usuario;
				String nomeUsuario = result.getString("nome_usuario");
				String emailUsuario = result.getString("ds_email_usuario");
				String senha = result.getString("ds_senha");

				usuario = new Usuario(idUsuario, nomeUsuario, emailUsuario, senha);
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

		return usuario;

	}

	@Override
	public List<Usuario> listar() {
		List<Usuario> lista = new ArrayList<Usuario>();

		PreparedStatement statement = null;

		ResultSet result = null;

		try {

			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "SELECT * FROM T_USUARIO";

			statement = conexao.prepareStatement(sql);

			result = statement.executeQuery();

			while (result.next()) {
				Usuario usuario = new Usuario();

				usuario.setId_usuario(result.getInt("ID_USUARIO"));
				usuario.setNome_usuario(result.getString("NOME_USUARIO"));
				usuario.setDs_email_usuario(result.getString("DS_EMAIL_USUARIO"));
				usuario.setDs_senha(result.getString("DS_SENHA"));

				lista.add(usuario);
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
	
	@Override
	public boolean validarUsuario(String email, String senha) {
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			statement = conexao.prepareStatement("SELECT * FROM T_USUARIO WHERE DS_EMAIL_USUARIO = ? AND DS_SENHA = ?");
			statement.setString(1, email);
			statement.setString(2, senha);
			result = statement.executeQuery();
			
			if (result.next()){
				return true;
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
		
		return false;
	}
	
}
