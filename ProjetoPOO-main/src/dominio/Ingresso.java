package dominio;

public class Ingresso {
	
	private int cod_ingresso;
	//private int atracao;
	private Compra com;
	private Atracao at;

	
	
	public Ingresso() {
		super();
	}

	public Ingresso(int cod_ingresso) {
		super();
		this.cod_ingresso = cod_ingresso;
		
		
	}
	
	
	
	public Compra getCom() {
		return com;
	}

	public void setCom(Compra com) {
		this.com = com;
	}
	

	public Atracao getAt() {
		return at;
	}

	public void setAt(Atracao at) {
		this.at = at;
	}

	public int getCod_ingresso() {
		return cod_ingresso;
	}
	public void setCod_ingresso(int cod_ingresso) {
		this.cod_ingresso = cod_ingresso;
	}
	
	
	
	
	
}
