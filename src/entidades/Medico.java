package entidades;

public class Medico {

	private String nome;
	private String cpf;
	private String especialidade;
	private String sexo;
	private int id;

	public Medico(int id2, String nome, String cpf, String endereco, String email) {

		this.id = id2;
		this.nome = nome;
		this.cpf = cpf;
		this.especialidade = endereco;
		this.email = email;
	}

	public String getEndereco() {
		return especialidade;
	}

	public void setEndereco(String endereco) {
		this.especialidade = endereco;
	}

	public String getDtNascimento() {
		return sexo;
	}

	public void setDtNascimento(String dtNascimento) {
		this.sexo = dtNascimento;
	}

	public String getSintomas() {
		return sintomas;
	}

	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}

	private String email;
	private String sintomas;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
