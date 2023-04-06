package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dominio.Atracao;
import dominio.Cliente;
import dominio.Compra;

public class CompraDAO {
	Conexao c;
	private String REL = "select * from compra";
	private String COM = "INSERT INTO compra(data,valor,cod_cadeira,FK_Cliente, FK_Atracao) VALUES(?,80.00,?,?,?);\r\n";
	private String BUSCARINGRESSO = "select * from compra where id = ?";
	private String DELETARINGRESSO = "DELETE from compra where id = ?";
	private String BUSCARCOMPRA = "select * from compra where fk_cliente = ?";
	private String BUSCARFKATRACAO = "SELECT * FROM compra WHERE FK_Atracao = ? ORDER BY cod_cadeira";
	private String BUSCARCADEIRA = "SELECT * FROM compra where cod_cadeira = ? and fk_atracao = ?";
	private String BUSCARCODIGO = "SELECT * FROM compra WHERE cod_cadeira = ? AND fk_atracao = ?";
	
	public CompraDAO() {
		c = new Conexao("jdbc:postgresql://localhost:5432/ProjetoPOO","postgres","123");
	}
	
	public Compra buscarCodigo(int cadeira, int atracao) {
		Compra com = null;
		try {
			c.conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(BUSCARCODIGO);
			instrucao.setInt(1, cadeira);
			instrucao.setInt(2, atracao);
			instrucao.execute();
			ResultSet rs = instrucao.executeQuery();
			if(rs.next()) {
				com = new Compra(rs.getInt("id"), rs.getString("data"), rs.getFloat("valor"), rs.getInt("cod_cadeira"));			
			}
		}catch(Exception e) {
			System.out.println("Erro na busca do codigo");
		}
		c.desconectar();
		return com;
	}
	
	public void deletarCompra(int compra_c) {
		try {
			c.conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(DELETARINGRESSO);
			instrucao.setInt(1, compra_c);
			instrucao.execute();
			c.desconectar();
		}catch(Exception e) {
			System.out.println("Erro no deletar");
		}
	}
	
	public Compra buscarIngresso(int compra_c) {
		Compra com = null;
		try {
			c.conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(BUSCARINGRESSO);
			instrucao.setInt(1, compra_c);
			ResultSet rs = instrucao.executeQuery();
			if(rs.next()) {
				com = new Compra(rs.getInt("id"), rs.getString("data"), rs.getFloat("valor"), rs.getInt("cod_cadeira"));			
			}
			c.desconectar();
		}catch(Exception e) {
			System.out.println("Erro Banco Login");
		}
		return com;
	}
	
	public Compra buscarCadeira(int cadeira, int fk_atracao) {
		Compra com = null;
		try {
			c.conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(BUSCARCADEIRA);
			instrucao.setInt(1, cadeira);
			instrucao.setInt(2, fk_atracao);
			ResultSet rs = instrucao.executeQuery();
			if(rs.next()) {
				com = new Compra(rs.getInt("id"), rs.getString("data"), rs.getFloat("valor"), rs.getInt("cod_cadeira"));			
			}
			c.desconectar();
		}catch(Exception e) {
			System.out.println("Erro na busca de cadeira");
		}
		return com;
	}
	
	public ArrayList<Compra> buscarAtracao(int cadeira) {
		Compra com = null;
		ArrayList<Compra> lista = new ArrayList<Compra>();
		try {
			c.conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(BUSCARFKATRACAO);
			instrucao.setInt(1, cadeira);
			ResultSet rs = instrucao.executeQuery();
			while(rs.next()) {
				com = new Compra(rs.getInt("id"), rs.getString("data"), rs.getFloat("valor"), rs.getInt("cod_cadeira"));
				lista.add(com);
			}
			
			c.desconectar();
		}catch(Exception e) {
			System.out.println("Erro na busca de cadeiras");
		}
		return lista;
	}
	
	public ArrayList<Compra> buscarCompra(long cpf_c){
		Compra compra_c = null;
		ArrayList<Compra> lista = new ArrayList<Compra>();
		Cliente cliente;
		Atracao atracao = null;
		ClienteDAO cDAO = new ClienteDAO();
		AtracaoDAO aDAO = new AtracaoDAO();
		try {
			c.conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(BUSCARCOMPRA);
			instrucao.setLong(1, cpf_c);
			ResultSet rs = instrucao.executeQuery();
			while(rs.next()) {
				compra_c = new Compra(rs.getInt("id"), rs.getString("data"), rs.getFloat("valor"), rs.getInt("cod_cadeira"));
				cliente = cDAO.buscarCliente(rs.getLong("fk_cliente"));
				compra_c.setCli(cliente);
				atracao = aDAO.buscarCodigo(rs.getInt("fk_atracao"));
				compra_c.setAt(atracao);
				lista.add(compra_c);
			}
		
		}catch(Exception e) {
			System.out.println("Erro na busca da Compra");
		}
		return lista;
	}

	public void comprarIngresso(int cod_cadeira, long cpf_c, int atracao_c, String data) {
		
		try {	
			c.conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(COM);
			instrucao.setString(1, data);
			instrucao.setInt(2, cod_cadeira);
			instrucao.setLong(3, cpf_c);
			instrucao.setInt(4, atracao_c);
			instrucao.execute();
			c.desconectar();
		}catch(Exception e) {
			System.out.println("Compra não feita");
		}
	}
	
}
