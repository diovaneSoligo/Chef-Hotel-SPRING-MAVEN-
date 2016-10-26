package br.controler;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.dao.DadosCheckOutSpan;
import br.dao.DadosHospedados;
import br.dao.DadosHospede;
import br.dao.DadosUsuarioLogado;
import br.modelo.DadosCadastro;
import br.modelo.DadosHospedagem;

@Controller
public class Hospedados {
	
@RequestMapping("hospedados")
	public ModelAndView hospedados(HttpSession session){
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		
		System.out.println("...chamou HOSPEDADOS");
		
		ModelAndView mv  = new ModelAndView("hospedados");
		
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		mv.addObject("hospededados", new DadosHospedados().hospedados(d.getCnpj()));
		
		return mv;
	}
	
@RequestMapping("hospedadosbusca")
	public ModelAndView hospedadosbusca(DadosHospedagem dados,HttpSession session){
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		
		System.out.println("...chamou HOSPEDADOS");
		
		ModelAndView mv  = new ModelAndView("hospedados");
		
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		mv.addObject("hospededados", new DadosHospedados().hospedadosBusca(d.getCnpj(),dados));
		
		return mv;
	}
	
@RequestMapping("fazercheckoutanti")
	public ModelAndView fazercheckout(DadosHospedagem dados,HttpSession session){
		System.out.println("...chamou fazer CHECKOUT");
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		System.out.println("hospedagem: "+dados.getCodhospedagem());
		System.out.println("codquarto: "+dados.getCodquarto());
		
		ModelAndView mv  = new ModelAndView("hospedados");
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		if(new DadosCheckOutSpan().realizacheckout(d.getCnpj(),dados)){
			mv.addObject("notificacaoOK", "CHECK-OUT REALIZADO COM SUCESSO!");
		}else{
			mv.addObject("notificacaoERRO", "OCORREU ALGUM ERRO AO REALIZAR O CHECK-OUT!");
		}
		
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		mv.addObject("hospededados", new DadosHospedados().hospedados(d.getCnpj()));
		return mv;
	}
	
@RequestMapping("hospedar")
	public ModelAndView hospedar(HttpSession session){
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		
		System.out.println("...chamou hospedar");
		
		ModelAndView mv  = new ModelAndView("hospedados");
		
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		mv.addObject("ver", new DadosHospedados().quartosDisponiveis(d.getCnpj()));
		mv.addObject("ver2", new DadosHospede().hospedes(d.getCnpj()));
		
		return mv;
	}
	
@RequestMapping("hospedarok")
	public ModelAndView hospedarok(DadosHospedagem dados,HttpSession session){
		System.out.println("...chamou HOSPEDAR OK");
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		System.out.println("codhospede: "+dados.getCodhospede());
		System.out.println("codquarto: "+dados.getCodquarto());
		System.out.println("acompanhantes: "+dados.getNumeroacompanhanteshospedagem());
		System.out.println("saida: "+dados.getSaidahospedagem());
		
		ModelAndView mv  = new ModelAndView("hospedados");
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		if(new DadosHospedados().hospedar(d.getCnpj(),dados)){
			mv.addObject("notificacaoOK", "HOSPEDAGEM REALIZADA COM SUCESSO!");
		}else{
			mv.addObject("notificacaoERRO", "OCORREU ALGUM ERRO AO REALIZAR A HOSPEDAGEM!");
		}
		
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		mv.addObject("hospededados", new DadosHospedados().hospedados(d.getCnpj()));
		
		return mv;
	}
}
