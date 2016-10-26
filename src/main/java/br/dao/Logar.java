package br.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.modelo.DadosCadastro;
import br.postgresDB.Conexao;

public class Logar {

public boolean logar(DadosCadastro log){
	Connection c = null;
	PreparedStatement stmt = null;
	boolean retorno = false;
	
	try {
		System.out.println("vai conecta com a base de dados");
		c = Conexao.getConexao(log.getCnpj());//conecta na base de dados criada
		System.out.println("retornou da conexao");
		
		String sql = "";
		
		sql = "select * from hotel where "
				+ "cnpj =? and senha =? ;";
		
		stmt = c.prepareStatement(sql);
		stmt.setString(1, log.getCnpj());
		stmt.setString(2, log.getSennha());	
		
		ResultSet rs =  stmt.executeQuery();
		
		while(rs.next()){
			log.setCnpj(rs.getString("cnpj"));
			log.setSennha(rs.getString("senha"));
			retorno = true;
		}
		}catch(Exception e){
			e.printStackTrace();		
		}
		
return retorno;
	}

}
