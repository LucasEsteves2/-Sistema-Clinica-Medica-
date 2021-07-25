package medico;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import agenda.NovaAgenda;
import mouse.MouseFunction;
import principal.MenuPrincipal;
import tabelas.TabelaPaciente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JTable;
import banco.QueryBd;
import entidades.Paciente;
import model.MedicoModel;
import model.PacienteTableModel;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.UIManager;

public class ScreenMedico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable jTabelaPaciente;
	private JTextField txtPesquisar;
	private ArrayList<Paciente> pacientes = new ArrayList<>();
	private Connection con;
	PacienteTableModel paciente_tableModel = new PacienteTableModel();

	MedicoModel medico_modelo = new MedicoModel();

	public ScreenMedico(Connection con) {
		setTitle("Profissionais");

		this.con = con;
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ScreenMedico.class.getResource("/Imagens/doctor_icon_134842 (2).png")));
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

		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(-55, -29, 421, 225);
		lblLogo.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Imagens/asx.png")));
		panel.add(lblLogo);

		JPanel PainelMedico = new JPanel();
		PainelMedico.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		PainelMedico.setBounds(-1, 252, 243, 56);

		PainelMedico.setBackground(new Color(240, 248, 255));
		panel.add(PainelMedico);
		PainelMedico.setLayout(null);

		JLabel imgPaciente = new JLabel("");
		imgPaciente.setHorizontalTextPosition(SwingConstants.CENTER);
		imgPaciente.setHorizontalAlignment(SwingConstants.CENTER);
		imgPaciente.setIcon(new ImageIcon(ScreenMedico.class.getResource("/Imagens/doctor (4).png")));
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

		lblInicio.setIcon(new ImageIcon(ScreenMedico.class.getResource("/Imagens/pagina-inicial (1).png")));
		lblInicio.setBounds(10, -1, 29, 30);
		PainelVoltar.add(lblInicio);

		JLabel lblNewLabel_5 = new JLabel("Home");
		lblNewLabel_5.setForeground(new Color(192, 192, 192));
		lblNewLabel_5.setBackground(new Color(0, 0, 255));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(37, 10, 46, 14);
		PainelVoltar.add(lblNewLabel_5);

		JLabel lblNewLabel_4 = new JLabel(">Profissional");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setForeground(new Color(0, 0, 0));
		lblNewLabel_4.setBounds(71, 10, 74, 14);
		PainelVoltar.add(lblNewLabel_4);

		JPanel PainelFechar = new JPanel();
		PainelFechar.setBorder(new MatteBorder(1, 1, 2, 1, (Color) new Color(0, 0, 0)));
		PainelFechar.setLayout(null);
		PainelFechar.setBackground(Color.WHITE);
		PainelFechar.setBounds(-1, 308, 246, 56);
		panel.add(PainelFechar);

		JLabel imgPaciente_1_1_1 = new JLabel("");
		imgPaciente_1_1_1.setIcon(new ImageIcon(ScreenMedico.class.getResource("/Imagens/cancelar.png")));
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
		imgPaciente_1_1.setIcon(new ImageIcon(ScreenMedico.class.getResource("/Imagens/health-check.png")));
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
		imgPaciente_1.setIcon(new ImageIcon(ScreenMedico.class.getResource("/Imagens/examination.png")));
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

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(0, 55, 240, 4);
		PainelFechar.add(separator_4);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(235, 30, 1139, 692);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 96, 1070, 535);
		panel_1.add(scrollPane);

		jTabelaPaciente = new JTable();

		scrollPane.setViewportView(jTabelaPaciente);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon(ScreenMedico.class.getResource("/Imagens/trash.png")));
		btnExcluir.setBounds(255, 36, 90, 34);
		panel_1.add(btnExcluir);

		JLabel lblPesquisarr = new JLabel("Pesquisar:");
		lblPesquisarr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPesquisarr.setBounds(356, 43, 67, 17);
		panel_1.add(lblPesquisarr);

		txtPesquisar = new JTextField();

		txtPesquisar.setBounds(424, 36, 530, 34);
		panel_1.add(txtPesquisar);
		txtPesquisar.setColumns(10);

		btnExcluir.setBackground(new java.awt.Color(0, 102, 52));
		btnExcluir.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
		btnExcluir.setForeground(new java.awt.Color(255, 255, 255));
		btnExcluir.setBorder(null);
		btnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		btnExcluir.setDoubleBuffered(true);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditar.setIcon(new ImageIcon(ScreenMedico.class.getResource("/Imagens/edit.png")));
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnEditar.setDoubleBuffered(true);
		btnEditar.setBorder(null);
		btnEditar.setBackground(new Color(0, 102, 52));
		btnEditar.setBounds(144, 36, 90, 35);
		panel_1.add(btnEditar);

		JButton btnNovo = new JButton("Novo");
		btnNovo.setIcon(new ImageIcon(ScreenMedico.class.getResource("/Imagens/plus-black-symbol.png")));
		btnNovo.setForeground(Color.WHITE);
		btnNovo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnNovo.setDoubleBuffered(true);
		btnNovo.setBorder(null);
		btnNovo.setBackground(new Color(0, 102, 52));
		btnNovo.setBounds(33, 36, 90, 35);
		panel_1.add(btnNovo);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPesquisar.setIcon(new ImageIcon(ScreenMedico.class.getResource("/Imagens/search.png")));
		btnPesquisar.setForeground(Color.WHITE);
		btnPesquisar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnPesquisar.setDoubleBuffered(true);
		btnPesquisar.setBorder(null);
		btnPesquisar.setBackground(new Color(0, 102, 52));
		btnPesquisar.setBounds(963, 36, 129, 34);
		panel_1.add(btnPesquisar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setIcon(new ImageIcon(ScreenMedico.class.getResource("/Imagens/trash.png")));
		btnLimpar.setForeground(Color.WHITE);
		btnLimpar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnLimpar.setDoubleBuffered(true);
		btnLimpar.setBorder(null);
		btnLimpar.setBackground(Color.RED);
		btnLimpar.setBounds(963, 36, 129, 34);
		panel_1.add(btnLimpar);

		btnLimpar.setVisible(false);

		// Metodo responsavel por instanciar todos os passageiros no Jtable

		QueryBd banco = new QueryBd(con);

		banco.exibirAllMedicos(medico_modelo);

		// ANIMAÇÃO AO PASSAR O MOUSE ( Escutador de click-mouse)

		PainelMedico.addMouseListener(new MouseFunction(PainelMedico, "medico", this, con));
		PainelConsulta.addMouseListener(new MouseFunction(PainelConsulta, "consulta", this, con));
		PainelPaciente.addMouseListener(new MouseFunction(PainelPaciente, "paciente", this, con));
		PainelFechar.addMouseListener(new MouseFunction(PainelFechar, "fechar", this, con));

		JLabel lblImgMedico = new JLabel("");
		lblImgMedico.setIcon(new ImageIcon(ScreenMedico.class.getResource("/Imagens/sd2.png")));
		lblImgMedico.setBounds(-11, 553, 239, 172);
		panel.add(lblImgMedico);

		JLabel lblMenu = new JLabel("");
		lblMenu.setBackground(UIManager.getColor("CheckBox.background"));
		lblMenu.setIcon(new ImageIcon(ScreenMedico.class.getResource("/Imagens/2.jpg")));
		lblMenu.setBounds(-1, 11, 243, 714);
		panel.add(lblMenu);

		// Instanciando meu proprio model (jtable)

		jTabelaPaciente.setModel(medico_modelo);

		// Escutadores + funçoes

		home(lblInicio);

		excluirMedico(btnExcluir);

		adicionarMedico(btnNovo);

		editarPaciente(btnEditar, jTabelaPaciente);
		pesquisarPressedKey(btnLimpar, banco, btnPesquisar, lblPesquisarr);
		pesquisarClick(btnPesquisar, txtPesquisar, paciente_tableModel, banco);

		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(ScreenMedico.class.getResource("/Imagens/aaaaaaaaaaaaaaa.jpg")));
		lblBackground.setBounds(0, 0, 1139, 681);
		panel_1.add(lblBackground);

	}

	public void pesquisarPressedKey(JButton btnLimpar, QueryBd banco, JButton btnPesquisar, JLabel lblPesquisarr) {

		// Evento ao digita
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				System.out.println("digitei");

				if (txtPesquisar.getText().trim().length() <= 1) // vai procurar de 3 em 3 digitos
				{

					// Metodo que atualiza a jtable
					medico_modelo.limpaTabela(paciente_tableModel.getRowCount());
					banco.exibirAllMedicos(medico_modelo);

					btnPesquisar.setVisible(true);
					btnLimpar.setVisible(false);

				}

				else {

					String nome = txtPesquisar.getText();

					banco.pesquisarMedico(nome, medico_modelo);

					btnPesquisar.setVisible(false);
					btnLimpar.setVisible(true);

					limpar(btnLimpar, txtPesquisar, banco, btnPesquisar);

				}

			}

		});

	}

	// metodos

	public void pesquisarClick(JButton btnPesquisar, JTextField txtPesquisar2, PacienteTableModel paciente_tableModel2,
			QueryBd banco) {

		// PESQUISAR

		btnPesquisar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (txtPesquisar.getText().trim().equals("")) {

					// Metodo que atualiza a jtable
					paciente_tableModel.limpaTabela(paciente_tableModel.getRowCount());
					banco.exibirAlllPacientes(paciente_tableModel);

				} else {
					String nome = txtPesquisar.getText();

					banco.pesquisarPaciente(nome, paciente_tableModel);

				}

			}
		});

	}

	public void editarPaciente(JButton btnEditar, JTable jTabelaPaciente2) {

		// editar escutador
		btnEditar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				editarPaciente(jTabelaPaciente);

			}
		});

	}

	public void limpar(JButton btnlimpar, JTextField txtpesquisar, QueryBd banco, JButton btnPesquisar) {

		btnlimpar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtpesquisar.setText("");
				reseta(txtpesquisar, banco, btnlimpar, btnPesquisar);

			}
		});

	}

	public void reseta(JTextField txtPesquisar, QueryBd banco, JButton btnlimpar, JButton btnPesquisar) {

		// Metodo que atualiza a jtable
		medico_modelo.limpaTabela(paciente_tableModel.getRowCount());
		btnlimpar.setVisible(false);
		btnPesquisar.setVisible(true);
		banco.exibirAllMedicos(medico_modelo);

	}

	public void editarPaciente(JTable jTabelaPaciente) {

		if (jTabelaPaciente.getSelectedRow() != -1) {
			int linha = jTabelaPaciente.getSelectedRow();

			String id = jTabelaPaciente.getValueAt(linha, 0).toString();
			String nome = jTabelaPaciente.getValueAt(linha, 1).toString();
			String cpf = jTabelaPaciente.getValueAt(linha, 2).toString();
			String especialidade = jTabelaPaciente.getValueAt(linha, 3).toString();
			String sexo = jTabelaPaciente.getValueAt(linha, 4).toString();

			EditarMedico novoMedico = new EditarMedico(medico_modelo, con, nome, cpf, especialidade, sexo, id);
			novoMedico.setLocationRelativeTo(null);
			novoMedico.setVisible(true);

		} else {
			JOptionPane.showMessageDialog(null, "Por Favor, Selecione um Funcionario", "Funcionario Invalido",
					JOptionPane.ERROR_MESSAGE);

		}

	}

	public void adicionarMedico(JButton btnNovo)

	{
		btnNovo.addActionListener(new ActionListener() // Escutador
		{
			@Override
			public void actionPerformed(ActionEvent e) {

				NovoMedico novoMedico = new NovoMedico(medico_modelo, con);
				novoMedico.setLocationRelativeTo(null);
				novoMedico.setVisible(true);

			}
		});

	}

	public void excluirMedico(JButton btnExcluir) {

		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// metodo que verifica se a linha esta selecionada(se nao estiver ela valera -1)

				if (jTabelaPaciente.getSelectedRow() != -1) {

					int i = JOptionPane.showConfirmDialog(null, "Deseja Excluir o profissional selecionado?",
							"Passageiros", JOptionPane.OK_CANCEL_OPTION);

					if (i == JOptionPane.YES_OPTION) {
						System.out.println("Clicou em Sim");

						String cpf = jTabelaPaciente.getValueAt(jTabelaPaciente.getSelectedRow(), 2).toString();

						medico_modelo.removeLinha(jTabelaPaciente.getSelectedRow(), cpf, con, jTabelaPaciente); // metodo
																												// que
																												// exclui
																												// a
																												// linha

					}

					else if (i == JOptionPane.CANCEL_OPTION) {

						System.out.println("Clicou em Não");

					}

				}

				// se nao tiver nehum paciente selecioando
				else {

					JOptionPane.showMessageDialog(null, "Por Favor, Selecione um Funcionario", "Medico Invalido",
							JOptionPane.ERROR_MESSAGE);

				}

			}
		});

	}

	public void home(JLabel lblInicio)

	{

		lblInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				MenuPrincipal home = new MenuPrincipal(con);
				home.setLocationRelativeTo(null);
				home.setVisible(true);
				dispose();

			}
		});

	}

	public ArrayList<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(ArrayList<Paciente> pacientes) {
		this.pacientes = pacientes;
	}
}