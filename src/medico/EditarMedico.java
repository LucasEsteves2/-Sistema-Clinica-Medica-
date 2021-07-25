package medico;

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
import model.MedicoModel;
import model.PacienteTableModel;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import javax.swing.JTable;
import java.awt.Toolkit;
import javax.swing.JProgressBar;

public class EditarMedico extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtEspecialidade;
	private String nome;
	private String cpf;
	private String especialidade;
	private String sexo;
	private Connection con;
	private String id;

	public EditarMedico(MedicoModel medico_modelo, Connection con, String nome, String cpf, String endereco,
			String email, String id) {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(EditarMedico.class.getResource("/Imagens/doctor_icon_134842 (2).png")));
		setTitle("Editar Funcionario");

		this.id = id;
		this.con = con;
		this.nome = nome;
		this.cpf = cpf;
		this.especialidade = endereco;
		this.sexo = email;
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 295);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel CadastroCliente = new JLabel("Editar Profissional");
		CadastroCliente.setHorizontalAlignment(SwingConstants.CENTER);
		CadastroCliente.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		CadastroCliente.setBounds(10, 11, 512, 31);
		contentPane.add(CadastroCliente);

		JLabel lblFoto = new JLabel("");
		lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoto.setIcon(new ImageIcon(EditarMedico.class.getResource("/Imagens/as21.png")));
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

		txtEspecialidade = new JTextField();
		txtEspecialidade.setColumns(10);
		txtEspecialidade.setBounds(328, 89, 182, 27);
		contentPane.add(txtEspecialidade);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNome.setBounds(135, 68, 46, 14);
		contentPane.add(lblNome);

		JLabel lblCpf = new JLabel("Especialidade");
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCpf.setBounds(383, 69, 76, 14);
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

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(EditarMedico.class.getResource("/Imagens/cancelar (2).png")));
		btnCancelar.setBounds(134, 201, 133, 42);
		contentPane.add(btnCancelar);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(EditarMedico.class.getResource("/Imagens/salve-.png")));
		btnSalvar.setBounds(286, 201, 133, 42);
		contentPane.add(btnSalvar);

		dadosPaciente(rdMasculino, rdFeminino);

		fechar(btnCancelar);

		controleRdSexo(rdMasculino, rdFeminino);

		salvarPaciente(btnSalvar, rdFeminino, rdMasculino, medico_modelo, id, sexo);

	}

	// metodos

	public void salvarPaciente(JButton btnSalvar, JRadioButton rdFeminino, JRadioButton rdMasculino,
			MedicoModel medico_modelo, String id, String sexo2) {

		// btn salvar
		btnSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String cpff = txtCpf.getText();
				boolean valido = valida(cpff);
				System.out.println(valido);

				if (txtNome.getText() == null || txtNome.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor Preencha o campo 'NOME'");

				}

				else if (txtCpf.getText() == null || txtCpf.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor Preencha o campo 'CPF'");

				}

				else if (rdMasculino.isSelected() && rdFeminino.isSelected()) {

					JOptionPane.showMessageDialog(null, "Por favor Selecione apenas um SEXO'");

				}

				else if (!rdFeminino.isSelected() && !rdMasculino.isSelected()) {
					JOptionPane.showMessageDialog(null, "Por favor Preencha o campo 'SEXO'");

				}

				else if (!valido) // se o cpf nao for Valido

				{
					txtCpf.setBorder(new LineBorder(Color.RED));

					JOptionPane.showMessageDialog(null, "CPF INVÁLIDO!!", "CPF NAO EXISTE!", JOptionPane.ERROR_MESSAGE);

				}

				else {

					String sexo1 = sexo2;

					if (rdFeminino.isSelected()) {
						sexo1 = "F";
					}

					else {
						sexo1 = "M";
					}

					String nome11 = txtNome.getText();
					String cpf1 = txtCpf.getText();
					String especialidade = txtEspecialidade.getText();

					int idCOnvertido = Integer.parseInt(id); // o metodo tostring retorna uma string entao tenho que
																// covjnerter

					QueryBd banco = new QueryBd(con);

					banco.atualizarMedico(nome11, cpf1, especialidade, sexo1, idCOnvertido, medico_modelo, banco);

					dispose();

				}

			}
		});

	}

	public void dadosPaciente(JRadioButton rdMasculino, JRadioButton rdFeminino) {

		txtNome.setText(nome);
		txtEspecialidade.setText(sexo);
		txtCpf.setText(cpf);
		txtEspecialidade.setText(especialidade);

		if (sexo.equals("M")) {
			sexo = "M";

			rdMasculino.setSelected(true);
		}

		if (sexo.equals("F")) {
			sexo = "F";
			rdFeminino.setSelected(true);
		}

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

	public void fechar(JButton btnCancelar) {
		btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

	}
}
