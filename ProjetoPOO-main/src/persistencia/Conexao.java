package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	
	private String usuario;
	private String senha;
	private String caminho;
	private Connection minhaConexao;
	

	public Conexao(String caminho, String usuario, String senha) {
		super();
		this.caminho = caminho;
		this.usuario = usuario;
		this.senha = senha;
		
		
	}
	
	public void conectar() {
		try {
			Class.forName("org.postgresql.Driver");
			minhaConexao = DriverManager.getConnection(caminho, usuario, senha);
		}catch(Exception e) {
			System.out.println("Deu erro na conexao");
		}
		
	}
	
	public void desconectar() {
		try {
			minhaConexao.close();
		}catch(Exception e) {
			System.out.println("Deu erro para fechar");
		}
}
	
	public Connection getConexao() {
		
		return minhaConexao;
	}
}