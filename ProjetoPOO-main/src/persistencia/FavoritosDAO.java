package persistencia;

import java.sql.PreparedStatement;

public class FavoritosDAO {
	Conexao c;
	private String FAVORITAR = "INSERT INTO favoritos(FK_Cliente,FK_Atracao,favorito) VALUES(?,?,'true')\r\n";
	
	public FavoritosDAO() {
		c = new Conexao("jdbc:postgresql://localhost:5432/ProjetoPOO","postgres","123");
	}
	
	public void favoritar(long cpf_c, int cod_atracao) {
		try {
			c.conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(FAVORITAR);
			instrucao.setLong(1, cpf_c);
			instrucao.setInt(2, cod_atracao);
			instrucao.execute();
			c.desconectar();
		}catch(Exception e) {
			System.out.println("Erro no favoritar");
		}
	}
	
	
}
