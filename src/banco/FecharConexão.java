package banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FecharConexão {

	public void fechar(ResultSet rs, PreparedStatement stmt) {

		try {
			rs.close();

			stmt.close();

		} catch (SQLException e) {
			System.out.println("erro ao fechar objetos");
			e.printStackTrace();
		}
	}

}
