package br.controler;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.dao.CadastroDao;
import br.dao.DadosUsuarioLogado;
import br.modelo.DadosCadastro;

@Controller
public class ConfiguracaoDeConta {
	
@RequestMapping("configuracaodeconta")
	public ModelAndView contato(HttpSession session){
		System.out.println("CHAMOU CONFIGURAÇÃO DE CONTA...");
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		
		ModelAndView mv  = new ModelAndView("configuracao-de-conta");
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		return mv;
	}
	
@RequestMapping("atualizaConta")
	public String confconta(DadosCadastro c,HttpSession session){
		System.out.println("CHAMOU atualizaConta com novos dados...\n");
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			return "forward:logout";
		}

		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		c.setCnpj(d.getCnpj());
		
		System.out.println("Nome Fantasia: "+c.getNome());
		System.out.println("Endereço: "+c.getEndereço());
		System.out.println("Cidade: "+c.getCidade());
		System.out.println("E-mail: "+c.getEmail());
		System.out.println("CNPJ: "+c.getCnpj());
		System.out.println("Responsável: "+c.getResponsave());
		System.out.println("Senha: "+c.getSennha());
		
		if(new CadastroDao().AtualizaConta(c)){
			System.out.println("Atualizou conta, LOGOUT...");
			return "forward:logout?atualiza=true";
		}else{
			System.out.println("Atualizou conta, LOGOUT...");
			return "forward:atualizaERRO";
		}

	}
	
@RequestMapping("atualizaERRO")
	public ModelAndView ERRO(HttpSession session){
		System.out.println("...chamou o atualizaERRO....");
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		ModelAndView mv  = new ModelAndView("painelINICIAL");
		mv.addObject("atualizaERRO", "nao foi possivel atualizar informações do usuario");
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		
		return mv;
	}
}
