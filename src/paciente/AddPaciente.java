package paciente;

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
import model.PacienteTableModel;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.awt.Toolkit;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class AddPaciente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtEmail;
	private JTextField txtRua;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtNumero;
	private Connection con;

	public AddPaciente(PacienteTableModel paciente_tableModel, Connection con) {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(AddPaciente.class.getResource("/Imagens/mask_icon_134856 (2).png")));
		setTitle("Cadastrar Paciente");

		this.con = con;
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 546, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel CadastroCliente = new JLabel("Cadastro de Pacientes");
		CadastroCliente.setHorizontalAlignment(SwingConstants.CENTER);
		CadastroCliente.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		CadastroCliente.setBounds(10, 11, 511, 31);
		contentPane.add(CadastroCliente);

		JLabel lblFoto = new JLabel("");
		lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoto.setIcon(new ImageIcon(AddPaciente.class.getResource("/Imagens/12s.png")));
		lblFoto.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblFoto.setBounds(23, 67, 95, 114);
		contentPane.add(lblFoto);

		txtNome = new JTextField();
		txtNome.setBounds(134, 89, 169, 27);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		txtCpf = new JTextField();
		txtCpf.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
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
		jpEmail.setBounds(27, 195, 483, 129);
		contentPane.add(jpEmail);
		jpEmail.setLayout(null);

		txtRua = new JTextField();
		txtRua.setColumns(10);
		txtRua.setBounds(57, 26, 157, 27);
		jpEmail.add(txtRua);

		JLabel lblEndereo = new JLabel("RUA");
		lblEndereo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEndereo.setBounds(15, 32, 29, 14);
		jpEmail.add(lblEndereo);

		JLabel lblEndereo_1 = new JLabel("BAIRRO");
		lblEndereo_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEndereo_1.setBounds(244, 32, 46, 14);
		jpEmail.add(lblEndereo_1);

		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(295, 26, 157, 27);
		jpEmail.add(txtBairro);

		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(295, 75, 157, 27);
		jpEmail.add(txtCidade);

		JLabel lblRua = new JLabel("ESTADO");
		lblRua.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRua.setBounds(244, 81, 44, 14);
		jpEmail.add(lblRua);

		JLabel lblRua_1 = new JLabel("Numero");
		lblRua_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRua_1.setBounds(10, 81, 62, 14);
		jpEmail.add(lblRua_1);

		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		txtNumero.setBounds(57, 75, 157, 27);
		jpEmail.add(txtNumero);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(AddPaciente.class.getResource("/Imagens/cancelar (2).png")));
		btnCancelar.setBounds(143, 337, 115, 38);
		contentPane.add(btnCancelar);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(AddPaciente.class.getResource("/Imagens/salve-.png")));
		btnSalvar.setBounds(291, 337, 115, 38);
		contentPane.add(btnSalvar);

		fechar(btnCancelar);

		// metodo que nao deixa 2 rd ficarem ativos ao mesmo tempo
		controleRdSexo(rdMasculino, rdFeminino);

		salvarPaciente(btnSalvar, rdFeminino, rdMasculino, paciente_tableModel, txtCpf);

	}

	// metodos
	private void salvarPaciente(JButton btnSalvar, JRadioButton rdFeminino, JRadioButton rdMasculino,
			PacienteTableModel paciente_tableModel, JTextField txtCpf) {

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

				else if (txtBairro.getText() == null || txtBairro.getText().trim().equals("")) {

					JOptionPane.showMessageDialog(null, "por favor, preencha corretamente o campo 'BAIRRO'");

				}

				else if (txtCidade.getText() == null || txtCidade.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "por favor, preencha corretamente o campo 'CIDADE'");

				} else if (txtCpf.getText() == null || txtCpf.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "por favor, preencha corretamente o campo 'CPF'");

				}

				else if (txtEmail.getText() == null || txtEmail.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "por favor, preencha corretamente o campo 'E-MAIL'");

				} else if (txtNumero.getText() == null || txtNumero.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "por favor, preencha corretamente o campo 'NUMERO'");

				} else if (txtRua.getText() == null || txtRua.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "por favor, preencha corretamente o campo 'RUA'");

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

				}

				else {

					txtCpf.setBorder(new LineBorder(Color.green));

					String nome = txtNome.getText();
					String cpf = txtCpf.getText();
					String email = txtEmail.getText();

					String endereco = "R. " + txtRua.getText() + ", " + txtBairro.getText() + " " + " "
							+ txtNumero.getText() + " - " + txtCidade.getText();

					QueryBd banco = new QueryBd(con);

					banco.addPaciente(cpf, nome, endereco, email, paciente_tableModel, con);

					dispose();

				}

			}
		});

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
