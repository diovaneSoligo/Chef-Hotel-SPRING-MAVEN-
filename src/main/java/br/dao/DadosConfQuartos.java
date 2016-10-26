package br.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.modelo.DadosQuartos;
import br.postgresDB.Conexao;

public class DadosConfQuartos {

	public Object quartos(String cnpj) {
		ArrayList<DadosQuartos> quartos = new ArrayList<DadosQuartos>();
		try{
			
			System.out.println("vai buscar os quartos");
			
			Connection c = Conexao.getConexao(cnpj);//Faz conexão com banco prova1
			
			PreparedStatement stmt =  c.prepareStatement("select *from quarto order by tituloquarto;");
			
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				DadosQuartos l = new DadosQuartos();
				
				l.setCodigo(rs.getInt("codquarto"));
				l.setNome(rs.getString("tituloquarto"));
				l.setCapacidade(rs.getInt("capacidade"));
				l.setDisponivel(rs.getInt("disponivel"));
				l.setDiaria(rs.getFloat("diaria"));
				l.setDescricao(rs.getString("descricao"));
				
				quartos.add(l);
			}
			System.out.println("tudo OK ... retornando a lista com quartos");
			
		}catch(Exception e){
			System.out.println("CATCH em Object quartos(String cnpj) em DAO DadosQuartos ...");
			e.printStackTrace();
		}
		
		return quartos;
	}

	public boolean adicionaQuarto(String cnpj, DadosQuartos quarto) {
		Connection c = null;
		PreparedStatement stmt = null;
		boolean retorno = false;
		try {
			
			c = Conexao.getConexao(cnpj);//conecta na base de dados criada
			
			String nome=quarto.getNome().toUpperCase();
			
			String sql = "";
			
				System.out.println(".........  Vai inserir novo quarto...........");
				
				sql = "insert into quarto (tituloquarto,capacidade,disponivel,diaria,descricao)values (?,?,1,?,?) ;";
				
				stmt = c.prepareStatement(sql);	
				stmt.setString(1, nome);
				stmt.setInt(2, quarto.getCapacidade());
				stmt.setFloat(3, quarto.getDiaria());
				stmt.setString(4, quarto.getDescricao());
				
				System.out.println(".........vai executar, inserirquarto");
				
				stmt.execute();
				
				System.out.println(".........Dados cadastrados OK, stmt.close e voltando....");
				
				stmt.close();
				
				retorno = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("......... ERRO EM adicionaQuarto(String cnpj, DadosQuartos quarto).............");
			return retorno;
		}	
		
		return retorno;
	}

	public Object buscaQuartos(String cnpj, DadosQuartos quarto) {
		ArrayList<DadosQuartos> quartos = new ArrayList<DadosQuartos>();
		try{
			
			System.out.println("vai pesquisar os quartos");
			
			String nome = quarto.getNome().toUpperCase();
			
			Connection c = Conexao.getConexao(cnpj);//Faz conexão com banco prova1
			
			PreparedStatement stmt =  c.prepareStatement("select *from quarto where tituloquarto like '%"+nome+"%' order by tituloquarto;");
			
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				DadosQuartos l = new DadosQuartos();
				
				l.setCodigo(rs.getInt("codquarto"));
				l.setNome(rs.getString("tituloquarto"));
				l.setCapacidade(rs.getInt("capacidade"));
				l.setDisponivel(rs.getInt("disponivel"));
				l.setDiaria(rs.getFloat("diaria"));
				l.setDescricao(rs.getString("descricao"));
				
				quartos.add(l);
			}
			System.out.println("tudo OK ... retornando a lista com quartos");
			
		}catch(Exception e){
			System.out.println("CATCH em Object buscaQuartos(String cnpj, DadosQuartos quarto) em DAO DadosQuartos ...");
			e.printStackTrace();
		}
		
		return quartos;
	}

	public Object buscaQuarto(String cnpj, DadosQuartos quarto) {
		DadosQuartos quartos = new DadosQuartos();
		try{
			
			System.out.println("vai buscar o quarto");
			
			Connection c = Conexao.getConexao(cnpj);//Faz conexão com banco prova1
			
			PreparedStatement stmt =  c.prepareStatement("select *from quarto where codquarto = "+quarto.getCodigo()+" ;");
			
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				
				quartos.setCodigo(rs.getInt("codquarto"));
				quartos.setNome(rs.getString("tituloquarto"));
				quartos.setCapacidade(rs.getInt("capacidade"));
				quartos.setDisponivel(rs.getInt("disponivel"));
				quartos.setDiaria(rs.getFloat("diaria"));
				quartos.setDescricao(rs.getString("descricao"));
				
			}
			System.out.println("tudo OK ... retornando a lista com dados do quarto");
			
		}catch(Exception e){
			System.out.println("CATCH em Object buscaQuarto(String cnpj, DadosQuartos quarto) em DAO DadosQuartos ...");
			e.printStackTrace();
		}
		
		return quartos;
	}

	public boolean atualizaQuarto(String cnpj, DadosQuartos quarto) {
		Connection c = null;
		PreparedStatement stmt = null;
		boolean retorno = false;
		try {
			
			c = Conexao.getConexao(cnpj);//conecta na base de dados criada
			
			String nome=quarto.getNome().toUpperCase();
			
			String sql = "";
			
				System.out.println(".........  Vai atualizar quarto...........");
				
				sql = "UPDATE quarto  SET tituloquarto = ? , capacidade = ? , diaria = ? , descricao = ? where codquarto = "+quarto.getCodigo()+" ;";
				
				stmt = c.prepareStatement(sql);	
				stmt.setString(1, nome);
				stmt.setInt(2, quarto.getCapacidade());
				stmt.setFloat(3, quarto.getDiaria());
				stmt.setString(4, quarto.getDescricao());
				
				System.out.println(".........vai executar, atualizarquarto");
				
				stmt.execute();
				
				System.out.println(".........Dados atualizados OK, stmt.close e voltando....");
				
				stmt.close();
				
				retorno = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("......... ERRO EM atualizaQuarto(String cnpj, DadosQuartos quarto).............");
			return retorno;
		}	
		
		return retorno;
	}

	public boolean excluiQuarto(String cnpj, DadosQuartos quarto) {
		Connection c = null;
		PreparedStatement stmt = null;
		boolean retorno = false;
		try {
			
			c = Conexao.getConexao(cnpj);//conecta na base de dados criada
			
			String sql = "";
			
				System.out.println(".........  Vai deletar quarto...........");
				
				sql = "DELETE FROM quarto WHERE  codquarto = "+quarto.getCodigo()+" ;";
				
				stmt = c.prepareStatement(sql);	
				
				System.out.println(".........vai deletar, atualizarquarto");
				
				stmt.execute();
				
				System.out.println(".........quarto deletado OK, stmt.close e voltando....");
				
				stmt.close();
				
				retorno = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("......... ERRO EM excluiQuarto(String cnpj, DadosQuartos quarto).............");
			return retorno;
		}	
		
		return retorno;
	}

}
