package br.controler;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.dao.DadosCheckInSpan;
import br.dao.DadosUsuarioLogado;
import br.modelo.DadosCadastro;
import br.modelo.DadosHospedagem;

@Controller
public class checkin {
@RequestMapping("spancheckin")
	public ModelAndView vercheckins(HttpSession session){
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		System.out.println("...chamou VER CHECKINs DE HOJE");
		
		ModelAndView mv  = new ModelAndView("checkin");
		
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		mv.addObject("xin", new DadosCheckInSpan().busca(d.getCnpj()));
		
		return mv;
	}
	
@RequestMapping("spancheckinbusca")
	public ModelAndView buscacheckins(DadosHospedagem dados,HttpSession session){
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		System.out.println("...chamou BUSCAR CHECKINs DE HOJE");
		
		ModelAndView mv  = new ModelAndView("checkin");
		
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		mv.addObject("xin", new DadosCheckInSpan().buscaPesquisa(d.getCnpj(),dados));
		
		return mv;
	}
	
@RequestMapping("fazercheckin")
	public ModelAndView fazercheckin(DadosHospedagem dados,HttpSession session){
		System.out.println("...chamou ADICIONAR LEMBRETES");
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		System.out.println("hospedagem: "+dados.getCodhospedagem());
		System.out.println("codquarto: "+dados.getCodquarto());
		
		ModelAndView mv  = new ModelAndView("checkin");
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		if(new DadosCheckInSpan().realizacheckin(d.getCnpj(),dados)){
			mv.addObject("notificacaoOK", "CHECK-IN REALIZADO COM SUCESSO!");
		}else{
			mv.addObject("notificacaoERRO", "OCORREU ALGUM ERRO AO REALIZAR O CHECK-IN!");
		}
		
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		mv.addObject("xin", new DadosCheckInSpan().busca(d.getCnpj()));
		
		return mv;
	}
}
