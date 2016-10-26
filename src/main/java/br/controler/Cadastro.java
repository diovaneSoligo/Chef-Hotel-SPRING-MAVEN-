package br.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import br.dao.CadastroDao;
import br.modelo.DadosCadastro;

@Controller
public class Cadastro {
	
@RequestMapping("cadastro")
	public String cadastro(DadosCadastro c){
		System.out.println("Entrou em cadastro...");
		
		System.out.println("Nome Fantasia: "+c.getNome());
		System.out.println("CNPJ: "+c.getCnpj());
		System.out.println("Endereço: "+c.getEndereço());
		System.out.println("Cidade: "+c.getCidade());
		System.out.println("E-mail: "+c.getEmail());
		System.out.println("Responsável: "+c.getResponsave());
		System.out.println("Senha: "+c.getSennha());
		
		boolean retorno = new CadastroDao().cadastrar(c);

		if(retorno){
			return "forward:retornaOK";
		}else{
			return "forward:retornaERRO";
		}
	}
	
@RequestMapping("retornaOK")
	public ModelAndView OK(){
		System.out.println("...chamou o retornaOK....");
		
		ModelAndView mv  = new ModelAndView("../../cadastro");
		mv.addObject("cadastroOK", "cadastrado");
		
		return mv;
	}
	
@RequestMapping("retornaERRO")
	public ModelAndView ERRO(){
		System.out.println("...chamou o retornaERRO....");
		
		ModelAndView mv  = new ModelAndView("../../cadastro");
		mv.addObject("cadastroERRO", "nao cadastrado");
		
		return mv;
	}
	
@RequestMapping("recupera")
	public String recupera(DadosCadastro c){
		System.out.println("entrou em recupera conta");
		
		System.out.println("CNPJ para recuperar conta: "+c.getCnpj());

		if(new CadastroDao().RecuperaConta(c)){
			return "forward:recuperaOK";
		}else{
			return "forward:recuperaERRO";
		}
		
	}
	
@RequestMapping("recuperaOK")
	public ModelAndView recuperaOK(){
		System.out.println("...chamou o recuperaOK....");
		
		ModelAndView mv  = new ModelAndView("../../index");
		mv.addObject("recuperaOK", "cadastrado");
		
		return mv;
	}
	
@RequestMapping("recuperaERRO")
	public ModelAndView recuperaERRO(){
		System.out.println("...chamou o recuperaERRO....");
		
		ModelAndView mv  = new ModelAndView("../../index");
		mv.addObject("recuperaERRO", "nao cadastrado");
		
		return mv;
	}
}
