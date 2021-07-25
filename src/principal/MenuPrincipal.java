package principal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Toolkit;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import mouse.MouseFunction;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;

public class MenuPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public MenuPrincipal(Connection con) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/Imagens/hospital.png")));
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		panel.setBounds(-6, -14, 238, 736);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblLogo = new JLabel("New label");
		lblLogo.setBounds(-55, -29, 421, 225);
		lblLogo.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Imagens/asx.png")));
		panel.add(lblLogo);

		JPanel PainelMedico = new JPanel();
		PainelMedico.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		PainelMedico.setBounds(-1, 252, 243, 56);
		PainelMedico.setBackground(Color.WHITE);
		panel.add(PainelMedico);
		PainelMedico.setLayout(null);

		JLabel imgPaciente = new JLabel("");
		imgPaciente.setHorizontalTextPosition(SwingConstants.CENTER);
		imgPaciente.setHorizontalAlignment(SwingConstants.CENTER);
		imgPaciente.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Imagens/doctor (4).png")));
		imgPaciente.setBounds(23, 12, 32, 32);
		PainelMedico.add(imgPaciente);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(79, 16, 0, 0);
		PainelMedico.add(lblNewLabel_2);
		lblNewLabel_2.setBackground(new Color(0, 255, 0));

		JLabel txtMedico = new JLabel("PROFISSIONAL");
		txtMedico.setHorizontalTextPosition(SwingConstants.CENTER);
		txtMedico.setHorizontalAlignment(SwingConstants.CENTER);
		txtMedico.setBounds(68, 16, 112, 22);
		txtMedico.setFont(new Font("Segoe UI", Font.BOLD, 16));
		PainelMedico.add(txtMedico);

		Panel PainelVoltar = new Panel();
		PainelVoltar.setBackground(new Color(248, 248, 255));
		PainelVoltar.setBounds(235, 0, 1139, 26);
		contentPane.add(PainelVoltar);
		PainelVoltar.setLayout(null);

		JLabel lblInicio = new JLabel("");

		lblInicio.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Imagens/pagina-inicial (1).png")));
		lblInicio.setBounds(10, -1, 29, 30);
		PainelVoltar.add(lblInicio);

		JLabel lblNewLabel_5 = new JLabel("Home");
		lblNewLabel_5.setForeground(UIManager.getColor("Button.foreground"));
		lblNewLabel_5.setBackground(new Color(0, 0, 255));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(37, 10, 46, 14);
		PainelVoltar.add(lblNewLabel_5);

		JPanel PainelFechar = new JPanel();
		PainelFechar.setBorder(new MatteBorder(1, 1, 2, 1, (Color) new Color(0, 0, 0)));
		PainelFechar.setLayout(null);
		PainelFechar.setBackground(Color.WHITE);
		PainelFechar.setBounds(-1, 308, 246, 56);
		panel.add(PainelFechar);

		JLabel imgPaciente_1_1_1 = new JLabel("");
		imgPaciente_1_1_1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Imagens/cancelar.png")));
		imgPaciente_1_1_1.setBounds(23, 12, 32, 32);
		PainelFechar.add(imgPaciente_1_1_1);

		JLabel lblNewLabel_2_1_1_1 = new JLabel("");
		lblNewLabel_2_1_1_1.setBackground(Color.GREEN);
		lblNewLabel_2_1_1_1.setBounds(79, 16, 0, 0);
		PainelFechar.add(lblNewLabel_2_1_1_1);

		JLabel txtMedico_1_1_1 = new JLabel("SAIR");
		txtMedico_1_1_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		txtMedico_1_1_1.setBounds(68, 16, 90, 22);
		PainelFechar.add(txtMedico_1_1_1);

		JPanel PainelConsulta = new JPanel();
		PainelConsulta.setBorder(new MatteBorder(2, 1, 1, 1, (Color) new Color(0, 0, 0)));
		PainelConsulta.setLayout(null);
		PainelConsulta.setBackground(Color.WHITE);
		PainelConsulta.setBounds(-1, 140, 246, 56);
		panel.add(PainelConsulta);

		JLabel imgPaciente_1_1 = new JLabel("");
		imgPaciente_1_1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Imagens/health-check.png")));
		imgPaciente_1_1.setBounds(23, 12, 32, 32);
		PainelConsulta.add(imgPaciente_1_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("");
		lblNewLabel_2_1_1.setBackground(Color.GREEN);
		lblNewLabel_2_1_1.setBounds(79, 16, 0, 0);
		PainelConsulta.add(lblNewLabel_2_1_1);

		JLabel txtMedico_1_1 = new JLabel("AGENDA");
		txtMedico_1_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		txtMedico_1_1.setBounds(68, 16, 115, 22);
		PainelConsulta.add(txtMedico_1_1);

		JPanel PainelPaciente = new JPanel();
		PainelPaciente.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		PainelPaciente.setLayout(null);
		PainelPaciente.setBackground(Color.WHITE);
		PainelPaciente.setBounds(-1, 196, 245, 56);
		panel.add(PainelPaciente);

		JLabel imgPaciente_1 = new JLabel("");
		imgPaciente_1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Imagens/examination.png")));
		imgPaciente_1.setBounds(23, 12, 32, 32);
		PainelPaciente.add(imgPaciente_1);

		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setBackground(Color.GREEN);
		lblNewLabel_2_1.setBounds(79, 16, 0, 0);
		PainelPaciente.add(lblNewLabel_2_1);

		JLabel txtMedico_1 = new JLabel("PACIENTE");
		txtMedico_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		txtMedico_1.setBounds(68, 16, 90, 22);
		PainelPaciente.add(txtMedico_1);

		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Imagens/backgroundvddd.jpg")));
		lblBackground.setBounds(235, 28, 1134, 711);
		contentPane.add(lblBackground);

		// ANIMAÇÃO AO PASSAR O MOUSE ( Escutador de click-mouse)

		PainelMedico.addMouseListener(new MouseFunction(PainelMedico, "medico", this, con));
		PainelConsulta.addMouseListener(new MouseFunction(PainelConsulta, "consulta", this, con));
		PainelPaciente.addMouseListener(new MouseFunction(PainelPaciente, "paciente", this, con));
		PainelFechar.addMouseListener(new MouseFunction(PainelFechar, "fechar", this, con));

		JLabel lblMenu = new JLabel("New label");
		lblMenu.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Imagens/2.jpg")));
		lblMenu.setBounds(6, 11, 232, 714);
		panel.add(lblMenu);

	}
}