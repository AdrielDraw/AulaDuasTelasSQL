package model;

public class Funcionario {
	
	private String id;
	private String nome;
	private String cargo;
	private String cpf;
	private String senha;
	private String nivel;
	
	public Funcionario(String nome, String cargo, String cpf, String senha, String nivel) {
		super();
		this.nome = nome;
		this.cargo = cargo;
		this.cpf = cpf;
		this.senha = senha;
		this.nivel = nivel;
	}
	
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}


	public Funcionario() {
		super();
	}


	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	
	
	
	
	
	
}
