package br.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import br.modelo.DadosCadastro;
import br.postgresDB.Conexao;



public class CadastroDao {

	public boolean cadastrar(DadosCadastro c) {
		System.out.println("\nDentro do cadastra no CadastroDao\n");
		
		boolean retorno = false;
		
		System.out.println("VAI CRIAR A BASE DE DADOS");
		boolean ok = CriaBaseDeDados(c.getCnpj());
		
		 if(ok){
			 System.out.println("CRIOU A BASE DE DADOS, VAI CRIAR AS TABELAS E INSERIR DADOS");
			 ok=InsereDadosNaBase(c);
			 
			 if(ok){
				 System.out.println("DADOS INSERIDOS...");
				 retorno = true;
			 }else{
				 System.out.println("ERRO AO INSERIR DADOS NA TABELA...");
			 }
		 }else{
			 System.out.println("PROBLEMAS AO CRIAR A BASE DE DADOS!!! retornando false");
			 return retorno; 
		 }
	
		 if(retorno){//Tenta mandar e-mail de confirmação do cadastro
			boolean e = mandaEmail(c);
			if(e){System.out.println("EMAIL ENVIADO...");}
			else{System.out.println("EMAIL DE CONFIRMAÇÃO NÃO ENVIADO (problema de rede!)...");}
		 }
		 
		 return retorno;
	}
	
	
	
	public boolean CriaBaseDeDados(String cnpj) {
		Connection c = null;
		PreparedStatement stmt = null;
		boolean retorno = false;
		try {
			System.out.println("\nVAI conectar\n");
			c = Conexao.getConexao();
			System.out.println("\nVOLTOU de conectar\n");
			String sql = "";
			
				System.out.println("......... vai criar o banco rf808_"+cnpj);
				
				sql = "create database \"rf808_"+cnpj+"\"; ";
				
				stmt = c.prepareStatement(sql);
				
				System.out.println(".........vai executar, criar banco: "+sql);
				
				stmt.execute();		
			
				System.out.println(".........  banco "+sql+" CRIADO..) /retornando...........");
				
				stmt.close();
				
				retorno = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("......... ERRO EM CriaBaseDeDados(String cnpj).............");
			return retorno;
		}	
		
		return retorno;
	}

	
	public boolean InsereDadosNaBase(DadosCadastro u) {
		Connection c = null;
		PreparedStatement stmt = null;
		boolean retorno = false;
		try {
			
			c = Conexao.getConexao(u.getCnpj());//conecta na base de dados criada
			
			String sql = "";
			
				System.out.println("......... vai criar a tabela no banco rf808_"+u.getCnpj());
				
				sql = "create table hotel("
							+"fantasia varchar (100) not null,"
							+"cnpj varchar (20) not null,"
							+"endereco varchar (100) not null,"
							+"cidade varchar (50) not null,"
							+"email varchar (100) not null,"
							+"responsavel varchar (50) not null,"
							+"senha varchar (50) not null,"
							+"primary key (cnpj)"
						+");"
						+"create table lembretes("
							+"codlembrete serial not null,"
							+"titulo varchar(50) not null,"
							+"descricaolembrete varchar(500) not null,"
							+"criado date,"
							+"primary key (codlembrete)"
						+");"
						+"create table quarto("
							+"codquarto serial not null,"
							+"tituloquarto varchar(50) not null,"
							+"capacidade int not null,"
							+"disponivel int,"
							+"diaria numeric(12,2),"
							+"descricao varchar(500),"
							+"unique (tituloquarto),"
							+"primary key (codquarto)"
						+");"
						+"create table hospede("
							+"codhospede serial not null,"
							+"nome varchar(100) not null,"
							+"cpf varchar(15) not null,"
							+"rg varchar(15) not null,"
							+"nascimento date not null,"
							+"email varchar(50),"
							+"fone varchar(50),"
							+"foneextra varchar(50),"
							+"primary key(codhospede),"
							+"unique (cpf),"
							+"unique (rg)"
						+");"
						+"create table hospedagem("
							+"codhospedagem serial not null,"
							+"codhospede int not null,"
							+"foreign key (codhospede) references hospede (codhospede),"
							+"codquarto int not null,"
							+"foreign key (codquarto) references quarto (codquarto),"
							+"numeroacompanhantes int,"
							+"entrada date not null,"
							+"saida date not null,"
							+"xin int not null,"
							+"xout int not null,"
							+"primary key (codhospedagem)"
						+");"
						+ "";
				
				stmt = c.prepareStatement(sql);
				
				System.out.println(".........vai executar, criar tabela");
				
				stmt.execute();		
			
				System.out.println(".........  Tabela Criada ok\n vai inserir dados...........");
				
				sql = "insert into hotel"
						+ "(fantasia,cnpj,endereco,cidade,email,responsavel,senha)"
						+ "values (?,?,?,?,?,?,?);";
				
				stmt = c.prepareStatement(sql);	
				stmt.setString(1, u.getNome());
				stmt.setString(2, u.getCnpj());
				stmt.setString(3, u.getEndereço());
				stmt.setString(4, u.getCidade());
				stmt.setString(5, u.getEmail());
				stmt.setString(6, u.getResponsave());
				stmt.setString(7, u.getSennha());
				
				System.out.println(".........vai executar, inserir dados");
				
				stmt.execute();
				
				System.out.println(".........Dados inseridos OK, stmt.close e voltando....");
				
				stmt.close();
				
				retorno = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("......... ERRO EM CriaBaseDeDados(String cnpj.............");
			return retorno;
		}	
		
		return retorno;
	}

	public boolean mandaEmail(DadosCadastro u) {
		
		Properties props = new Properties();
        /** Parâmetros de conexão com servidor Gmail */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.starttls.enable", "true"); 
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.fallback", "false");  
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        boolean envio=false;
        
        Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                         protected PasswordAuthentication getPasswordAuthentication()
                         {
                             return new PasswordAuthentication("suporte.chef.hotel@gmail.com", "123@321@");
                         }
                    });

        /** Ativa Debug para sessão */
       session.setDebug(true);

        try {

              Message message = new MimeMessage(session);
              message.setFrom(new InternetAddress("suporte.chef.hotel@gmail.com")); //Remetente

              Address[] toUser = InternetAddress //Destinatário(s)
                         .parse(u.getEmail());  

              message.setRecipients(Message.RecipientType.TO, toUser);
              message.setSubject("BEM VINDO AO CHEFHOTEL.COM");//Assunto
              String texto = " Olá "+u.getNome()+","
              		+ "\n\n Bem vindo ao ChefHotel.com, acesse o sistema com os dados:"
              		+ "\n\n User(CNPJ):"+u.getCnpj()+""
              		+ "\n Password:"+u.getSennha()+"\n\n Responsável cadastrado: "+u.getResponsave()+""
              		+ "\n\nPara poder ver seus dados cadastrados e poder altera-los, "
              		+ "acesse o sistema. Caso esqueça sua senha, basta clicar em esquici "
              		+ "minha senha e então basta inserir o e-mail cadastrado, que você receberá suas informações para acesso."
              		+ "\n\n Obrigado!\n\n\nE-mail programado, favor, não responda!!\n.......";
              
              message.setText(texto);
              
              
              System.out.println("....Vai enviar email para\n"+u.getEmail()+"\ntexto\n"+texto);
              
              /**Método para enviar a mensagem criada*/
              
          // Transport.send(message);    //metodo para mandar o email
              Transport.send(message);
              System.out.println("email enviado!!!");
              
              envio=true;

         } catch (Exception e) {
        	 e.printStackTrace();
        	 System.out.println("ERRO catch em enviar email, nao conseguiur se conectar com o gmail");
              return envio;
        }
        
        
		return envio;
	}



	public boolean RecuperaConta(DadosCadastro u) {
		DadosCadastro dados = new DadosCadastro();
		boolean retorno = false;
		try{
			
			System.out.println("vai fazer a conexão com o banco para buscar os dados de usuario");
			
			Connection c = Conexao.getConexao(u.getCnpj());//Faz conexão com a base de dados
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
			System.out.println("tudo OK ... vai tenta mandar e-mail");
			retorno =true;
			stmt.close();
		}catch(Exception e){
			System.out.println("CATCH em public Object dados(DadosCadastro log) em DadosUsuarioLogado.java ...");
			e.printStackTrace();
		}
		
		if(retorno){//Tenta mandar e-mail de confirmação do cadastro
			boolean e = mandaEmailRecupera(dados);
			if(e){System.out.println("EMAIL RECUPERAÇÃO ENVIADO...");}
			else{System.out.println("EMAIL DE RECUPERAÇÃO NÃO ENVIADO (problema de rede!)...");}
		 }
		
		return retorno;
	}

	public boolean mandaEmailRecupera(DadosCadastro u) {
	
			Properties props = new Properties();
		    /** Parâmetros de conexão com servidor Gmail */
		    props.put("mail.smtp.host", "smtp.gmail.com");
		    props.put("mail.smtp.socketFactory.port", "465");
		    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.port", "465");
		    props.put("mail.smtp.host", "smtp.gmail.com");
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.port", "465");
		    props.put("mail.smtp.starttls.enable", "true"); 
		    props.put("mail.smtp.socketFactory.port", "465");
		    props.put("mail.smtp.socketFactory.fallback", "false");  
		    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		
		    boolean envio=false;
		    
		    Session session = Session.getDefaultInstance(props,
		                new javax.mail.Authenticator() {
		                     protected PasswordAuthentication getPasswordAuthentication()
		                     {
		                         return new PasswordAuthentication("suporte.chef.hotel@gmail.com", "123@321@");
		                     }
		                });
		
		    /** Ativa Debug para sessão */
		   session.setDebug(true);
		
		    try {
		
		          Message message = new MimeMessage(session);
		          message.setFrom(new InternetAddress("suporte.chef.hotel@gmail.com")); //Remetente
		
		          Address[] toUser = InternetAddress //Destinatário(s)
		                     .parse(u.getEmail());  
		
		          message.setRecipients(Message.RecipientType.TO, toUser);
		          message.setSubject("RECUPERAÇÃO DE CONTA CHEFHOTEL.COM");//Assunto
		          String texto = " Olá "+u.getNome()+","
		          		+ "\n\n Acesse o sistema com os dados:"
		          		+ "\n\n User(CNPJ):"+u.getCnpj()+""
		          		+ "\n Password:"+u.getSennha()+"\n\n Responsável cadastrado: "+u.getResponsave()+""
		          		+ "\n\nPara poder ver seus dados cadastrados e poder altera-los, "
		          		+ "acesse o sistema. (É RECOMENDADO A ALTERAÇÃO DA SENHA)"
		          		+ "\n\n Obrigado!\n\n\nE-mail programado, favor, não responda!!\n.......";
		          
		          message.setText(texto);
		          
		          
		          System.out.println("....Vai enviar email para\n"+u.getEmail()+"\ntexto\n"+texto);
		          
		          /**Método para enviar a mensagem criada*/
		          
		      // Transport.send(message);    //metodo para mandar o email
		          Transport.send(message);
		          System.out.println("email enviado!!!");
		          
		          envio=true;
		
		     } catch (Exception e) {
		    	 e.printStackTrace();
		    	 System.out.println("ERRO catch em enviar email, nao conseguiur se conectar com o gmail");
		          return envio;
		    }
		    
		    
			return envio;
	}

		public boolean AtualizaConta(DadosCadastro u) {
			Connection c = null;
			PreparedStatement stmt = null;
			boolean retorno = false;
			try {
				
				c = Conexao.getConexao(u.getCnpj());//conecta na base de dados criada
				
				String sql = "";
				
				sql = "UPDATE hotel  SET fantasia = ? "
									+", endereco = ?"	 
									+"	, cidade = ? "
									+"	, email = ? "
									+"	, responsavel = ? "
									+"	, senha = ? ;";
				
				stmt = c.prepareStatement(sql);	
				stmt.setString(1, u.getNome());
				stmt.setString(2, u.getEndereço());
				stmt.setString(3, u.getCidade());
				stmt.setString(4, u.getEmail());
				stmt.setString(5, u.getResponsave());
				stmt.setString(6, u.getSennha());
				
				System.out.println(".........vai executar, atualizar dados");
				
				stmt.execute();
				
				System.out.println(".........Dados atualizados OK, stmt.close e voltando....");
				
				stmt.close();
				
				retorno = true;
			
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("......... ERRO EM atualiza dados()");
				return retorno;
			}	
		
		return retorno;
		}

}

