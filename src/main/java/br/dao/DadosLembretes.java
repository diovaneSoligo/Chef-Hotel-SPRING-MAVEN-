package br.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.modelo.InfoLembretes;
import br.postgresDB.Conexao;

public class DadosLembretes {

	public Object lembretes(String cnpj) {
		ArrayList<InfoLembretes> lembretes = new ArrayList<InfoLembretes>();
		try{
			
			System.out.println("vai buscar os lembretes");
			
			Connection c = Conexao.getConexao(cnpj);//Faz conexão com banco prova1
			
			PreparedStatement stmt =  c.prepareStatement("select *from lembretes order by criado desc");
			
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				InfoLembretes l = new InfoLembretes();
				
				l.setCodigo(rs.getInt("codlembrete"));
				l.setDescricao(rs.getString("descricaolembrete"));
				l.setTitulo(rs.getString("titulo"));
				l.setCriado(rs.getString("criado"));
				
				lembretes.add(l);
			}
			System.out.println("tudo OK ... retornando a lista com lembretes");
			
		}catch(Exception e){
			System.out.println("CATCH em public Object lembretes(String cnpj) em DAO DadosLembretes ...");
			e.printStackTrace();
		}
		
		return lembretes;
	}

	public boolean adicionaLembrete(String cnpj, InfoLembretes lembrete) {
		Connection c = null;
		PreparedStatement stmt = null;
		boolean retorno = false;
		try {
			
			c = Conexao.getConexao(cnpj);//conecta na base de dados criada
			
			Date dat=new Date();
            SimpleDateFormat x= new SimpleDateFormat("dd/MM/yyyy");
            String data = x.format(dat);
			System.out.println("DATA:"+data);
			lembrete.setCriado(data);
			
			String sql = "";
			
				System.out.println("......... inserir novo lembrete");
				
				sql = "insert into lembretes (titulo,descricaolembrete,criado)values('"+lembrete.getTitulo()+"','"+lembrete.getDescricao()+"','"+lembrete.getCriado()+"');";
				
				stmt = c.prepareStatement(sql);	
				
				System.out.println(".........vai executar, inserir lembrete");
				
				stmt.execute();
				
				System.out.println(".........Lembrete Inserido OK, stmt.close e voltando....");
				
				stmt.close();
				
				retorno = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("......... ERRO EM Inserir Lembrete em DAO dados lembrete(String cnpj.............");
			return retorno;
		}	
		
		return retorno;
	}

	public boolean apagaLembrete(String cnpj, InfoLembretes lembrete) {
		Connection c = null;
		PreparedStatement stmt = null;
		boolean retorno = false;
		try {
			
			c = Conexao.getConexao(cnpj);//conecta na base de dados criada
			
			String sql = "";
			
				System.out.println("......... APAGAR LEMBRETE CODIGO: "+lembrete.getCodigo());
				
				sql = "delete from lembretes WHERE  codlembrete = "+lembrete.getCodigo()+" ;";
				
				stmt = c.prepareStatement(sql);	
				System.out.println(".........vai executar, deletar lembrete");
				
				stmt.execute();
				
				System.out.println(".........Lembrete deletado OK, stmt.close e voltando....");
				
				stmt.close();
				
				retorno = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("......... ERRO EM deletar Lembrete em DAO dados apagalembrete(String cnpj.............");
			return retorno;
		}	
		
		return retorno;
	}

	public Object buscaLembrete(String cnpj, InfoLembretes lembrete) {
		InfoLembretes lembretes = new InfoLembretes();
		try{
			
			System.out.println("vai buscar o lembrete a ser editado");
			
			Connection c = Conexao.getConexao(cnpj);//Faz conexão com banco prova1
			
			PreparedStatement stmt =  c.prepareStatement("select *from lembretes where codlembrete = "+lembrete.getCodigo()+" ; ");
			
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){;
			lembretes.setCodigo(rs.getInt("codlembrete"));
			lembretes.setDescricao(rs.getString("descricaolembrete"));
			lembretes.setTitulo(rs.getString("titulo"));
			lembretes.setCriado(rs.getString("criado"));
				
			}
			System.out.println("tudo OK ... retornando o lembrete a ser alterado");
			
		}catch(Exception e){
			System.out.println("CATCH em public Object buscalembretes(String cnpj) em DAO DadosLembretes ...");
			e.printStackTrace();
		}
		
		return lembretes;
	}

	public boolean atualizaLembrete(String cnpj, InfoLembretes lembrete) {
		Connection c = null;
		PreparedStatement stmt = null;
		boolean retorno = false;
		try {
			
			c = Conexao.getConexao(cnpj);//conecta na base de dados criada
			
			String sql = "";
			
				System.out.println("......... ALTERAR LEMBRETE CODIGO: "+lembrete.getCodigo());
				
				sql = "update lembretes set  titulo= '"+lembrete.getTitulo()+"' , "
						+ "descricaolembrete = '"+lembrete.getDescricao()+"' "
								+ "where codlembrete = "+lembrete.getCodigo()+" ;";
				
				stmt = c.prepareStatement(sql);	
				System.out.println(".........vai executar, alterar lembrete");
				
				stmt.execute();
				
				System.out.println(".........Lembrete atualizado OK, stmt.close e voltando....");
				
				stmt.close();
				
				retorno = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("......... ERRO EM atualiza Lembrete em DAO dados atualizalembrete(String cnpj.............");
			return retorno;
		}	
		
		return retorno;
	}

}
