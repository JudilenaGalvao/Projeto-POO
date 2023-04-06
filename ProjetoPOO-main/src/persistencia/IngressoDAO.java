package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dominio.Atracao;
import dominio.Cliente;
import dominio.Compra;
import dominio.Ingresso;

public class IngressoDAO {
	Conexao c;
	private String BUS = "select * from ingresso where cod_ingresso = ?";
	private String COMI = "INSERT INTO ingresso(FK_Atracao) VALUES(?)";
	
	
	public IngressoDAO() {
		c = new Conexao("jdbc:postgresql://localhost:5432/ProjetoPOO","postgres","123");
	}
	
	
	public void validarIngresso(Ingresso in, int atracao_c) {
		
		try {
			c.conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(COMI);
			instrucao.setInt(1, atracao_c);
			instrucao.execute();
			
			c.desconectar();
		}catch(Exception e) {
			System.out.println("Ingresso errado");
		}
		

		
		
	}
	
	
	public Ingresso buscarIngresso(int idAux) {
		Ingresso i = null;
		try {
			c.conectar(); // se conecta com o bd
			PreparedStatement instrucao = c.getConexao().prepareStatement(BUS); // "new" do Statement - cria uma instru��o
			instrucao.setInt(1, idAux);
			ResultSet rs = instrucao.executeQuery();
			if(rs.next()) { //andando no resultset
				i = new Ingresso(rs.getInt("cod_ingresso"));
				
			}
			c.desconectar(); //se desconecta com o bd
		}catch(Exception e) {
			System.out.println("Erro no relatorio");
		}
		return i;
	}
	
	public ArrayList<Ingresso> buscarCompra(int cod_ingresso){
		Compra compra_c;
		ArrayList<Ingresso> lista = new ArrayList<Ingresso>();
		Ingresso in = null;
		Atracao atracao = null;
		CompraDAO cDAO = new CompraDAO();
		AtracaoDAO aDAO = new AtracaoDAO();
		try {
			c.conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(BUS);
			instrucao.setInt(1, cod_ingresso);
			ResultSet rs = instrucao.executeQuery();
			while(rs.next()) {
				//compra_c = new Compra(rs.getInt("id"), rs.getString("data"), rs.getFloat("valor"), rs.getInt("cod_cadeira"));
				in = new Ingresso(rs.getInt("cod_ingresso"));
				//cliente = cDAO.buscarCliente(rs.getLong("fk_cliente"));
				compra_c = cDAO.buscarIngresso(rs.getInt("FK_Compra"));
				//cliente = new Cliente(rs.getInt("cpf"), rs.getString("nome"), rs.getInt("telefone"), rs.getString("senha"));
				in.setCom(compra_c);
				atracao = aDAO.buscarCodigo(rs.getInt("FK_Atracao"));
				//atracao = new Atracao(rs.getInt("cod_atracao"), rs.getString("nome"), rs.getString("data"), rs.getString("horario"), rs.getString("genero"));			
				in.setAt(atracao);
				lista.add(in);
			}
			c.desconectar();
		}catch(Exception e) {
			System.out.println("Erro na procura do ingresso");
		}
		return lista;
	}
}
