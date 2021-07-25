package banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import agenda.NovaAgenda;
import email.JavaMailApp;
import entidades.Agenda;
import entidades.Paciente;
import model.AgendaModel;
import model.MedicoModel;
import model.PacienteTableModel;
import principal.MenuPrincipal;
import progressBar.BarraProgresso;
import progressBar.Progress2;

//
//Através dessa classe irei criar metodos para a conexão
public class QueryBd {
	public PreparedStatement stmt = null;
	public Conexao conectar = new Conexao();
	public Connection con = null;
	public ResultSet rs = null;
	public int id;

	private List<MenuPrincipal> passageiro = new ArrayList<MenuPrincipal>();

	public QueryBd(Connection con) {
		;
		this.con = con;
	}

	public QueryBd() {
		// TODO Auto-generated constructor stub
	}

	public List<MenuPrincipal> getPassageiro() {
		return passageiro;
	}

	public void setPassageiro(List<MenuPrincipal> passageiro) {
		this.passageiro = passageiro;
	}

//Verifica se o administrador informado é valido
	public boolean VerificarAdministrador(String login, String senha) {

		// verifica se o usuario existe

		try {

			stmt = con.prepareStatement("Select login,senha from administrador where login = ? and senha = ?");

			stmt.setString(1, login);
			stmt.setString(2, senha);
			stmt.execute();

			rs = stmt.getResultSet();

			if (rs.next()) {
				System.out.println("retornou");
				return true;

			}

			else {

				System.out.println(login + senha);
				System.out.println("nao retornou nada");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERRO: CONTATE O SUPORTE", "ERRO", JOptionPane.ERROR_MESSAGE);

			return null != null;
		}
	}

	public void addPaciente(String cpf, String nome, String endereco, String email,
			PacienteTableModel paciente_tableModel, Connection con) {

		try {
			nome = nome.toUpperCase();

			stmt = con.prepareStatement("Select cpf from paciente where cpf = ? ");

			stmt.setString(1, cpf);

			stmt.execute();

			rs = stmt.getResultSet();

			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "CPF JA CADASTRADO", "ERRO", JOptionPane.ERROR_MESSAGE);

			}

			else {

				stmt = con.prepareStatement("insert into paciente(nome,cpf,endereco,email) values(?,?,?,?) ");

				stmt.setString(1, nome);
				stmt.setString(2, cpf);
				stmt.setString(3, endereco);
				stmt.setString(4, email);

				stmt.execute();

				boolean existe = pegaId(cpf); // Pega o id do paciente (ele é serial) entao nao tenho acesso

				if (existe) {

					Progress2 barra = new Progress2(nome, email, this, con);

					barra.setLocationRelativeTo(null);
					barra.setVisible(true);

					Paciente p1 = new Paciente(id, nome, cpf, endereco, email);

					paciente_tableModel.adicionarLinha(p1);

					JOptionPane.showMessageDialog(null, "Paciente Cadastrado com sucesso!!", "Paciente Validado",
							JOptionPane.INFORMATION_MESSAGE);

				}

			}

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, "Erro ao cadastrar o paciente", "ERRO", JOptionPane.ERROR_MESSAGE);
		}

	}

	public boolean pegaId(String cpf) {
		try {
			stmt = con.prepareStatement("Select idPaciente from Paciente where cpf=?");

			stmt.setString(1, cpf);

			stmt.execute();

			rs = stmt.getResultSet();

			if (rs.next()) {
				id = rs.getInt(1);
				return true;
			}

			else {
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return false;
	}

	public void exibirAlllPacientes(PacienteTableModel paciente_tableModel)

	{

		try {
			stmt = con.prepareStatement("select nome,cpf,endereco,email,idpaciente from paciente");

			stmt.execute();

			rs = stmt.getResultSet();

			while (rs.next()) {
				String nome = rs.getString(1);
				String cpf = rs.getString(2);
				String endereco = rs.getString(3);
				String email = rs.getString(4);
				int id = rs.getInt(5);

				Paciente nPaciente = new Paciente(id, nome, cpf, endereco, email);

				paciente_tableModel.adicionarLinha(nPaciente);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deletarPaciente(String cpf, JTable jTabelaPaciente) {

		try {

			stmt = con.prepareStatement("select*from paciente where cpf = ?");
			stmt.setString(1, cpf);

			stmt.execute();

			rs = stmt.getResultSet();

			if (rs.next()) {
				int id = rs.getInt("idpaciente");

				stmt = con.prepareStatement("delete from agenda where Id_paciente =?");

				stmt.setInt(1, id);

				stmt.execute();

				stmt = con.prepareStatement("delete from paciente where idpaciente = ?");

				stmt.setInt(1, id);
				stmt.execute();

				jTabelaPaciente.getValueAt(jTabelaPaciente.getSelectedRow(), 2).toString();

			}

			else {
				stmt = con.prepareStatement("delete from paciente where cpf = ?");

				stmt.setString(1, cpf);
				stmt.execute();

				jTabelaPaciente.getValueAt(jTabelaPaciente.getSelectedRow(), 2).toString();

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void pesquisarPaciente(String nome, PacienteTableModel paciente_tableModel) {

		try {

			String nomeCaps = nome.toUpperCase();

			stmt = con.prepareStatement("select *from paciente where nome  LIKE ?");

			stmt.setString(1, "%" + nomeCaps + "%");
			stmt.execute();

			rs = stmt.getResultSet();

			// metodo que limpa o jtable
			paciente_tableModel.limpaTabela(paciente_tableModel.getRowCount());

			while (rs.next()) {

				int id = rs.getInt("IdPACIENTE");
				String nome2 = rs.getString("nome");
				String cpf = rs.getString("cpf");
				String endereco = rs.getString("endereco");
				String email = rs.getString("email");

				Paciente nPaciente = new Paciente(id, nome2, cpf, endereco, email);

				paciente_tableModel.adicionarLinha(nPaciente);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void atualizarPaciente(String nome, String cpf, String email, String endereco, int id,
			PacienteTableModel paciente_tableModel, QueryBd banco) {

		try {

			nome = nome.toUpperCase();

			// setando nome
			stmt = con.prepareStatement(" update paciente set nome= ? where idpaciente =?    ");
			stmt.setString(1, nome);
			stmt.setInt(2, id);
			stmt.executeUpdate();

			// Setando cpf

			stmt = con.prepareStatement(" update paciente set cpf= ? where idpaciente =?    ");
			stmt.setString(1, cpf);
			stmt.setInt(2, id);
			stmt.executeUpdate();

			// Setando email

			stmt = con.prepareStatement(" update paciente set email= ? where idpaciente =?    ");
			stmt.setString(1, email);
			stmt.setInt(2, id);
			stmt.executeUpdate();

			// Setando endereco

			stmt = con.prepareStatement(" update paciente set endereco= ? where idpaciente =?    ");
			stmt.setString(1, endereco);
			stmt.setInt(2, id);
			stmt.executeUpdate();

			atualizarTABELA(paciente_tableModel, banco); // metodo que vai atualizar a jframe
			JOptionPane.showMessageDialog(null, "Paciente ATUALIZADO!!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Atualizar o paciente", "ERRO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	public void atualizarTABELA(PacienteTableModel paciente_tableModel, QueryBd banco) {
		// metodo que limpa o jtable
		paciente_tableModel.limpaTabela(paciente_tableModel.getRowCount());

		banco.exibirAlllPacientes(paciente_tableModel);

	}

	public void exibirAllMedicos(MedicoModel medico_modelo) {

		try {
			stmt = con.prepareStatement("select nome,cpf,especialidade,sexo,idMedico from medico");

			stmt.execute();

			rs = stmt.getResultSet();

			while (rs.next()) {
				String nome = rs.getString(1);
				String cpf = rs.getString(2);
				String especialidade = rs.getString(3);
				String sexo = rs.getString(4);
				int id = rs.getInt(5);

				Paciente nPaciente = new Paciente(id, nome, cpf, especialidade, sexo);

				medico_modelo.adicionarLinha(nPaciente);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addMedico(String cpf, String nome, String especialidade, String sexo, MedicoModel medicomodel) {

		sexo = sexo.toUpperCase();

		try {
			nome = nome.toUpperCase();

			stmt = con.prepareStatement("Select cpf from medico where cpf = ? ");

			stmt.setString(1, cpf);

			stmt.execute();

			rs = stmt.getResultSet();

			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "CPF JA CADASTRADO", "ERRO", JOptionPane.ERROR_MESSAGE);

			}

			else {

				stmt = con.prepareStatement("insert into medico(nome,cpf,especialidade,sexo) values(?,?,?,?) ");

				stmt.setString(1, nome);
				stmt.setString(2, cpf);
				stmt.setString(3, especialidade);
				stmt.setString(4, sexo);

				stmt.execute();

				boolean existe = pegaIdMedico(cpf); // Pega o id do paciente (ele é serial) entao nao tenho acesso

				if (existe) {
					Paciente p1 = new Paciente(id, nome, cpf, especialidade, sexo);

					medicomodel.adicionarLinha(p1);

					JOptionPane.showMessageDialog(null, "Medico Cadastrado com sucesso!!", "Paciente Validado",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, "Erro ao cadastrar o paciente", "ERRO", JOptionPane.ERROR_MESSAGE);
		}

	}

	public boolean pegaIdMedico(String cpf) {
		try {
			stmt = con.prepareStatement("Select idMedico from medico where cpf=?");

			stmt.setString(1, cpf);

			stmt.execute();

			rs = stmt.getResultSet();

			if (rs.next()) {
				id = rs.getInt(1);
				return true;
			}

			else {
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	public void deletarMedico(String cpf, JTable jTabelaPaciente) {

		try {

			stmt = con.prepareStatement("select*from medico where cpf = ?");
			stmt.setString(1, cpf);

			stmt.execute();

			rs = stmt.getResultSet();

			if (rs.next()) {
				int id = rs.getInt("idmedico");

				stmt = con.prepareStatement("delete from agenda where id_Medico =?");

				stmt.setInt(1, id);

				stmt.execute();

				stmt = con.prepareStatement("delete from medico where idMedico = ?");

				stmt.setInt(1, id);
				stmt.execute();

				jTabelaPaciente.getValueAt(jTabelaPaciente.getSelectedRow(), 2).toString();

			}

			else {
				stmt = con.prepareStatement("delete from medico where cpf = ?");

				stmt.setString(1, cpf);

				stmt.execute();

				jTabelaPaciente.getValueAt(jTabelaPaciente.getSelectedRow(), 2).toString();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void atualizarMedico(String nome, String cpf, String especialidade, String sexo, int idCOnvertido,
			MedicoModel medico_modelo, QueryBd banco) {

		int id = idCOnvertido;
		sexo = sexo.toUpperCase();

		try {

			nome = nome.toUpperCase();

			// setando nome
			stmt = con.prepareStatement(" update medico set nome= ? where idmedico =?    ");
			stmt.setString(1, nome);
			stmt.setInt(2, id);
			stmt.executeUpdate();

			// Setando cpf

			stmt = con.prepareStatement(" update medico set cpf= ? where idmedico =?    ");
			stmt.setString(1, cpf);
			stmt.setInt(2, id);
			stmt.executeUpdate();

			// Setando a especialidade

			stmt = con.prepareStatement(" update medico set especialidade= ? where idmedico =?    ");
			stmt.setString(1, especialidade);
			stmt.setInt(2, id);
			stmt.executeUpdate();

			// Setando o sexo
			stmt = con.prepareStatement(" update medico set sexo= ? where idmedico =?    ");
			stmt.setString(1, sexo);
			stmt.setInt(2, id);
			stmt.executeUpdate();

			atualizarTabelaMedico(medico_modelo, banco); // metodo que vai atualizar a jframe
			JOptionPane.showMessageDialog(null, "Funcionario ATUALIZADO!!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Atualizar o paciente", "ERRO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	public void atualizarTabelaMedico(MedicoModel medico_modelo, QueryBd banco) {
		// metodo que limpa o jtable
		medico_modelo.limpaTabela(medico_modelo.getRowCount());

		banco.exibirAllMedicos(medico_modelo);

	}

	public void pesquisarMedico(String nome, MedicoModel medico_modelo) {

		try {

			String nomeCaps = nome.toUpperCase();

			stmt = con.prepareStatement("select *from medico where nome  LIKE ?");

			stmt.setString(1, "%" + nomeCaps + "%");
			stmt.execute();

			rs = stmt.getResultSet();

			// metodo que limpa o jtable
			medico_modelo.limpaTabela(medico_modelo.getRowCount());

			while (rs.next()) {

				int id = rs.getInt("idMedico");
				String nome2 = rs.getString("nome");
				String cpf = rs.getString("cpf");
				String especialidade = rs.getString("especialidade");
				String sexo = rs.getString("sexo");

				Paciente nPaciente = new Paciente(id, nome2, cpf, especialidade, sexo);

				medico_modelo.adicionarLinha(nPaciente);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void marcarConsulta(String idMedico, String idPaciente, String data, String horario, String procedimento,
			String nomePaciente, AgendaModel agendaTable, String nomeMedico, NovaAgenda menu, Connection con)

	{
		int idMedicoo = Integer.parseInt(idMedico);
		int idPacientee = Integer.parseInt(idPaciente);

		try {

			// verifica se o paciente Ja possui uma consulta marcada (mesmo dia, mesmo
			// horario)
			boolean naoExiste = verificaConsulta2(data, horario, nomeMedico);

			boolean paciente = pacienteAgendado(data, nomePaciente, horario);
			if (naoExiste && paciente) {

				stmt = con.prepareStatement(
						"insert into agenda (id_Medico,id_Paciente,data,horario,procedimento)  values(?,?,?,?,?) ");

				stmt.setInt(1, idMedicoo);
				stmt.setInt(2, idPacientee);
				stmt.setString(3, data);
				stmt.setString(4, horario);
				stmt.setString(5, procedimento);

				stmt.execute();

				rs = stmt.getResultSet();

				BarraProgresso kk = new BarraProgresso(idPacientee, nomePaciente, data, horario, con, this);
				kk.setLocationRelativeTo(null);
				kk.setVisible(true);

				agendaTable.limpaTabela(agendaTable.getRowCount());

				exibirConsultas(agendaTable);

				JOptionPane.showMessageDialog(null, "Consulta Marcada com sucesso!!", "Consulta Valida",
						JOptionPane.INFORMATION_MESSAGE);

				menu.dispose();

			} else {

				JOptionPane.showMessageDialog(null, "Por favor consulte os horarios disponiveis!!", "ERRO",
						JOptionPane.ERROR_MESSAGE);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void exibirConsultas(AgendaModel agendaModel)

	{

		try {

			String sql = "select agenda.horario,paciente.nome, medico.nome, agenda.procedimento, agenda.data, agenda.idAgenda from paciente,medico,agenda\r\n"
					+ "\r\n" + "where idpaciente=Id_paciente and idMedico = id_medico;";

			stmt = con.prepareStatement(sql);

			stmt.execute();

			rs = stmt.getResultSet();

			while (rs.next()) {
				String nome = rs.getString(2);
				String horario = rs.getString(1);
				String medico = rs.getString(3);
				String procedimento = rs.getString(4);
				String data = rs.getString(5);
				int id = rs.getInt(6);

				Agenda nAgenda = new Agenda(nome, medico, data, horario, id, procedimento);

				agendaModel.adicionarLinha(nAgenda);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deletarConsulta(String id, JTable jTabelaPaciente) {
		try {

			int idConvertido = Integer.parseInt(id);

			stmt = con.prepareStatement("delete from agenda where idAgenda = ?");

			stmt.setInt(1, idConvertido);

			stmt.execute();

			jTabelaPaciente.getValueAt(jTabelaPaciente.getSelectedRow(), 1).toString();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void pesquisarConsulta(String nome, AgendaModel agendaTable) {

		try {

			String nomeCaps = nome.toUpperCase();

			stmt = con.prepareStatement("select *from paciente,agenda,medico\r\n"
					+ "where idpaciente=id_paciente and idmedico = id_medico and horario is not null and data is not null "
					+ "and Paciente.nome LIKE ?; ");

			stmt.setString(1, "%" + nomeCaps + "%");
			stmt.execute();

			rs = stmt.getResultSet();

			// metodo que limpa o jtable
			agendaTable.limpaTabela(agendaTable.getRowCount());

			while (rs.next()) {

				String paciente = rs.getString(2);
				String horario = rs.getString("horario");
				String procedimento = rs.getString("procedimento");
				String data = rs.getString("data");
				String medico = rs.getString(13);
				int id = rs.getInt("idAgenda");

				Agenda nAgenda = new Agenda(paciente, medico, data, horario, id, procedimento);

				agendaTable.adicionarLinha(nAgenda);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void atualizarConsulta(String procedimento, String data, String horario, int idConsulta,
			AgendaModel agendaTable, String nomeMedico, String nomePaciente) {
		try {

			// metodo verfica se ja existe alguma consulta marcada para o horario/data
			// informado
			boolean Nãoexiste = verificaConsulta2(data, horario, nomeMedico);
			boolean paciente = pacienteAgendado(data, nomePaciente, horario);

			if (Nãoexiste && paciente) {

				// setando o procedimento
				stmt = con.prepareStatement(" update agenda set data= ? where idAgenda =?    ");
				stmt.setString(1, data);
				stmt.setInt(2, idConsulta);
				stmt.executeUpdate();

				// Setando o horario

				stmt = con.prepareStatement(" update agenda set horario= ? where idAgenda =?    ");

				stmt.setString(1, horario);
				stmt.setInt(2, idConsulta);
				stmt.executeUpdate();

				// Setando o atendimento

				stmt = con.prepareStatement(" update agenda set procedimento= ? where idAgenda =?    ");

				stmt.setString(1, procedimento);
				stmt.setInt(2, idConsulta);
				stmt.executeUpdate();

				JOptionPane.showMessageDialog(null, "Consulta remarcada com sucesso!!", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);

				agendaTable.limpaTabela(agendaTable.getRowCount());

				exibirConsultas(agendaTable);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean verificaConsulta2(String data, String horario, String medico) {

		try {
			stmt = con.prepareStatement("select medico.nome from paciente,medico,agenda \r\n"
					+ "where idpaciente=id_paciente and idmedico=id_medico\r\n" + "and data = ? and horario =? \r\n"
					+ "and medico.nome = ?;");
			stmt.setString(1, data);
			stmt.setString(2, horario);
			stmt.setString(3, medico);

			stmt.execute();

			rs = stmt.getResultSet();

			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "o medico selecionado ja possui uma consulta marcada para o dia "
						+ data + " Horario: " + horario, "ERRO", JOptionPane.ERROR_MESSAGE);
				return false;

			}

			else {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	public void enviarEmail(int idPacientee, String nomePaciente, String data, String horario) {

		try {
			stmt = con.prepareStatement("select email from paciente where idpaciente=?");

			stmt.setInt(1, idPacientee);

			stmt.execute();
			rs = stmt.getResultSet();

			if (rs.next()) {
				String email = rs.getString("email");

				JavaMailApp enviarEmail = new JavaMailApp();

				String mensagem = "Olá " + nomePaciente + " Sua consulta foi marcada com sucesso " + " Data: " + data
						+ " Horario: " + horario;

				enviarEmail.enviarEmail(mensagem, "Consulta Agendada", email);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean pacienteAgendado(String data, String nome, String horario) {

		try {
			stmt = con.prepareStatement("select paciente.nome from paciente,medico,agenda \r\n"
					+ "where idpaciente=id_paciente and idmedico=id_medico\r\n"
					+ "and data= ? and paciente.nome= ? and horario = ?");

			stmt.setString(1, data);
			stmt.setString(2, nome);
			stmt.setString(3, horario);
			stmt.execute();

			rs = stmt.getResultSet();

			if (rs.next()) {
				JOptionPane.showMessageDialog(null,
						"o paciente selecionado ja possui uma consulta marcada, não é possivel agendar duas consultas para o mesmo dia",
						"error", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}

			else {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void enviarEmailPaciente(String nome, String email) {

		String assunto = "Cadastro Realizado com sucesso";

		String mensagem = "Olá " + nome + " seu cadastro na clinica HospitalNator foi feito com sucesso!!";
		JavaMailApp novoEmail = new JavaMailApp();
		novoEmail.enviarEmail(mensagem, assunto, email);

	}

	public void pesquisarConsultaDatas(String dataa, AgendaModel agendaTable, JDateChooser calendario) {

		try {

			stmt = con.prepareStatement("select *from paciente,agenda,medico\r\n"
					+ "where idpaciente=id_paciente and idmedico = id_medico and horario is not null and data is not null "
					+ "and data = ? ");

			stmt.setString(1, dataa);
			stmt.execute();

			rs = stmt.getResultSet();

			// metodo que limpa o jtable
			agendaTable.limpaTabela(agendaTable.getRowCount());

			int i = 0; // se entrar no meetodo o i vai valer mais de 1

			while (rs.next()) {
				i++;
				String paciente = rs.getString(2);
				String horario = rs.getString("horario");
				String procedimento = rs.getString("procedimento");
				String data = rs.getString("data");
				String medico = rs.getString(13);
				int id = rs.getInt("idAgenda");

				Agenda nAgenda = new Agenda(paciente, medico, data, horario, id, procedimento);

				agendaTable.adicionarLinha(nAgenda);

			}

			if (i <= 0) {

				JOptionPane.showMessageDialog(null, "Não encontramos nenhuma consulta agendada para o dia " + dataa);

				((JTextField) calendario.getDateEditor().getUiComponent()).setText(""); // reseta o campo de data

				exibirConsultas(agendaTable);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
