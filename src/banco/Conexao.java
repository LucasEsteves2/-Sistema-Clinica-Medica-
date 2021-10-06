package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private String url ="jdbc:postgresql://ec2-54-158-232-223.compute-1.amazonaws.com:5432/df1qc0eriao1r1";
	private String usuario = "wvworoqgvleegv";
	private String senha="54033e0d34d2a429bb3c27aa91896e3cee2da87d8645c17cdf72dda2a087340b";
	
	
	public Connection conectar() {

		Connection conexao = null;
		try {
			conexao = DriverManager.getConnection(url,usuario,senha );

			System.out.println("CONEXÃO FEITA COM SUCESSO!!");

		} catch (SQLException e) {
			System.out.println("FALHA AO SE CONECTAR COM O BANCO DE DADOS");
		}

		return conexao;
	}
