package dominio;

public class Favoritos {
	private boolean favorito;
	private Cliente cli;
	private Atracao at;
	
	
	public Favoritos() {
		
	}
	

	public Favoritos(boolean favorito) {
		super();
		this.favorito = favorito;
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


	public boolean getFavorito() {
		return favorito;
	}

	public void setFavorito(boolean favorito) {
		this.favorito = favorito;
	}
	
	
}
