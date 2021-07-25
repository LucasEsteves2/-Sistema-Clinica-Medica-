package mouse;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import agenda.ScreenAgendaa;
import medico.ScreenMedico;
import paciente.ScreenPaciente;
import principal.MenuPrincipal;

public class MouseAgenda extends MouseAdapter {

	private JPanel painel;

	private String tipo;
	private MenuPrincipal telaPrincipal = null; // instanciado como nulo para saber se foi instanciada
	private ScreenMedico Medicos = null;
	private ScreenPaciente menu_Paciente = null;
	public Color c;
	public Connection con;
	private ScreenAgendaa screenAgenda;
	private JLabel lblPaciente;

	public MouseAgenda(JLabel lblPaciente) {

		this.lblPaciente = lblPaciente;
	}

	// Escutador de click
	@Override
	public void mouseClicked(MouseEvent e) {
		ChamaTela_do_Menu();

	}

	// Volta a cor padrão quando o mouse sair do objeto
	@Override
	public void mouseExited(MouseEvent e) {

		painel.setBackground(c);

	}

	// Faz animação ao passar o mouse
	@Override
	public void mouseEntered(MouseEvent e) {

		// estava com problema (quando passava o mouse sumia tudo, entao reslvi
		// instanciar ak dnv)

//Vai mudar todas as cores que nao forem amarelo (no caso que nao estja 
		if (!(painel.getBackground() == Color.YELLOW)) {
			painel.setBackground(new Color(255, 250, 205));

			c = new Color(240, 248, 255);

		}

		else {

			c = Color.YELLOW;

		}

	}

	public void ChamaTela_do_Menu() {
		if (tipo.equals("medico")) {

			ScreenMedico kk = new ScreenMedico(con);
			kk.setLocationRelativeTo(null);

			kk.setExtendedState(Frame.MAXIMIZED_BOTH);

			kk.setVisible(true);

			FecharJframeATual(); // Metodo verifica quem instanciou e fecha o mesmo

		} else if (tipo.equals("paciente")) {
			System.out.println("Paciente");

			ScreenPaciente pacientes = new ScreenPaciente(con);
			pacientes.setVisible(true);
			FecharJframeATual();

		}

		else if (tipo.equals("consulta")) {
			System.out.println("Consulta");

			ScreenAgendaa pacientes = new ScreenAgendaa(con);
			pacientes.setVisible(true);
			FecharJframeATual();

		}

		else if (tipo.equals("fechar")) {

			int i = JOptionPane.showConfirmDialog(null, "Deseja Encerrar o programa?", "Finalizar",
					JOptionPane.OK_CANCEL_OPTION);

			if (i == JOptionPane.YES_OPTION) {
				System.out.println("Clicou em Sim");

				FecharJframeATual();

			} else if (i == JOptionPane.CANCEL_OPTION) {

				System.out.println("Clicou em Não");

			}
		}

	}

	// varias jframes vao ter acesso, como eu nao sei qual vai ser vou fazer um
	// retorno, caso retorne a propria tela fecha
	public void FecharJframeATual() {

		if (telaPrincipal != null) {

			telaPrincipal.dispose();
			// se ela nao for nula, significa que ela ja foi instanciada então é ela queem
			// deve ser fechada

		}

		else if (Medicos != null) {
			Medicos.dispose();

		}

		else if (menu_Paciente != null) {

			menu_Paciente.dispose();
		}

	}

}
