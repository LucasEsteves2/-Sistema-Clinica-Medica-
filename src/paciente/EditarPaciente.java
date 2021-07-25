package paciente;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import banco.QueryBd;
import br.com.caelum.stella.validation.CPFValidator;
import entidades.Paciente;
import model.PacienteTableModel;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import javax.swing.JTable;
import java.awt.Toolkit;

public class EditarPaciente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtEmail;
	private JTextField txtEndereco;
	private String nome;
	private String cpf;
	private String endereco;
	private String email;
	private Connection con;
	private String id;

	public EditarPaciente(PacienteTableModel paciente_tableModel, Connection con, String nome, String cpf,
			String endereco, String email, String id) {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(EditarPaciente.class.getResource("/Imagens/mask_icon_134856 (2).png")));
		setTitle("Editar Paciente");

		this.id = id;
		this.con = con;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.email = email;
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 553, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel CadastroCliente = new JLabel("Editar Paciente");
		CadastroCliente.setHorizontalAlignment(SwingConstants.CENTER);
		CadastroCliente.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		CadastroCliente.setBounds(10, 11, 520, 31);
		contentPane.add(CadastroCliente);

		JLabel lblFoto = new JLabel("");
		lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoto.setIcon(new ImageIcon(EditarPaciente.class.getResource("/Imagens/12s.png")));
		lblFoto.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblFoto.setBounds(23, 67, 95, 114);
		contentPane.add(lblFoto);

		txtNome = new JTextField();
		txtNome.setBounds(134, 89, 169, 27);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		txtCpf = new JTextField();
		txtCpf.setColumns(10);
		txtCpf.setBounds(134, 141, 169, 27);
		contentPane.add(txtCpf);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(328, 89, 182, 27);
		contentPane.add(txtEmail);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNome.setBounds(135, 68, 46, 14);
		contentPane.add(lblNome);

		JLabel lblCpf = new JLabel("E-mail");
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCpf.setBounds(393, 69, 52, 15);
		contentPane.add(lblCpf);

		JLabel lblEmail = new JLabel("CPF");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(135, 124, 52, 14);
		contentPane.add(lblEmail);

		JPanel jpSexo = new JPanel();
		jpSexo.setFont(new Font("Tahoma", Font.BOLD, 11));
		jpSexo.setBorder(new TitledBorder(null, "Sexo", TitledBorder.LEADING, TitledBorder.TOP,
				new java.awt.Font("Arial", 1, 11)));
		jpSexo.setBounds(328, 122, 188, 46);
		contentPane.add(jpSexo);
		jpSexo.setLayout(null);

		JRadioButton rdFeminino = new JRadioButton("Feminino");

		rdFeminino.setBounds(101, 16, 82, 23);
		jpSexo.add(rdFeminino);

		JRadioButton rdMasculino = new JRadioButton("Masculino");
		rdMasculino.setBounds(12, 16, 88, 23);
		jpSexo.add(rdMasculino);

		JPanel jpEmail = new JPanel();
		jpEmail.setBorder(new TitledBorder(null, "Endereço", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		jpEmail.setBounds(27, 197, 483, 89);
		contentPane.add(jpEmail);
		jpEmail.setLayout(null);

		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(10, 34, 463, 29);
		jpEmail.add(txtEndereco);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(EditarPaciente.class.getResource("/Imagens/cancelar (2).png")));
		btnCancelar.setBounds(139, 305, 115, 38);
		contentPane.add(btnCancelar);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(EditarPaciente.class.getResource("/Imagens/salve-.png")));
		btnSalvar.setBounds(277, 305, 115, 38);
		contentPane.add(btnSalvar);

		fechar(btnCancelar);

		controleRdSexo(rdMasculino, rdFeminino);

		dadosPaciente(txtNome, txtEmail, txtCpf, txtEndereco);

		salvarPaciente(btnSalvar, rdFeminino, rdMasculino, paciente_tableModel, id);

	}

	private void trocaCor() {
		String cpff = txtCpf.getText();
		boolean valido = valida(cpff);

		if (valido) {
			txtCpf.setBorder(new LineBorder(Color.green));

		}

	}

	// metodos

	public void salvarPaciente(JButton btnSalvar, JRadioButton rdFeminino, JRadioButton rdMasculino,
			PacienteTableModel paciente_tableModel, String id) {

		// btn salvar
		btnSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String cpff = txtCpf.getText();
				boolean valido = valida(cpff);
				System.out.println(valido);

				if (txtNome.getText() == null || txtNome.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "por favor, preencha corretamente o campo 'NOME'");

				}

				else if (txtCpf.getText() == null || txtCpf.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "por favor, preencha corretamente o campo 'CPF'");

				}

				else if (txtEmail.getText() == null || txtEmail.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "por favor, preencha corretamente o campo 'E-MAIL'");

				}

				else if (rdMasculino.isSelected() && rdFeminino.isSelected()) {

					JOptionPane.showMessageDialog(null, "por favor, preencha corretamente o campo SEXO'");

				}

				else if (!rdFeminino.isSelected() && !rdMasculino.isSelected()) {
					JOptionPane.showMessageDialog(null, "por favor, preencha corretamente o campo 'SEXO'");

				}

				else if (!valido)

				{
					txtCpf.setBorder(new LineBorder(Color.RED));

					JOptionPane.showMessageDialog(null, "CPF INVÁLIDO!!", "CPF NAO EXISTE!", JOptionPane.ERROR_MESSAGE);

				} else {

					txtCpf.setBorder(new LineBorder(Color.green));

					String nome = txtNome.getText();
					String cpf = txtCpf.getText();
					String email = txtEmail.getText();

					String endereco = txtEndereco.getText();

					int idCOnvertido = Integer.parseInt(id); // o metodo tostring retorna uma string entao tenho que
																// covjnerter

					QueryBd banco = new QueryBd(con);

					banco.atualizarPaciente(nome, cpf, email, endereco, idCOnvertido, paciente_tableModel, banco);

					dispose();

				}

			}
		});

	}

	public void dadosPaciente(JTextField txtNome2, JTextField txtEmail2, JTextField txtCpf2, JTextField txtEndereco2) {

		txtNome.setText(nome);
		txtEmail.setText(email);
		txtCpf.setText(cpf);
		txtEmail.setText(email);
		txtEndereco.setText(endereco);

	}

	public void controleRdSexo(JRadioButton rdMasculino, JRadioButton rdFeminino) {

		// Escutador rd
		rdFeminino.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				rdMasculino.setSelected(false);

			}
		});

		rdMasculino.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				rdFeminino.setSelected(false);

			}
		});

	}

	public void fechar(JButton btnCancelar) {
		btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

	}

	public boolean valida(String cpf) {
		CPFValidator cpfValidator = new CPFValidator();
		System.out.println("entrei");
		try {
			cpfValidator.assertValid(cpf);
			return true;
		} catch (Exception e) {
			System.out.println("CPF INVÁLIDO!!");
			return false;
		}
	}

}
