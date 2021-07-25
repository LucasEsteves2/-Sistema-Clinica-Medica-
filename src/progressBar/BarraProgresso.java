package progressBar;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import banco.QueryBd;

import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window.Type;
import java.sql.Connection;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class BarraProgresso extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JProgressBar progressBar;
	private final JLabel lblNewLabel_1 = new JLabel("Loading....");
	private int idPacientee;
	private String nomePaciente;
	private String data;
	private String horario;
	private Connection con;
	private QueryBd banco;

	public BarraProgresso(int idPacientee, String nomePaciente, String data, String horario, Connection con,
			QueryBd queryBd) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(BarraProgresso.class.getResource("/Imagens/as21.png")));

		this.idPacientee = idPacientee;
		this.nomePaciente = nomePaciente;
		this.data = data;
		this.horario = horario;
		this.con = con;
		this.banco = queryBd;

		setModal(true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 345, 141);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			progressBar = new JProgressBar();
			progressBar.setForeground(new Color(50, 200, 50));
			progressBar.setMaximum(1000);
			progressBar.setStringPainted(true);
			progressBar.setBounds(26, 43, 285, 27);
			contentPanel.add(progressBar);

			JLabel lblNewLabel = new JLabel("Enviando E-mail de confirma\u00E7\u00E3o");
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel.setBounds(68, 76, 203, 16);
			contentPanel.add(lblNewLabel);
			lblNewLabel_1.setForeground(Color.WHITE);
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblNewLabel_1.setBounds(134, 13, 120, 19);

			contentPanel.add(lblNewLabel_1);

			JLabel lblNewLabel_2 = new JLabel("New label");
			lblNewLabel_2.setIcon(new ImageIcon(BarraProgresso.class.getResource("/Imagens/s2.jpg")));
			lblNewLabel_2.setBounds(0, -1, 368, 113);
			contentPanel.add(lblNewLabel_2);
			new Temporizador().start();
			new enviaEmail().start();

		}
	}

	public class Temporizador extends Thread {

		public void run() {

			while (progressBar.getValue() < 1000) {

				try {
					sleep(30);
					progressBar.setValue(progressBar.getValue() + 10);
					System.out.println();

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			JOptionPane.showMessageDialog(null, "E-mail de confirmação enviado para o e-mail do paciente!!",
					"Paciente Validado", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}
	}

	public class enviaEmail extends Thread {

		public void run() {

			banco.enviarEmail(idPacientee, nomePaciente, data, horario);

		}
	}
}
