package br.controler;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.dao.DadosUsuarioLogado;
import br.dao.Logar;
import br.modelo.DadosCadastro;

@Controller
public class Login {
	
@RequestMapping("logar")
	public String efetuarLogin(DadosCadastro log, HttpSession session){
		if(log.getCnpj() == null){
			System.out.println("CNPJ NULL");
			return "forward:logout";
		}
		
		System.out.println("CHAMOU LOGAR...");
		
		System.out.println("CNPJ: "+log.getCnpj());
		System.out.println("SENHA: "+log.getSennha());
		
		if(new Logar().logar(log)){
			session.setAttribute("usuarioLogado",new DadosUsuarioLogado().dados(log));
			
			return "forward:home";
		}else{
			return "forward:logERRO";
		}
	}
	
@RequestMapping("logout")
	public String logout(HttpSession session){
		System.out.println("CHAMOU LOGOUT... session.invalidate()");
		session.invalidate();
		System.out.println("invalidou a sessao");
		return "../../index";
	}
	
	@RequestMapping("logERRO")
	public ModelAndView logERRO(){
		System.out.println("...chamou o logERRO....");
		
		ModelAndView mv  = new ModelAndView("../../index");
		mv.addObject("logERRO", "erro ao logar");
		
		return mv;
	}
	
@RequestMapping("home")
	public ModelAndView logOK(HttpSession session){
		
		System.out.print("chamou home");
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		System.out.println("...chamou o logOK.... CNPJ RECEBIDO: "+d.getCnpj());
		ModelAndView mv  = new ModelAndView("painelINICIAL");
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		
		return mv;
	}

}
