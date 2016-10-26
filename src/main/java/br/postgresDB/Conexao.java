package br.postgresDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	
	public static void main(String args[]){
		//Conexao.getConexao("00.000.000-0000-00");
		Conexao.getConexao();
		System.out.println("conexao aberta!");
	}
	
/*******************************************************************/
	
	public static Connection getConexao(String cnpj){
		Connection c = null;
		System.out.println("Recebeu:"+cnpj+"\nConecta com a base rf808_"+cnpj);
		try {
			Class.forName("org.postgresql.Driver");						//SE NECESSARIO ALTERAR O CAMINHO DO BANCO
			String url ="jdbc:postgresql://localhost:5432/rf808_"+cnpj;	//MANTER O "/rf808_"+cnpj;"
			String user = "postgres"; 									// ALTERAR USUARIO SE NECESSARIO
			String password = "2304"; 									// ALTERAR A SENHA
			c = DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) {
			System.out.println("ERRO EM CATCH NO CONECTAR COM O BANCO");
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ERRO EM CATCH NO CONECTAR COM O BANCO FIM");
		}
		
		return c;
	}
	
/*******************************************************************/	
//ESTE METODO SERVIRA PARA SE CONECTAR COM O BANCO PARA CRIR A BASE DE DADOS DO HOTEL QUE SE CADASTRAR 
	public static Connection getConexao(){
		Connection c = null;
		System.out.println("\n ...conecta com o banco...\n");
		try {
			Class.forName("org.postgresql.Driver");
			String url ="jdbc:postgresql://localhost:5432/";//SE NECESSARIO ALTERAR O CAMINHO DO BANCO	
			String user = "postgres"; 						// ALTERAR USUARIO SE NECESSARIO
			String password = "2304"; 						// ALTERAR A SENHA
			c = DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}

/*******************************************************************/	
}
