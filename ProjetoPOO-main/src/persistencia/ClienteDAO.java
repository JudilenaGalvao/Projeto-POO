package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dominio.Cliente;

public class ClienteDAO {
	Conexao c;
	private String REL = "select * from cliente";
	private String INC = "insert into cliente(cpf, nome, telefone, senha) values(?,?,?,?)";
	private String BUSCARLOGIN = "select * from cliente where cpf =?";
	private String BUSCARSENHA = "select * from cliente where senha =?";
	private String BUSCARGERAL = "SELECT * FROM cliente where cpf = ? and senha = ?;";
	private String BUSCARCLIENTE = "select * from cliente where cpf =?";
	private String ATUALIZARCPF = "update cliente set cpf = ? where cpf = ?";
	private String ATUALIZARSENHA = "update cliente set senha = ? where senha = ?";
	private String ATUALIZARNOME = "update cliente set nome = ? where nome = ?";
	private String ATUALIZARTELEFONE = "update cliente set telefone = ? where telefone = ?";

	public ClienteDAO() {
		c = new Conexao("jdbc:postgresql://localhost:5432/ProjetoPOO","postgres","123");
	}
	
	public void atualizarCpf(long cpf_novo, long cpf_fixo) {
		try {
			c.conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(ATUALIZARCPF);
			instrucao.setLong(1, cpf_novo);
			instrucao.setLong(2, cpf_fixo);
			instrucao.execute();
			c.desconectar();
		}catch(Exception e) {
			System.out.println("Erro no deletar");
		}
	}
	
	public void atualizarSenha(Cliente cli ,String senha_velha) {
		try {
			c.conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(ATUALIZARSENHA);
			instrucao.setString(1, cli.getSenha());
			instrucao.setString(2, senha_velha);
			instrucao.execute();
			c.desconectar();
		}catch(Exception e) {
			System.out.println("Erro no deletar");
		}
	}
	
	public void atualizarTelefone(long tel_novo, Cliente cli) {
		try {
			c.conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(ATUALIZARTELEFONE);
			instrucao.setLong(1, tel_novo);
			instrucao.setLong(2, cli.getTelefone());
			instrucao.execute();
			c.desconectar();
		}catch(Exception e) {
			System.out.println("Erro no telefone");
		}
	}
	
	public void atualizarNome(String nome_novo, Cliente cli) {
		try {
			c.conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(ATUALIZARNOME);
			instrucao.setString(1, nome_novo);
			instrucao.setString(2, cli.getNome());
			instrucao.execute();
			c.desconectar();
		}catch(Exception e) {
			System.out.println("Erro no telefone");
		}
	}
	
	public ArrayList<Cliente> emitirRelatorio() {
		Cliente pessoa;
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		try {
			c.conectar(); // se conecta com o bd
			Statement instrucao = c.getConexao().createStatement(); // "new" do Statement - cria uma instru��o
			ResultSet rs = instrucao.executeQuery(REL);
			while(rs.next()) { //andando no resultset
				pessoa = new Cliente(rs.getInt("cpf"), rs.getString("nome"), rs.getLong("telefone"), rs.getString("senha"));
				lista.add(pessoa);
			}
			c.desconectar(); //se desconecta com o bd
		}catch(Exception e) {
			System.out.println("Erro no relatorio");
		}
		return lista;
		
	}
	
	public Cliente buscarGeral(long cpf_c, String senha_c) {
		Cliente pessoa = null;
		try {
			c.conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(BUSCARGERAL);
			instrucao.setLong(1, cpf_c);
			instrucao.setString(2, senha_c);
			ResultSet rs = instrucao.executeQuery();
			if(rs.next()) {
				pessoa = new Cliente(rs.getLong("cpf"), rs.getString("nome"), rs.getLong("telefone"), rs.getString("senha"));
			}
		}catch(Exception e) {
			System.out.println("Erro Banco Login");
		}
		
		return pessoa;
	}
	
	public Cliente buscarLogin(long cpf_c) {
		Cliente pessoa = null;
		try {
			c.conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(BUSCARLOGIN);
			instrucao.setLong(1, cpf_c);
			ResultSet rs = instrucao.executeQuery();
			if(rs.next()) {
				pessoa = new Cliente(rs.getLong("cpf"), rs.getString("nome"), rs.getLong("telefone"), rs.getString("senha"));
			}
		}catch(Exception e) {
			System.out.println("Erro Banco Login");
		}
		return pessoa;
	}
	
	public Cliente buscarSenha(String senha_c) {
		Cliente pessoa = null;
		try {
			c.conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(BUSCARSENHA);
			instrucao.setString(1, senha_c);
			ResultSet rs = instrucao.executeQuery();
			if(rs.next()) {
				pessoa = new Cliente(rs.getLong("cpf"), rs.getString("nome"), rs.getLong("telefone"), rs.getString("senha"));
			}
		}catch(Exception e) {
			System.out.println("Erro Banco Login");
		}
		return pessoa;
	}
	
	public Cliente buscarCliente(long cpf_c) {
		Cliente pessoa = null;
		try {
			c.conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(BUSCARCLIENTE);
			instrucao.setLong(1, cpf_c);
			ResultSet rs = instrucao.executeQuery();
			if(rs.next()) {
				pessoa = new Cliente(rs.getLong("cpf"), rs.getString("nome"), rs.getLong("telefone"), rs.getString("senha"));
			}
		}catch(Exception e) {
			System.out.println("Erro Banco Login");
		}
		return pessoa;
	}
	
	public void cadastro(Cliente pessoa){
		try {
			c.conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(INC);
			instrucao.setLong(1, pessoa.getCpf());
			instrucao.setString(2, pessoa.getNome());
			instrucao.setLong(3, pessoa.getTelefone());
			instrucao.setString(4, pessoa.getSenha());
			instrucao.execute();
			c.desconectar();
		}catch(Exception e) {
			System.out.println("Inclusao erro");
		}
		
	}
	
}
