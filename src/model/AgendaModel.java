package model;

import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import banco.QueryBd;
import entidades.Agenda;
import entidades.Paciente;

//Estava aprendendo a criar jtable e vi uns tutoriais recomendando criar o proprio model

public class AgendaModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Agenda> dados = new ArrayList<>();
	private String[] colunas = { "ID", "Horario", "Paciente", "Profissional", "Atendimento", "Data" };

	public ArrayList<Agenda> getDados() {
		return dados;
	}

	public void setDados(ArrayList<Agenda> dados) {
		this.dados = dados;
	}

	@Override
	public String getColumnName(int column) {
		return colunas[column];

	}

	// Metodo retorna quantidade de linhas
	@Override
	public int getRowCount() {

		return dados.size();

	}

	// Metodo retorna quantidade de colunas
	@Override
	public int getColumnCount() {

		return colunas.length;
	}

	@Override
	public Object getValueAt(int linha, int coluna) {

		switch (coluna) {
		case 0:
			return dados.get(linha).getId(); // 1 Coluna

		case 1:
			return dados.get(linha).getHorario(); // 2 linha

		case 2:
			return dados.get(linha).getPacienteNome(); //

		case 3:
			return dados.get(linha).getMedicoNome();
		case 4:
			return dados.get(linha).getProcedimento();

		case 5:
			return dados.get(linha).getData();

		}

		return null;

	}

	public void adicionarLinha(Agenda nAgenda) {

		this.dados.add(nAgenda);
		this.fireTableDataChanged(); // metodo que atualiza a tabela
	}

	public void removeLinha(int linha, String id, Connection con, JTable jTabelaPaciente) {

		QueryBd banco = new QueryBd(con);

		banco.deletarConsulta(id, jTabelaPaciente);

		this.dados.remove(linha);
		this.fireTableRowsDeleted(linha, linha);

		JOptionPane.showMessageDialog(null, "Paciente removido do sistema!!", "Paciente Excluido",
				JOptionPane.INFORMATION_MESSAGE);

	}

	@Override
	public void setValueAt(Object valor, int linha, int coluna) {

		switch (coluna) {
		case 0:
			dados.get(linha).setId(Integer.parseInt((String) valor)); // 1 Coluna

		case 1:
			dados.get(linha).setHorario((String) valor); // 2 linha
			break;
		case 2:
			dados.get(linha).setPacienteNome((String) valor); //
			break;
		case 3:
			dados.get(linha).setMedicoNome((String) valor);
			break;
		case 4:
			dados.get(linha).setProcedimento((String) valor);
			break;

		case 5:
			dados.get(linha).setData((String) valor);

		}

		this.fireTableRowsUpdated(linha, linha);

	}

	public void limpaTabela(int linha) {

		dados.removeAll(dados);
		this.fireTableRowsDeleted(linha, linha);

	}

}
