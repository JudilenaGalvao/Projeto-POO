package dominio;

public class Compra {
	private int id;
	private String data;
	private float valor;
	private int cod_cadeira;
	private Cliente cli;
	private Atracao at;
	
	
	public Compra() {
		
	}
	

	public Compra(int id, String data, float valor, int cod_cadeira) {
		super();
		this.id = id;
		this.data = data;
		this.valor = valor;
		this.cod_cadeira = cod_cadeira;
	
	}


	public Cliente getCli() {
		return cli;
	}


	public void setCli(Cliente cli) {
		this.cli = cli;
	}


	public Atracao getAt() {
		return at;
	}


	public void setAt(Atracao at) {
		this.at = at;
	}


	public int getCod_cadeira() {
		return cod_cadeira;
	}


	public void setCod_cadeira(int cod_cadeira) {
		this.cod_cadeira = cod_cadeira;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	/*
	public String getFormasPag() {
		return formasPag;
	}
	public void setFormasPag(String formasPag) {
		this.formasPag = formasPag;
	}
	*/
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	/*
	public String getTipoCompra() {
		return tipoCompra;
	}
	public void setTipoCompra(String tipoCompra) {
		this.tipoCompra = tipoCompra;
	}
	*/
	
	
}
