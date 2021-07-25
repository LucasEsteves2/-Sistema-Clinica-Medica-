package agenda;

import java.awt.Font;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import banco.QueryBd;
import model.AgendaModel;
import mouse.MouseAgenda;
import tabelas.TabelaMedico;
import tabelas.TabelaPaciente;

import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.ComponentOrientation;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JProgressBar;
import java.awt.Toolkit;
import com.toedter.calendar.JDateChooser;

public class NovaAgenda extends JFrame {

	/**
	 * 
	 */
	private JDateChooser calendario;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPaciente;
	private Connection con;
	private JTextField txtProfissional;
	private JTextField txtId;
	private JLabel lblId;
	private JTextField txtid2;
	private JLabel lblId2;
	private String idMeidco;
	private String idPaciente;
	private String nomeMedico;
	private String nomePaciente;

	public NovaAgenda(Connection con, AgendaModel agendaTable) {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(NovaAgenda.class.getResource("/Imagens/calendar_icon_134849 (3).png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 538, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel CadastroCliente = new JLabel("Agendar Nova Consulta");
		CadastroCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		CadastroCliente.setForeground(new Color(0, 0, 0));
		CadastroCliente.setHorizontalAlignment(SwingConstants.CENTER);
		CadastroCliente.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		CadastroCliente.setBounds(10, 11, 506, 31);
		contentPane.add(CadastroCliente);

		txtPaciente = new JTextField();
		txtPaciente.setEditable(false);
		txtPaciente.setText("\r\n");
		txtPaciente.setForeground(new Color(0, 0, 0));
		txtPaciente.setBackground(new Color(220, 220, 220));
		txtPaciente.setColumns(10);
		txtPaciente.setBounds(131, 81, 258, 31);
		contentPane.add(txtPaciente);

		JLabel lblNome = new JLabel("Procedimento");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNome.setBounds(35, 198, 79, 14);
		contentPane.add(lblNome);

		JLabel lblEmail = new JLabel("Paciente");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(35, 89, 52, 14);
		contentPane.add(lblEmail);

		JPanel jpEmail = new JPanel();
		jpEmail.setBorder(new TitledBorder(null, "Observação", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		jpEmail.setBounds(11, 291, 506, 163);
		contentPane.add(jpEmail);
		jpEmail.setLayout(null);

		TextArea textArea = new TextArea();
		textArea.setBounds(10, 20, 486, 133);
		jpEmail.add(textArea);

		JComboBox cbProcedimento = new JComboBox();
		cbProcedimento.setModel(new DefaultComboBoxModel(new String[] { "Vacina\u00E7\u00E3o COVID-19 ",
				"Teste COVID-19", "Fisioterapia\t", "Rotina", "Esteticista", "Dermatologista", "Exame de Sangue" }));
		cbProcedimento.setBounds(131, 190, 258, 31);
		contentPane.add(cbProcedimento);

		JLabel lblPaciente = new JLabel("Profissional");
		lblPaciente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPaciente.setBounds(35, 144, 65, 14);
		contentPane.add(lblPaciente);

		txtProfissional = new JTextField();
		txtProfissional.setEditable(false);
		txtProfissional.setBackground(new Color(220, 220, 220));
		txtProfissional.setColumns(10);
		txtProfissional.setBounds(131, 135, 258, 31);
		contentPane.add(txtProfissional);

		JLabel lblBuscarPaciente = new JLabel("Buscar");

		lblBuscarPaciente.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarPaciente.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblBuscarPaciente.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblBuscarPaciente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBuscarPaciente.setIcon(new ImageIcon(NovaAgenda.class.getResource("/Imagens/pesquisar01.png")));
		lblBuscarPaciente.setBounds(399, 81, 79, 31);
		contentPane.add(lblBuscarPaciente);

		JLabel lblBuscarProfisisonal = new JLabel("Buscar");

		lblBuscarProfisisonal.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarProfisisonal.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblBuscarProfisisonal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBuscarProfisisonal.setIcon(new ImageIcon(NovaAgenda.class.getResource("/Imagens/pesquisar01.png")));
		lblBuscarProfisisonal.setBounds(399, 135, 79, 31);
		contentPane.add(lblBuscarProfisisonal);

		JLabel lblCpf = new JLabel("DATA");
		lblCpf.setBounds(36, 254, 52, 15);
		contentPane.add(lblCpf);
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblHorario = new JLabel("Horario");
		lblHorario.setBounds(248, 255, 52, 15);
		contentPane.add(lblHorario);
		lblHorario.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblNewLabel_1 = new JLabel("*");
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setBounds(25, 90, 18, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("*");
		lblNewLabel_1_1.setForeground(Color.RED);
		lblNewLabel_1_1.setBounds(25, 144, 18, 14);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("*");
		lblNewLabel_1_2.setForeground(Color.RED);
		lblNewLabel_1_2.setBounds(25, 198, 18, 14);
		contentPane.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("*");
		lblNewLabel_1_3.setForeground(Color.RED);
		lblNewLabel_1_3.setBounds(25, 254, 18, 14);
		contentPane.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("*");
		lblNewLabel_1_4.setForeground(Color.RED);
		lblNewLabel_1_4.setBounds(238, 256, 18, 14);
		contentPane.add(lblNewLabel_1_4);

		JComboBox cbHorario = new JComboBox();
		cbHorario.setModel(new DefaultComboBoxModel(
				new String[] { "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00" }));
		cbHorario.setBounds(310, 251, 79, 22);
		contentPane.add(cbHorario);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(NovaAgenda.class.getResource("/Imagens/checked.png")));
		btnSalvar.setForeground(Color.WHITE);
		btnSalvar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnSalvar.setDoubleBuffered(true);
		btnSalvar.setBorder(null);
		btnSalvar.setBackground(new Color(0, 102, 52));
		btnSalvar.setBounds(287, 461, 102, 35);
		contentPane.add(btnSalvar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(NovaAgenda.class.getResource("/Imagens/error.png")));
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnCancelar.setDoubleBuffered(true);
		btnCancelar.setBorder(null);
		btnCancelar.setBackground(new Color(255, 0, 0));
		btnCancelar.setBounds(412, 461, 102, 35);
		contentPane.add(btnCancelar);

		txtId = new JTextField();
		txtId.setBackground(new Color(255, 255, 255));
		txtId.setEditable(false);
		txtId.setVisible(false);
		txtId.setBounds(485, 86, 24, 22);
		contentPane.add(txtId);
		txtId.setColumns(10);

		lblId = new JLabel("ID");
		lblId.setVisible(false);
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setHorizontalTextPosition(SwingConstants.CENTER);
		lblId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblId.setBounds(490, 69, 15, 14);
		contentPane.add(lblId);

		abrirPacientes(lblBuscarPaciente, con, this);

		abrirMedicos(lblBuscarProfisisonal, con, this);

		txtid2 = new JTextField();
		txtid2.setVisible(false);
		txtid2.setBackground(new Color(255, 255, 255));
		txtid2.setEditable(false);
		txtid2.setBounds(485, 140, 24, 22);
		contentPane.add(txtid2);
		txtid2.setColumns(10);

		lblId2 = new JLabel("ID");
		lblId2.setVisible(false);
		lblId2.setBounds(490, 121, 18, 16);
		contentPane.add(lblId2);

		NovaAgenda menu = this;

		calendario(lblCpf);

		btnSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String data = ((JTextField) calendario.getDateEditor().getUiComponent()).getText();

				if (txtPaciente.getText().trim().equals("") || txtProfissional.getText().trim().equals("")
						|| data.equals("")) {
					if (txtPaciente.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Por favor, Selecione um PACIENTE valido");
					} else if (data.equals("")) {
						JOptionPane.showMessageDialog(null, "Por favor, Selecione uma DATA ");

					} else {
						JOptionPane.showMessageDialog(null, "Por favor, Selecione um MEDICO valido");

					}

				}

				else {

					String Procedimento = cbProcedimento.getSelectedItem().toString();

					String horario = cbHorario.getSelectedItem().toString();

					QueryBd bancoBd = new QueryBd(con);

					bancoBd.marcarConsulta(idMeidco, idPaciente, data, horario, Procedimento, nomePaciente, agendaTable,
							nomeMedico, menu, con);

				}

			}
		});

		cancelar(btnCancelar);

		calendario = new JDateChooser(null, "dd/MM/yyyy");
		calendario.setBounds(131, 251, 95, 22);
		contentPane.add(calendario);

	}

	public void cancelar(JButton btnCancelar) {
		btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
			}
		});

	}

	public void abrirMedicos(JLabel lblBuscarProfisisonal, Connection con2, NovaAgenda novaAgenda) {

		lblBuscarProfisisonal.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblBuscarProfisisonal.setBackground(null);
			}

			@Override
			public void mouseEntered(MouseEvent e) {

				lblBuscarProfisisonal.setBackground(Color.BLACK);
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				TabelaMedico tableMedico = new TabelaMedico(con2, novaAgenda);
				tableMedico.setLocationRelativeTo(null);
				tableMedico.setVisible(true);

			}
		});
	}

	public void abrirPacientes(JLabel lblNewLabel, Connection con2, NovaAgenda novaAgenda) {

		lblNewLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseEntered(MouseEvent e) {

				lblNewLabel.setBackground(Color.BLACK);

			}

			@Override
			public void mouseClicked(MouseEvent e) {

				TabelaPaciente tablePaciente = new TabelaPaciente(con2, novaAgenda);
				tablePaciente.setLocationRelativeTo(null);
				tablePaciente.setVisible(true);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel.setBackground(null);

			}
		});

	}

	// metodo que vai ser chamado através da tabela

	public void setarPaciente(String nome, String id) {
		this.idPaciente = id;
		this.nomePaciente = nome;

		txtPaciente.setText(nome);
		txtPaciente.setBackground(new Color(255, 255, 255));

		txtId.setText(id);
		txtId.setVisible(true);
		lblId.setVisible(true);
	}

	public void setarMedico(String nome, String id) {

		this.idMeidco = id;
		this.nomeMedico = nome;

		txtProfissional.setText(nome);
		txtProfissional.setBackground(new Color(255, 255, 255));

		txtid2.setText(id);
		txtid2.setVisible(true);
		lblId2.setVisible(true);

	}

	public void calendario(JLabel lblBuscarData) {

		lblBuscarData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String d1 = ((JTextField) calendario.getDateEditor().getUiComponent()).getText();
				System.out.println(d1);
			}
		});

	}
}
