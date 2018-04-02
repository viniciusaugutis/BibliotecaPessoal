package br.com.biblioteca.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	public static Connection criarConexao() throws SQLException {

		Connection con = null;

		String URL = "jdbc:derby:biblioteca;create = true;";

		//Caso der erro e aparecer continuadamente que o banco de dados j� esta aberto
		// modificar o nome do banco de dados para bibliotecaNovo por exemplo
		//pois esta no tipo que aceita uma conexao apenas ent�o pode causar problemas de conex�o
		// para acessar mais conexoes deveria usar localhost etc.
		
		con =  DriverManager.getConnection(URL);
		

		
		return con;
	}

	// private static final String DRIVER
	// ="org.apache.derby.jdbc.EmbeddedDriver";

}
