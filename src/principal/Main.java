package principal;

import java.sql.Connection;

import banco.FabricaConexaoo;
import logiin.ScreenLogin;

public class Main {

	public static void main(String[] args) {

		// classe responsavel por sempre deixar a conex�o aberta(Heroku demora um pouco
		// ao se conectar,dessa forma n�o trava)

		FabricaConexaoo fab = new FabricaConexaoo();
		Connection con = fab.conectar(); // nao quis criar um static

		ScreenLogin Login = new ScreenLogin(con);
		Login.setLocationRelativeTo(null);
		Login.setVisible(true);

		
	}

}
