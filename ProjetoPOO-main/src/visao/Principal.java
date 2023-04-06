package visao;

import java.util.ArrayList;
import java.util.Scanner;

import dominio.Atracao;
import dominio.Cliente;
import dominio.Compra;
import dominio.Ingresso;
import persistencia.AtracaoDAO;
import persistencia.ClienteDAO;
import persistencia.CompraDAO;
import persistencia.FavoritosDAO;
import persistencia.IngressoDAO;

import java.io.FileOutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;


public class Principal {

	public static void main(String[] args) {
		int op, i, idAux, atracao_c, compra_c, cod_compra, num_cadeira;
		long cpf_c, cpf_fixo = 0, tel_novo;
		boolean login = false, cadeira = false;
		String senha_c, novo_nome;
		Scanner teclado = new Scanner(System.in);
		Ingresso in;
		Cliente c;
		Compra com;
		Atracao at;
		CompraDAO comDAO = new CompraDAO();
		ClienteDAO cDAO = new ClienteDAO();
		IngressoDAO iDAO = new IngressoDAO();
		AtracaoDAO aDAO = new AtracaoDAO();
		FavoritosDAO fDAO = new FavoritosDAO();
		ArrayList<Cliente> cliente = new ArrayList<Cliente>();
		ArrayList<Compra> compra = new ArrayList<Compra>();
		ArrayList<Atracao> atracao = new ArrayList<Atracao>();
		ArrayList<Ingresso> ingresso = new ArrayList<Ingresso>();

		
		
		String data;
		SimpleDateFormat sdff = new SimpleDateFormat("dd/MM/yyyy");
		data = sdff.format( new Date( System.currentTimeMillis() ) );
	
		
		do {
			c = null;
			System.out.println("--------------------------");
			System.out.println("MENU PRINCIPAL");	
			System.out.println("--------------------------");
			System.out.println("1  LOGIN");	
			System.out.println("2  CADASTRAR");
			op = teclado.nextInt();
			switch(op) {
			
			
			case 1:	
				try {
					System.out.println("\nDigite seu CPF: ");
					cpf_fixo = teclado.nextLong();

					teclado.nextLine();
					System.out.println("\nDigite sua senha: ");
					senha_c = teclado.nextLine();	
					c = cDAO.buscarGeral(cpf_fixo, senha_c);
					if(c == null) {
						System.out.println("Dados errados");
					}else {
						System.out.println("Login feito com sucesso!\n");
						login = true;
					}
				}catch(Exception e) {
					System.out.println("Erro");
				}
					
				break;
				
			case 2:
				
				try {
					System.out.println("\nDigite um CPF: ");
					cpf_c = teclado.nextLong();
					if(cpf_c <= 99999999999l && cpf_c >= 11111111111l) {
						c = cDAO.buscarCliente(cpf_c);
						if(c == null) {
							c  = new Cliente();
							c.setCpf(cpf_c);
							teclado.nextLine();
							System.out.println("\nDigite o nome: ");
							c.setNome(teclado.nextLine());
							System.out.println("\nDigite o telefone: ");
							c.setTelefone(teclado.nextInt());
							teclado.nextLine();
							System.out.println("Digite uma senha: ");
							c.setSenha(teclado.nextLine());
							cDAO.cadastro(c);
							System.out.println("\nCadastro Efetuado!\n");
						}else {
							System.out.println("CPF já cadastrado!");
						}
						
					}else {
						System.out.println("Erro no tamanho do CPF");
					}	
				}catch(Exception e) {
					System.out.println("Erro");
				}
				
				
			
				
				break;
			}
			
		}while(login!=true);
		
		
		do {
			System.out.println("--------------------------");
			System.out.println("MENU PRINCIPAL");	
			System.out.println("--------------------------");
			System.out.println("1 - Comprar Ingresso");	
			System.out.println("2 - Desistir do Ingresso");	
			System.out.println("3 - Buscar Ingresso");	
			System.out.println("4 - Atualizar Cadastro");
			System.out.println("5 - Favoritar atracao");
			System.out.println("6 - Lista Atracoes");	
			System.out.println("7 - Sair do Sistema");	
			op = teclado.nextInt();
			
			switch(op) {
			case 1:
				num_cadeira = 0;
				atracao = aDAO.buscarAtracao();
				com = new Compra();
				comDAO = new CompraDAO();
				in = new Ingresso();
				iDAO = new IngressoDAO();
				// LISTAR APRESENTAÇÕES
				System.out.println("Preco de cada atracao: R$80,00");
				for(i=0;i<atracao.size();i++) {
					System.out.println("-----------------------------------------------------------------------");					
					System.out.println("Atracao: " + atracao.get(i).getNome() + "\t\n" + "Data: "+ atracao.get(i).getData() + "\t\n" + "Horatio: " +atracao.get(i).getHorario() + "\t\n" + "Genero: "+ atracao.get(i).getGenero() + "\t\n" +  "Código da apresentacao: " + atracao.get(i).getCod_atracao() + "\n");
				}
				
			
				System.out.println("-----------------------------------------------------------------------");					
				System.out.println("Digite o codigo da atracao desejada: ");
				atracao_c = teclado.nextInt();
				at = aDAO.buscarCodigo(atracao_c);
				if(at == null) {
					System.out.println("Codigo errado, por favor, tente outro");
				}else {
					
					compra = comDAO.buscarAtracao(atracao_c);
					for(i=0;i<compra.size();i++) {
						System.out.print(compra.get(i).getCod_cadeira() + " ");
						num_cadeira++;
					}
					do {
						System.out.println("\nCadeiras preenchidas: " + num_cadeira);
						if(num_cadeira>=200) {
							System.out.println("Sala lotada!");
							cadeira = true;
						}else {
							System.out.println("\nQual a sua cadeira: ");
							cod_compra = teclado.nextInt();
							com = comDAO.buscarCadeira(cod_compra, atracao_c);
							if(com == null) {
												
								comDAO.comprarIngresso(cod_compra, cpf_fixo, atracao_c, data);
								com = comDAO.buscarCodigo(cod_compra, atracao_c);
								if(com == null) {
									System.out.println("Erro no comprar ingresso");
								}else {
									System.out.println("Codigo do ingresso: " + com.getId() + " NAO PERCA");
									idAux = com.getId();
									iDAO.validarIngresso(in, atracao_c);
									cadeira = true;
									System.out.println("Compra feita com sucesso!");
								}
								
	
							}else {
								System.out.println("Cadeira já selecionada");
								
							}
						}
							
						}while(cadeira!=true);
					
				}
								
				
				
				break;
				
			case 2:
				do {
					System.out.println("---------------------------------------------------");
					System.out.println("DESISTIR DO INGRESSO");	
					System.out.println("---------------------------------------------------");
					System.out.println("1  Buscar pelo Código do Ingresso");	
					System.out.println("2  Voltar ao Menu Principal");
					op = teclado.nextInt();
					if(op == 1) {
						System.out.println("Digite código da compra");
						compra_c = teclado.nextInt();
						com = comDAO.buscarIngresso(compra_c);
						if(com == null)
							System.out.println("Codigo da compra errado");
						else {
							comDAO.deletarCompra(compra_c);
							System.out.println("Compra deletada com sucesso!");
						}
					}
					
				}while(op!=2);
					
				break;
				
				
			case 3:
				System.out.println("---------------------------------------------------");
				System.out.println("BUSCAR INGRESSO");	
				System.out.println("---------------------------------------------------");
				System.out.println("1  Buscar pelo Código do Ingresso");	
				System.out.println("2  Voltar ao Menu Principal");
				op = teclado.nextInt();
				if(op == 1) {
					System.out.println("Digite o codigo: ");
					idAux = teclado.nextInt();
					teclado.nextLine();
					in = iDAO.buscarIngresso(idAux);
					if(in == null) {
						System.out.println("Nao achou");
					}else {
						ingresso = iDAO.buscarCompra(idAux);
						for(i=0;i<ingresso.size();i++) {
							System.out.println(ingresso.get(i).getAt().getNome() + " " + ingresso.get(i).getAt().getData() + " " + ingresso.get(i).getAt().getHorario() + " " + ingresso.get(i).getAt().getGenero());
						}
						
					}
					
				}
					
				break;	
				
				
			case 4:
				do {
					System.out.println("---------------------------------------------------");
					System.out.println("ATUALIZAR CADASTRO");	
					System.out.println("---------------------------------------------------");
					System.out.println("1 - Atualizar CPF");	
					System.out.println("2 - Atualizar Senha");
					System.out.println("3 - Atualizar Telefone");
					System.out.println("4 - Atualizar Nome");
					System.out.println("5 - Sair");
					op = teclado.nextInt();
					switch(op) {
					
					
					case 1:
						System.out.println("\nDigite o CPF antigo: ");
						cpf_c = teclado.nextLong();
						c = cDAO.buscarCliente(cpf_c);
						if(c == null) {
							System.out.println("\nCPF nao existe");
						}else {
							System.out.println("\nDigite o CPF novo: ");
							cpf_fixo = teclado.nextLong();
							cDAO.atualizarCpf(cpf_fixo, cpf_c);
							System.out.println("\nCPF atualizado!");

						}
						break;
						
						
					case 2:
						
						teclado.nextLine();
						System.out.println("\nDigite a SENHA antiga: ");
						senha_c = teclado.nextLine();
						System.out.println(senha_c);	
						c = cDAO.buscarGeral(cpf_fixo, senha_c);
						if(c == null) {
							System.out.println("\nSENHA nao existe");
						}else {
							c = new Cliente();
							System.out.println("\nDigite a SENHA nova: ");
							c.setSenha(teclado.nextLine());
							cDAO.atualizarSenha(c, senha_c);
							System.out.println("Senha Atualizado! ");
						}
						break;
						
					case 3:
						System.out.println("\nTELEFONE antigo: ");
						c = cDAO.buscarCliente(cpf_fixo);
						if(c == null) {
							System.out.println("Erro na consulta");
						}else {
							System.out.println(c.getTelefone());
							teclado.nextLine();
							System.out.println("Digite o numero novo: ");
							tel_novo = teclado.nextLong();
							cDAO.atualizarTelefone(tel_novo, c);
							System.out.println("Telefone Atualizado! ");
						}
						break;
						
						
					case 4:
						System.out.println("\nNOME antigo: ");
						c = cDAO.buscarCliente(cpf_fixo);
						if(c == null) {
							System.out.println("Erro no nome");

						}else {
							System.out.println(c.getNome());
							teclado.nextLine();
							System.out.println("Digite o novo nome: ");
							novo_nome = teclado.nextLine();
							cDAO.atualizarNome(novo_nome, c);	
							System.out.println("Nome Atualizado! ");
						}
						break;
					}
				}while(op!=5);
				
				break;
			
			
			case 5:
				
				atracao = aDAO.buscarAtracao();
				for(i=0;i<atracao.size();i++) {
					System.out.println("-----------------------------------------------------------------------");					
					System.out.println("Atracao: " + atracao.get(i).getNome() + "\t\n" + "Data: "+ atracao.get(i).getData() + "\t\n" + "Horatio: " +atracao.get(i).getHorario() + "\t\n" + "Genero: "+ atracao.get(i).getGenero() + "\t\n" +  "Código da apresentacao: " + atracao.get(i).getCod_atracao() + "\n");
				}
				System.out.println("Digite o codigo da atracao que deseja favoritar: ");
				atracao_c = teclado.nextInt();
				fDAO.favoritar(cpf_fixo, atracao_c);
				break;
			
			
			case 6:
				
				System.out.println("Minhas atracoes: ");
				compra = comDAO.buscarCompra(cpf_fixo);
				for(i=0;i<compra.size();i++) {
					System.out.println(compra.get(i).getAt().getNome() + " " + compra.get(i).getAt().getData() + " " + compra.get(i).getAt().getHorario() + " " + compra.get(i).getAt().getGenero());

				}
				break;
			}
				
		}while(op!=7);
		
		
	}


}
