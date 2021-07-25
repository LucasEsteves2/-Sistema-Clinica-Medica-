package entidades;

public class Agenda {

	private String pacienteNome;

	private String medicoNome;

	private String data;
	private String horario;
	private int id;
	private String procedimento;

	public Agenda(String pacienteNome, String medicoNome, String data, String horario, int id, String procedimento) {
		super();
		this.pacienteNome = pacienteNome;
		this.medicoNome = medicoNome;
		this.data = data;
		this.horario = horario;
		this.id = id;
		this.procedimento = procedimento;
	}

	public String getPacienteNome() {
		return pacienteNome;
	}

	public void setPacienteNome(String pacienteNome) {
		this.pacienteNome = pacienteNome;
	}

	public String getMedicoNome() {
		return medicoNome;
	}

	public void setMedicoNome(String medicoNome) {
		this.medicoNome = medicoNome;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(String procedimento) {
		this.procedimento = procedimento;
	}
}
