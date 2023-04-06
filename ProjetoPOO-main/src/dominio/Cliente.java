package dominio;

public class Cliente {
	private long cpf;
	private String nome;
	private long telefone;
	private String senha;
	
	
	public Cliente(long cpf, String nome, long telefone, String senha) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.senha = senha;
	}

	public Cliente() {
		
	}
	
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public long getCpf() {
		return cpf;
	}
	public void setCpf(long cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public long getTelefone() {
		return telefone;
	}
	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}
	   
	
	
	
	
}
