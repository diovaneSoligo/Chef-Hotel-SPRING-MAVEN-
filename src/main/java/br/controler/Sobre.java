package br.controler;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.dao.DadosUsuarioLogado;
import br.modelo.DadosCadastro;

@Controller
public class Sobre {
	
@RequestMapping("sobre")
	public ModelAndView sobre(HttpSession session){
		System.out.println("CHAMOU SOBRE...");
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sess�o nula, retorna para p�gina inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		
		ModelAndView mv  = new ModelAndView("sobre");
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		
		return mv;
	}
}
