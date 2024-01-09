package br.com.fiap.fintech.singleton;

import java.sql.Connection;
import java.sql.DriverManager;

import br.com.fiap.fintech.singleton.ConnectionManager;

public class ConnectionManager {
	private static ConnectionManager connectionManager;
	private static final String URL = "";
	private static final String USUARIO = "";
	private static final String SENHA = "";

	private ConnectionManager() {}

	public static ConnectionManager getInstance() {
		if (connectionManager == null) {
			connectionManager = new ConnectionManager();
		}
		
		return connectionManager;
	}

	public Connection getConnection() {
		Connection connection = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(URL, USUARIO, SENHA);
			System.out.println("Conectado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}
}
