package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dominio.Atracao;
import dominio.Cliente;

public class AtracaoDAO {
	Conexao c;
	private String BUSATRACAO = "SELECT * FROM atracao\r\n";
	private String BUSCARCODIGO = "SELECT * FROM atracao where cod_atracao = ?\r\n";
	
	public AtracaoDAO() {
		c = new Conexao("jdbc:postgresql://localhost:5432/ProjetoPOO","postgres","123");
	}
	
	public Atracao buscarCodigo(int cod_atracao) {
		Atracao at = null;
		try {
			c.conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(BUSCARCODIGO);
			instrucao.setInt(1, cod_atracao);
			ResultSet rs = instrucao.executeQuery();
			if(rs.next()) {
				at = new Atracao(rs.getInt("cod_atracao"), rs.getString("nome"), rs.getString("data"), rs.getString("horario"), rs.getString("genero"));			}
		}catch(Exception e) {
			System.out.println("Erro Banco Login");
		}
		return at;
	}
	
	public ArrayList<Atracao> buscarAtracao(){
		Atracao atracao;
		ArrayList<Atracao> lista = new ArrayList<Atracao>();
		try {
			c.conectar(); // Conecta com o banco de dados
			Statement instrucao = c.getConexao().createStatement(); // "new" do Statement - cria uma instrucao
			ResultSet rs = instrucao.executeQuery(BUSATRACAO);
			while(rs.next()) { //andando no resultset
				atracao = new Atracao(rs.getInt("cod_atracao"), rs.getString("nome"), rs.getString("data"), rs.getString("horario"), rs.getString("genero"));
				lista.add(atracao);
			}
			c.desconectar(); //desconecta com o banco de dados
		}catch(Exception e) {
			System.out.println("Erro no relatorio");
		}
		return lista;
		
	}
	
}
