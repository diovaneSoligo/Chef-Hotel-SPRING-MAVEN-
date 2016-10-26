package br.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.modelo.DadosHospedagem;
import br.postgresDB.Conexao;

public class DadosCheckInSpan {

	public Object busca(String cnpj) {
		ArrayList<DadosHospedagem> hospedagem = new ArrayList<DadosHospedagem>();
		
		Date dat=new Date();
        SimpleDateFormat x= new SimpleDateFormat("dd/MM/yyyy");
        String data = x.format(dat);
		System.out.println("DATA:"+data);
		
		try{
			
			System.out.println("vai buscar os checkins de hoje");
			
			Connection c = Conexao.getConexao(cnpj);//Faz conexão com banco prova1
			
			PreparedStatement stmt =  c.prepareStatement("select *from "
					+ "hospedagem h,hospede o,quarto q where "
					+ "h.codhospede = o.codhospede and "
					+ "h.codquarto = q.codquarto and "
					+ "xin = 0 and xout=0 and "
					+ "entrada = '"+data+"';");
			
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				DadosHospedagem l = new DadosHospedagem();
				
				l.setCodhospedagem(rs.getInt("codhospedagem"));
				l.setCodhospedehospedagem(rs.getInt("codhospede"));
				l.setCodquartohospedagem(rs.getInt("codquarto"));
				l.setNumeroacompanhanteshospedagem(rs.getInt("numeroacompanhantes"));
				l.setEntradahospedagem(rs.getString("entrada"));
				l.setSaidahospedagem(rs.getString("saida"));
				l.setXinhopedagem(rs.getInt("xin"));
				l.setXouthospedagem(rs.getInt("xout"));
				
				l.setCodhospede(rs.getInt("codhospede"));
				l.setNomehospede(rs.getString("nome"));
				l.setCpfhospede(rs.getString("cpf"));
				l.setRghospede(rs.getString("rg"));
				l.setNascimentohospede(rs.getString("nascimento"));
				l.setEmailhospede(rs.getString("email"));
				l.setFonehospede(rs.getString("fone"));
				l.setFoneextrahospede(rs.getString("foneextra"));
				
				l.setCodquarto(rs.getInt("codquarto"));
				l.setNomequarto(rs.getString("tituloquarto"));
				l.setCapacidadequarto(rs.getInt("capacidade"));
				l.setDisponivelquarto(rs.getInt("disponivel"));
				l.setDiariaquarto(rs.getFloat("diaria"));
				l.setDescricaoquarto(rs.getString("descricao"));
				
				hospedagem.add(l);
			}
			System.out.println("tudo OK ... retornando a lista com checkins a ser realizados");
			
		}catch(Exception e){
			System.out.println("CATCH em Object busca(String cnpj) em DAO DadosCheckInSpan ...");
			e.printStackTrace();
		}
		
		return hospedagem;
	}

	public Object buscaPesquisa(String cnpj, DadosHospedagem dados) {
		ArrayList<DadosHospedagem> hospedagem = new ArrayList<DadosHospedagem>();
		
		Date dat=new Date();
        SimpleDateFormat x= new SimpleDateFormat("dd/MM/yyyy");
        String data = x.format(dat);
		System.out.println("DATA:"+data);
		
		String nome=dados.getNomehospede().toUpperCase();
		
		try{
			
			System.out.println("vai buscar os checkins de hoje");
			
			Connection c = Conexao.getConexao(cnpj);//Faz conexão com banco prova1
			
			PreparedStatement stmt =  c.prepareStatement("select *from "
					+ "hospedagem h,hospede o,quarto q where "
					+ "h.codhospede = o.codhospede and "
					+ "h.codquarto = q.codquarto and "
					+ "xin = 0 and xout=0 and "
					+ "entrada = '"+data+"' and nome like '%"+nome+"%';");
			
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				DadosHospedagem l = new DadosHospedagem();
				
				l.setCodhospedagem(rs.getInt("codhospedagem"));
				l.setCodhospedehospedagem(rs.getInt("codhospede"));
				l.setCodquartohospedagem(rs.getInt("codquarto"));
				l.setNumeroacompanhanteshospedagem(rs.getInt("numeroacompanhantes"));
				l.setEntradahospedagem(rs.getString("entrada"));
				l.setSaidahospedagem(rs.getString("saida"));
				l.setXinhopedagem(rs.getInt("xin"));
				l.setXouthospedagem(rs.getInt("xout"));
				
				l.setCodhospede(rs.getInt("codhospede"));
				l.setNomehospede(rs.getString("nome"));
				l.setCpfhospede(rs.getString("cpf"));
				l.setRghospede(rs.getString("rg"));
				l.setNascimentohospede(rs.getString("nascimento"));
				l.setEmailhospede(rs.getString("email"));
				l.setFonehospede(rs.getString("fone"));
				l.setFoneextrahospede(rs.getString("foneextra"));
				
				l.setCodquarto(rs.getInt("codquarto"));
				l.setNomequarto(rs.getString("tituloquarto"));
				l.setCapacidadequarto(rs.getInt("capacidade"));
				l.setDisponivelquarto(rs.getInt("disponivel"));
				l.setDiariaquarto(rs.getFloat("diaria"));
				l.setDescricaoquarto(rs.getString("descricao"));
				
				hospedagem.add(l);
			}
			System.out.println("tudo OK ... retornando a lista com checkins a ser realizados");
			
		}catch(Exception e){
			System.out.println("CATCH em Object buscaPesquisa(String cnpj, DadosHospedagem dados) em DAO DadosCheckInSpan ...");
			e.printStackTrace();
		}
		
		return hospedagem;
	}


public boolean realizacheckin(String cnpj, DadosHospedagem dados) {
	Connection c = null;
	PreparedStatement stmt = null;
	boolean retorno = false;
	try {
		
		c = Conexao.getConexao(cnpj);//conecta na base de dados criada
		
			String sql = "";
		
			System.out.println("......... FAZER CHECK-IN\nHOSPEDAGEM: "+dados.getCodhospedagem()+"QUARTO:"+dados.getCodquarto());
			
			sql = "UPDATE hospedagem SET xin  = 1 where codhospedagem = "+dados.getCodhospedagem()+";";
			
			stmt = c.prepareStatement(sql);	
			
			stmt.execute();
			
			sql = "UPDATE quarto SET disponivel  = 0 where codquarto = "+dados.getCodquarto()+" ;";
			
			stmt = c.prepareStatement(sql);	
			
			stmt.execute();
			
			System.out.println(".........check-in OK, stmt.close e voltando....");
			
			stmt.close();
			
			retorno = true;
		
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("......... ERRO EM realizacheckin(String cnpj, DadosHospedagem dados).............");
		return retorno;
	}	
	
	return retorno;
	}

}
