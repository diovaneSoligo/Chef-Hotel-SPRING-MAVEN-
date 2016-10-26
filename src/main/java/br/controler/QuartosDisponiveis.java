package br.controler;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.dao.DadosHospedados;
import br.dao.DadosUsuarioLogado;
import br.modelo.DadosCadastro;

@Controller
public class QuartosDisponiveis {
	
@RequestMapping("quartosdisponiveis")
	public ModelAndView quartosdisponiveis(HttpSession session){
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		
		System.out.println("...chamou quartosdisponiveis");
		
		ModelAndView mv  = new ModelAndView("quartosdisponiveis");
		
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		mv.addObject("ver", new DadosHospedados().quartosDisponiveis(d.getCnpj()));
		
		return mv;
	}
}
