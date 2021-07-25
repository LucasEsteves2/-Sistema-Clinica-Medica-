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

import com.toedter.calendar.JDateChooser;

import banco.QueryBd;
import entidades.Agenda;
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
import java.awt.Toolkit;

public class EditarAgenda extends JFrame {

	/**
	 * 
	 */
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
	private Agenda listaConsultas;
	private JDateChooser calendario;

	public EditarAgenda(Connection con, AgendaModel agendaTable, Agenda lista) {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(EditarAgenda.class.getResource("/Imagens/calendar_icon_134849.png")));

		listaConsultas = lista;
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
		lblBuscarPaciente.setEnabled(false);

		lblBuscarPaciente.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarPaciente.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblBuscarPaciente.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblBuscarPaciente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBuscarPaciente.setIcon(new ImageIcon(EditarAgenda.class.getResource("/Imagens/pesquisar01.png")));
		lblBuscarPaciente.setBounds(399, 81, 79, 31);
		contentPane.add(lblBuscarPaciente);

		JLabel lblBuscarProfisisonal = new JLabel("Buscar");
		lblBuscarProfisisonal.setEnabled(false);

		lblBuscarProfisisonal.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarProfisisonal.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblBuscarProfisisonal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBuscarProfisisonal.setIcon(new ImageIcon(EditarAgenda.class.getResource("/Imagens/pesquisar01.png")));
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
		btnSalvar.setIcon(new ImageIcon(EditarAgenda.class.getResource("/Imagens/checked.png")));
		btnSalvar.setForeground(Color.WHITE);
		btnSalvar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnSalvar.setDoubleBuffered(true);
		btnSalvar.setBorder(null);
		btnSalvar.setBackground(new Color(0, 102, 52));
		btnSalvar.setBounds(287, 461, 102, 35);
		contentPane.add(btnSalvar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(EditarAgenda.class.getResource("/Imagens/error.png")));
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnCancelar.setDoubleBuffered(true);
		btnCancelar.setBorder(null);
		btnCancelar.setBackground(new Color(255, 0, 0));
		btnCancelar.setBounds(412, 461, 102, 35);
		contentPane.add(btnCancelar);

		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setBackground(new Color(255, 255, 255));
		txtId.setEditable(false);
		txtId.setVisible(false);
		txtId.setBounds(485, 86, 24, 22);
		contentPane.add(txtId);
		txtId.setColumns(10);

		lblId = new JLabel("ID");
		lblId.setEnabled(false);
		lblId.setVisible(false);
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setHorizontalTextPosition(SwingConstants.CENTER);
		lblId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblId.setBounds(490, 69, 15, 14);
		contentPane.add(lblId);

		txtid2 = new JTextField();
		txtid2.setEnabled(false);
		txtid2.setVisible(false);
		txtid2.setBackground(new Color(255, 255, 255));
		txtid2.setEditable(false);
		txtid2.setBounds(485, 140, 24, 22);
		contentPane.add(txtid2);
		txtid2.setColumns(10);

		lblId2 = new JLabel("ID");
		lblId2.setEnabled(false);
		lblId2.setVisible(false);
		lblId2.setBounds(490, 121, 18, 16);
		contentPane.add(lblId2);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(0, 0, 532, 514);
		contentPane.add(lblNewLabel_2);
		calendario = new JDateChooser(null, "dd/MM/yyyy");
		calendario.setBounds(131, 251, 95, 22);
		contentPane.add(calendario);

		agendaInfo(cbProcedimento, cbHorario);

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

				} else {

					String Procedimento = cbProcedimento.getSelectedItem().toString();
					String horario = cbHorario.getSelectedItem().toString();

					int idConsulta = listaConsultas.getId();

					QueryBd bancoBd = new QueryBd(con);

					bancoBd.atualizarConsulta(Procedimento, data, horario, idConsulta, agendaTable, nomeMedico,
							nomePaciente);

					dispose();

				}

			}
		});

		btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
			}
		});

	}

	// metodo que vai ser chamado através da tabela

	public void agendaInfo(JComboBox cbProcedimento, JComboBox cbHorario) {

		((JTextField) calendario.getDateEditor().getUiComponent()).setText(listaConsultas.getData()); // troca o campo
																										// da data

		this.nomePaciente = listaConsultas.getPacienteNome();
		this.nomeMedico = listaConsultas.getMedicoNome();
		cbProcedimento.setSelectedItem(listaConsultas.getProcedimento());
		txtPaciente.setText(listaConsultas.getPacienteNome());
		txtProfissional.setText(listaConsultas.getMedicoNome());
		cbHorario.setSelectedItem(listaConsultas.getHorario());

	}

}
