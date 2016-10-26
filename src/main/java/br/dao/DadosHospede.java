package br.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.modelo.DadosHospedes;
import br.postgresDB.Conexao;

public class DadosHospede {

	public Object hospedes(String cnpj) {
		ArrayList<DadosHospedes> hospedes = new ArrayList<DadosHospedes>();
		try{
			
			System.out.println("vai buscar os hospedes");
			
			Connection c = Conexao.getConexao(cnpj);//Faz conexão com banco prova1
			
			PreparedStatement stmt =  c.prepareStatement("select *from hospede order by nome");
			
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				DadosHospedes l = new DadosHospedes();
				
				l.setCodigo(rs.getInt("codhospede"));
				l.setNome(rs.getString("nome"));
				l.setCpf(rs.getString("cpf"));
				l.setRg(rs.getString("rg"));
				l.setNascimento(rs.getString("nascimento"));
				l.setEmail(rs.getString("email"));
				l.setTelefone(rs.getString("fone"));
				l.setTelefoneextra(rs.getString("foneextra"));
				
				hospedes.add(l);
			}
			System.out.println("tudo OK ... retornando a lista com hospedes");
			
		}catch(Exception e){
			System.out.println("CATCH em Object hospedes(String cnpj) em DAO DadosHospede ...");
			e.printStackTrace();
		}
		
		return hospedes;
	}

	public boolean adicionaHospede(String cnpj, DadosHospedes hospede) {
		Connection c = null;
		PreparedStatement stmt = null;
		boolean retorno = false;
		try {
			
			c = Conexao.getConexao(cnpj);//conecta na base de dados criada
			
			String nome=hospede.getNome().toUpperCase();
			
			String sql = "";
			
				System.out.println(".........  Vai inserir novo hospede...........");
				
				sql = "insert into hospede (nome,cpf,rg,nascimento,email,fone,foneextra) "
						+ "values (?,?,?,'"+hospede.getNascimento()+"',?,?,?);";
				
				stmt = c.prepareStatement(sql);	
				stmt.setString(1, nome);
				stmt.setString(2, hospede.getCpf());
				stmt.setString(3, hospede.getRg());
				stmt.setString(4, hospede.getEmail());
				stmt.setString(5, hospede.getTelefone());
				stmt.setString(6, hospede.getTelefoneextra());
				
				System.out.println(".........vai executar, inserir hospede");
				
				stmt.execute();
				
				System.out.println(".........Dados inseridos OK, stmt.close e voltando....");
				
				stmt.close();
				
				retorno = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("......... ERRO EM adicionaHospede(String cnpj, DadosHospedes hospede).............");
			return retorno;
		}	
		
		return retorno;
	}

	public Object BuscaHospede(String cnpj, DadosHospedes hospede) {
		ArrayList<DadosHospedes> hospedes = new ArrayList<DadosHospedes>();
		try{
			
			System.out.println("vai buscar consulta hospedes");
			
			String nome=hospede.getNome().toUpperCase();
			
			Connection c = Conexao.getConexao(cnpj);//Faz conexão com banco prova1
			
			PreparedStatement stmt =  c.prepareStatement("select *from hospede where nome like '%"+nome+"%';");
			
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				DadosHospedes l = new DadosHospedes();
				
				l.setCodigo(rs.getInt("codhospede"));
				l.setNome(rs.getString("nome"));
				l.setCpf(rs.getString("cpf"));
				l.setRg(rs.getString("rg"));
				l.setNascimento(rs.getString("nascimento"));
				l.setEmail(rs.getString("email"));
				l.setTelefone(rs.getString("fone"));
				l.setTelefoneextra(rs.getString("foneextra"));
				
				hospedes.add(l);
			}
			System.out.println("tudo OK ... retornando a lista com consulta hospedes");
			
		}catch(Exception e){
			System.out.println("CATCH em BuscaHospede(String cnpj, DadosHospedes hospede) em DAO DadosHospede ...");
			e.printStackTrace();
		}
		
		return hospedes;
	}

	public Object VerHospede(String cnpj, DadosHospedes hospede) {
		DadosHospedes hospedes = new DadosHospedes();
		try{
			
			System.out.println("vai buscar ver hospede");
			
			Connection c = Conexao.getConexao(cnpj);//Faz conexão com banco prova1
			
			PreparedStatement stmt =  c.prepareStatement("select *from hospede where codhospede = "+hospede.getCodigo()+" ;");
			
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				hospedes.setCodigo(rs.getInt("codhospede"));
				hospedes.setNome(rs.getString("nome"));
				hospedes.setCpf(rs.getString("cpf"));
				hospedes.setRg(rs.getString("rg"));
				hospedes.setNascimento(rs.getString("nascimento"));
				hospedes.setEmail(rs.getString("email"));
				hospedes.setTelefone(rs.getString("fone"));
				hospedes.setTelefoneextra(rs.getString("foneextra"));
			}
			System.out.println("tudo OK ... retornando ver hospede");
			
		}catch(Exception e){
			System.out.println("CATCH em VerHospede(String cnpj, DadosHospedes hospede) em DAO DadosHospede ...");
			e.printStackTrace();
		}
		
		return hospedes;
	}

	public boolean atualizaHospede(String cnpj, DadosHospedes hospede) {
		Connection c = null;
		PreparedStatement stmt = null;
		boolean retorno = false;
		try {
			
			c = Conexao.getConexao(cnpj);//conecta na base de dados criada
			
			String nome=hospede.getNome().toUpperCase();
			
			System.out.println("DADOS RECEBIDOS PARA ATUALIZAÇÃO:");
			System.out.println("NOME: "+nome);
			System.out.println("CPF: "+hospede.getCpf());
			System.out.println("RG: "+hospede.getRg());
			System.out.println("NASCIMENTO: "+hospede.getNascimento());
			System.out.println("EMAIL: "+hospede.getEmail());
			System.out.println("FONE1: "+hospede.getTelefone());
			System.out.println("FONE2: "+hospede.getTelefoneextra());
			System.out.println("CODIGO: "+hospede.getCodigo());
			
			String sql = "";
			
				System.out.println(".........  Vai atualizar hospede...........");
				
				sql = "UPDATE hospede  SET nome = '"+nome+"' , "
						+ "cpf = '"+hospede.getCpf()+"' , "
						+ "rg = '"+hospede.getRg()+"' , "
						+ "nascimento = '"+hospede.getNascimento()+"' , "
						+ "email = '"+hospede.getEmail()+"' , "
						+ "fone = '"+hospede.getTelefone()+"' , "
						+ "foneextra = '"+hospede.getTelefoneextra()+"' "
						+ "where codhospede = '"+hospede.getCodigo()+"' ; ";
				
				stmt = c.prepareStatement(sql);	
				
				System.out.println(".........vai executar, atualizar hospede");
				
				stmt.execute();
				
				System.out.println(".........Dados atualizados OK, stmt.close e voltando....");
				
				stmt.close();
				
				retorno = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("......... ERRO EM atualizaHospede(String cnpj, DadosHospedes hospede).............");
			return retorno;
		}	
		
		return retorno;
	}

	public boolean exluiHospede(String cnpj, DadosHospedes hospede) {
		Connection c = null;
		PreparedStatement stmt = null;
		boolean retorno = false;
		try {
			
			c = Conexao.getConexao(cnpj);//conecta na base de dados criada
			
			String sql = "";
			
				System.out.println(".........  Vai deletar hospede...........");
				
				sql = "DELETE FROM  hospede  WHERE  codhospede = ?";
				
				stmt = c.prepareStatement(sql);	
				stmt.setInt(1, hospede.getCodigo());
				
				System.out.println(".........vai executar, deletar hospede");
				
				stmt.execute();
				
				System.out.println(".........Dados deletados OK, stmt.close e voltando....");
				
				stmt.close();
				
				retorno = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("......... ERRO EM exluiHospede(String cnpj, DadosHospedes hospede).............");
			return retorno;
		}	
		
		return retorno;
	}

}
