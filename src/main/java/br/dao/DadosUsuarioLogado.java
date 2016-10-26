package br.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import java.util.Date;

import br.modelo.DadosCadastro;
import br.modelo.Info;
import br.postgresDB.Conexao;

public class DadosUsuarioLogado {

	public Object dados(DadosCadastro log) {
		DadosCadastro dados = new DadosCadastro();
		try{
			
			System.out.println("vai fazer a conexão com o banco para buscar os dados de usuario");
			
			Connection c = Conexao.getConexao(log.getCnpj());//Faz conexão com a base de dados
			System.out.println("conectou com a base...");
			
			PreparedStatement stmt =  c.prepareStatement("select *from hotel");
			
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				dados.setNome(rs.getString("fantasia"));
				System.out.println("DADOS\nNOME:"+dados.getNome());
				
				dados.setCnpj(rs.getString("cnpj"));
				System.out.println("CNPJ:"+dados.getCnpj());
				
				dados.setEndereço(rs.getString("endereco"));
				System.out.println("ENDEREÇO:"+dados.getEndereço());
				
				dados.setCidade(rs.getString("cidade"));
				System.out.println("CIDADE:"+dados.getCidade());
				
				dados.setEmail(rs.getString("email"));
				System.out.println("EMAIL:"+dados.getEmail());
				
				dados.setResponsave(rs.getString("responsavel"));
				System.out.println("RESPONSAVEL:"+dados.getResponsave());
				
				dados.setSennha(rs.getString("senha"));
				System.out.println("SENHA:"+dados.getSennha());
			}
			System.out.println("tudo OK ... retornando os dados do usuario na sessão");
			stmt.close();
		}catch(Exception e){
			System.out.println("CATCH em public Object dados(DadosCadastro log) em DadosUsuarioLogado.java ...");
			e.printStackTrace();
		}
		
		return dados;
	}

	public Object numeros(String cnpj) {
		System.out.println("Dentro de buscar numeros, CNPJ:"+cnpj);
		
		Info numeros = new Info();
		
		try{
			System.out.println("vai fazer a conexão com o banco para buscar os dados de numeros");
			
			Connection c = Conexao.getConexao(cnpj);//Faz conexão com a base de dados
			System.out.println("conectou com a base...");
			
			Date dat=new Date();
            SimpleDateFormat x= new SimpleDateFormat("dd/MM/yyyy");
            String data = x.format(dat);
			System.out.println("DATA:"+data);
			numeros.setData(data);
			
			PreparedStatement stmt =  c.prepareStatement("select count(*)entram from hospedagem where "
					+ "entrada = '"+data+"' and xin = 0 and xout = 0 ;");
			System.out.println("executar comando...");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				numeros.setEntrada(rs.getInt("entram"));
				System.out.println("DADOS\nENTRAM HOJE:"+numeros.getEntrada());
			}
			
			stmt =  c.prepareStatement("select count(*)saem from hospedagem where "
					+ "saida = '"+data+"' and xin = 1 and xout = 0 ;");
			System.out.println("executar comando...");
			rs = stmt.executeQuery();
			while(rs.next()){
				numeros.setSaida(rs.getInt("saem"));
				System.out.println("SAEM HOJE:"+numeros.getSaida());
			}
			
			stmt =  c.prepareStatement("SELECT sum(numeroacompanhantes) + (select count(*)from hospedagem where xin = 1 and xout = 0) total_de_hospedes from hospedagem where xin = 1 and xout = 0 ;");
			System.out.println("executar comando...");
			rs = stmt.executeQuery();
			while(rs.next()){
				numeros.setNumhospedados(rs.getInt("total_de_hospedes"));
				System.out.println("TOTAL DE HOSPEDADOS:"+numeros.getNumhospedados());
			}
			
			stmt =  c.prepareStatement("select count(*) reservas from hospedagem "
					+ "where xin = 0 and xout = 0;");
			System.out.println("executar comando...");
			rs = stmt.executeQuery();
			while(rs.next()){
				numeros.setReservas(rs.getInt("reservas"));
				System.out.println("TOTAL DE RESERVAS:"+numeros.getReservas());
			}
			
			stmt =  c.prepareStatement("select count(*) lembrete from lembretes");
			System.out.println("executar comando...");
			rs = stmt.executeQuery();
			while(rs.next()){
				numeros.setLembretes(rs.getInt("lembrete"));
				System.out.println("TOTAL DE LEMBRETES:"+numeros.getLembretes());
			}
			
			stmt =  c.prepareStatement("select count(*) quartos from quarto where disponivel = 1");
			rs = stmt.executeQuery();
			while(rs.next()){
				numeros.setQuartosdisponiveis(rs.getInt("quartos"));
				System.out.println("TOTAL DE QUARTOS LIVRES:"+numeros.getQuartosdisponiveis());
			}
			
			
			
			System.out.println("tudo OK ... retornando os dados do usuario na sessão");
			stmt.close();
		}catch(Exception e){
			System.out.println("CATCH em buscar numeros em DadosUsuarioLogado.java ...");
			e.printStackTrace();
		}
		
		return numeros;
	}


}

