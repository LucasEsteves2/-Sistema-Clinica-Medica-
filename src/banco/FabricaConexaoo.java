package banco;

import java.sql.Connection;

public class FabricaConexaoo {

	private static Conexao banco = new Conexao();

	public static Connection conectar() {

		Connection con = banco.conectar();
		return con;

	}

}
