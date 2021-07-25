package model;

import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import banco.QueryBd;
import entidades.Paciente;

//Estava aprendendo a criar jtable e vi uns tutoriais recomendando criar o proprio model

public class MedicoModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Paciente> dados = new ArrayList<>();
	private String[] colunas = { "ID", "Nome", "CPF", "    Especialidade", "      Sexo" };

	public ArrayList<Paciente> getDados() {
		return dados;
	}

	public void setDados(ArrayList<Paciente> dados) {
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
			return dados.get(linha).getNome(); // 2 linha

		case 2:
			return dados.get(linha).getCpf(); //

		case 3:
			return dados.get(linha).getEndereco();
		case 4:
			return dados.get(linha).getEmail();

		}

		return null;

	}

	public void adicionarLinha(Paciente novoPaciente) {

		this.dados.add(novoPaciente);
		this.fireTableDataChanged(); // metodo que atualiza a tabela
	}

	public void removeLinha(int linha, String cpf, Connection con, JTable jTabelaPaciente) {

		QueryBd banco = new QueryBd(con);

		banco.deletarMedico(cpf, jTabelaPaciente);

		this.dados.remove(linha);
		this.fireTableRowsDeleted(linha, linha);

		JOptionPane.showMessageDialog(null, "Medico removido do sistema!!", "Medico Excluido",
				JOptionPane.INFORMATION_MESSAGE);

	}

	@Override
	public void setValueAt(Object valor, int linha, int coluna) {

		switch (coluna) {
		case 0:
			dados.get(linha).setId(Integer.parseInt((String) valor)); // 1 Coluna

		case 1:
			dados.get(linha).setNome((String) valor); // 2 linha
			break;
		case 2:
			dados.get(linha).setCpf((String) valor); //
			break;
		case 3:
			dados.get(linha).setEndereco((String) valor);
			break;
		case 4:
			dados.get(linha).setEmail((String) valor);
			break;
		}

		this.fireTableRowsUpdated(linha, linha);

	}

	public void limpaTabela(int linha) {

		dados.removeAll(dados);
		this.fireTableRowsDeleted(linha, linha);

	}

}
