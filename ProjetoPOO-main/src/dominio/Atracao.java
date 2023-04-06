package dominio;

public class Atracao {
	private int cod_atracao;
	private String nome;
	private String data;
	private String horario;
	private String genero;
	
	
	public Atracao(int cod_atracao, String nome, String data, String horario, String genero) {
		super();
		this.cod_atracao = cod_atracao;
		this.nome = nome;
		this.data = data;
		this.horario = horario;
		this.genero = genero;
	}
	
	public int getCod_atracao() {
		return cod_atracao;
	}
	
	public void setCod_atracao(int cod_atracao) {
		this.cod_atracao = cod_atracao;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	
	
}
