package br.controler;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.dao.DadosCheckOutSpan;
import br.dao.DadosUsuarioLogado;
import br.modelo.DadosCadastro;
import br.modelo.DadosHospedagem;

@Controller
public class checkout {
	
@RequestMapping("spancheckout")
	public ModelAndView vercheckouts(HttpSession session){
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		System.out.println("...chamou VER CHECKOUTs DE HOJE");
		
		ModelAndView mv  = new ModelAndView("checkout");
		
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		mv.addObject("xout", new DadosCheckOutSpan().busca(d.getCnpj()));
		
		return mv;
	}
	
@RequestMapping("spancheckoutbusca")
	public ModelAndView buscacheckouts(DadosHospedagem dados,HttpSession session){
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		System.out.println("...chamou BUSCAR CHECKOUTs DE HOJE");
		
		ModelAndView mv  = new ModelAndView("checkout");
		
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		mv.addObject("xout", new DadosCheckOutSpan().buscaPesquisa(d.getCnpj(),dados));
		
		return mv;
	}
	
@RequestMapping("fazercheckout")
	public ModelAndView fazercheckout(DadosHospedagem dados,HttpSession session){
		System.out.println("...chamou fazer CHECKOUT");
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		System.out.println("hospedagem: "+dados.getCodhospedagem());
		System.out.println("codquarto: "+dados.getCodquarto());
		
		ModelAndView mv  = new ModelAndView("checkout");
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		if(new DadosCheckOutSpan().realizacheckout(d.getCnpj(),dados)){
			mv.addObject("notificacaoOK", "CHECK-OUT REALIZADO COM SUCESSO!");
		}else{
			mv.addObject("notificacaoERRO", "OCORREU ALGUM ERRO AO REALIZAR O CHECK-OUT!");
		}
		
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		mv.addObject("xout", new DadosCheckOutSpan().busca(d.getCnpj()));
		
		return mv;
	}
}
