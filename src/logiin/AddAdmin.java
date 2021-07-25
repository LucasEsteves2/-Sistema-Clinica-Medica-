package logiin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import principal.MenuPrincipal;

import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddAdmin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JPasswordField txtSenha;
	private String login;
	private String senha;
	private Connection con;

	public AddAdmin(Connection con) {

		this.setCon(con);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\lucas\\Downloads\\doctor (1).png"));
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBounds(943, 231, 299, 2);
		contentPane.add(separator);

		JButton btnCancelar = new JButton("CANCELAR");

		btnCancelar.setForeground(new Color(255, 255, 255));
		btnCancelar.setBackground(new Color(255, 0, 0));
		btnCancelar.setBounds(926, 494, 339, 36);
		contentPane.add(btnCancelar);

		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setForeground(new Color(255, 255, 255));
		btnCadastrar.setBackground(new Color(46, 139, 87));
		btnCadastrar.setBounds(925, 438, 339, 36);
		contentPane.add(btnCadastrar);

		txtSenha = new JPasswordField();
		txtSenha.setToolTipText("Informe a senha");
		txtSenha.setBounds(924, 376, 341, 36);
		contentPane.add(txtSenha);

		JLabel imgCadeado = new JLabel("CADASTRAR ADMIN");
		imgCadeado.setForeground(new Color(0, 0, 0));
		imgCadeado.setBounds(944, 155, 299, 105);
		imgCadeado.setFont(new Font("Tahoma", Font.BOLD, 29));
		contentPane.add(imgCadeado);

		JLabel lblSenha = new JLabel("Sua Senha");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSenha.setBounds(926, 355, 168, 14);
		contentPane.add(lblSenha);

		JLabel lblNome = new JLabel("Seu Nome");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNome.setBounds(926, 266, 169, 14);
		contentPane.add(lblNome);

		txtNome = new JTextField();
		txtNome.setToolTipText("Informe o nome do administrador");
		txtNome.setBounds(924, 287, 341, 36);
		txtNome.setColumns(10);
		contentPane.add(txtNome);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(883, 157, 416, 390);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\lucas\\Desktop\\HOSPITAL APP\\FUNDO PRA TELA.png"));
		contentPane.add(lblNewLabel);

		JLabel ImgBackground = new JLabel("");
		ImgBackground.setBounds(0, 0, 1375, 705);
		ImgBackground.setIcon(new ImageIcon(AddAdmin.class.getResource("/Imagens/Background-tela1.jpg")));
		contentPane.add(ImgBackground);

		JLabel lblNewLabel_1 = new JLabel("LOGUIN");
		lblNewLabel_1.setBounds(480, 157, 299, 46);
		contentPane.add(lblNewLabel_1);

		setLogin(txtNome.getText());
		setSenha(String.valueOf(txtSenha.getPassword()));

		// evento botao cancelar
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// chama a tela de login
				telaLogin(con);

			}
		});

	}

	public void bemVindo(JLabel imgCadeado) {

		MenuPrincipal menu = new MenuPrincipal(con);
		imgCadeado.setIcon(new ImageIcon(AddAdmin.class.getResource("/Imagens/cadeado-desbloqueado.png")));

		JOptionPane.showMessageDialog(null, "Bem Vindo " + txtNome.getText());

		menu.setLocationRelativeTo(null);
		menu.setVisible(true);
		dispose();

	}

	public void telaLogin(Connection con)

	{
		ScreenLogin login = new ScreenLogin(con);
		login.setVisible(true);

		dispose();

	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

}
