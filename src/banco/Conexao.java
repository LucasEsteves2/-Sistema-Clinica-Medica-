package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	public Connection conectar() {

		Connection conexao = null;
		try {
			conexao = DriverManager.getConnection(
					"jdbc:postgresql://ec2-54-158-232-223.compute-1.amazonaws.com:5432/df1qc0eriao1r1",
					"wvworoqgvleegv", "54033e0d34d2a429bb3c27aa91896e3cee2da87d8645c17cdf72dda2a087340b");

			System.out.println("CONEXÃO FEITA COM SUCESSO!!");

		} catch (SQLException e) {
			System.out.println("FALHA AO SE CONECTAR COM O BANCO DE DADOS");
		}

		return conexao;
	}

}
