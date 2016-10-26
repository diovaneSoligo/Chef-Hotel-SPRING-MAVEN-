package br.controler;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.dao.DadosHospede;
import br.dao.DadosUsuarioLogado;
import br.modelo.DadosCadastro;
import br.modelo.DadosHospedes;

@Controller
public class VerAddHospede {
	
@RequestMapping("veraddhospedes")
	public ModelAndView verhospedes(HttpSession session){
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		System.out.println("...chamou VER HOSPEDES");
		
		ModelAndView mv  = new ModelAndView("hospedes");
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		mv.addObject("hospedes", new DadosHospede().hospedes(d.getCnpj()));
		
		return mv;
	}
	
@RequestMapping("cadastrohospede")
	public ModelAndView cadastrahospede(DadosHospedes hospede,HttpSession session){
		System.out.println("...chamou CADASTRA HOSPEDE");
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		System.out.println("nome: "+hospede.getNome());
		
		ModelAndView mv  = new ModelAndView("hospedes");
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		if(new DadosHospede().adicionaHospede(d.getCnpj(),hospede)){
			mv.addObject("notificacaoOK", "HÓSPEDE CADASTRADO");
		}else{
			mv.addObject("notificacaoERRO", "OCORREU ALGUM ERRO AO CADASTRAR O HÓSPEDE!");
		}
		
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		mv.addObject("hospedes", new DadosHospede().hospedes(d.getCnpj()));
		
		return mv;
	}
	
@RequestMapping("buscahospede")
	public ModelAndView buscahospede(DadosHospedes hospede,HttpSession session){
		System.out.println("...chamou BUSCA HOSPEDE");
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		System.out.println("string a buscar: "+hospede.getNome());
		
		ModelAndView mv  = new ModelAndView("hospedes");
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		mv.addObject("hospedes", new DadosHospede().BuscaHospede(d.getCnpj(),hospede));
		
		return mv;
	}
	
@RequestMapping("verhospede")
	public ModelAndView buscahospede2(DadosHospedes hospede,HttpSession session){
		System.out.println("...chamou VER HOSPEDE");
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		System.out.println("CODIGO: "+hospede.getCodigo());
		
		ModelAndView mv  = new ModelAndView("hospedes");
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		mv.addObject("ver", new DadosHospede().VerHospede(d.getCnpj(),hospede));
		
		return mv;
	}
	
@RequestMapping("atualizahospede")
	public ModelAndView atualizahospede(DadosHospedes hospede,HttpSession session){
		System.out.println("...chamou ATUALIZA HOSPEDE");
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		System.out.println("nome: "+hospede.getNome());
		
		ModelAndView mv  = new ModelAndView("hospedes");
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		if(new DadosHospede().atualizaHospede(d.getCnpj(),hospede)){
			mv.addObject("notificacaoOK", "HÓSPEDE ATUALIZADO");
		}else{
			mv.addObject("notificacaoERRO", "OCORREU ALGUM ERRO AO ATUALIZAR O HÓSPEDE!");
		}
		
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		mv.addObject("hospedes", new DadosHospede().hospedes(d.getCnpj()));
		
		return mv;
	}
	
@RequestMapping("exluirhospede")
	public ModelAndView excluihospede(DadosHospedes hospede,HttpSession session){
		System.out.println("...chamou EXCLUI HOSPEDE");
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		System.out.println("codigo para excluir: "+hospede.getCodigo());
		
		ModelAndView mv  = new ModelAndView("hospedes");
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		if(new DadosHospede().exluiHospede(d.getCnpj(),hospede)){
			mv.addObject("notificacaoOK", "HÓSPEDE DELETADO");
		}else{
			mv.addObject("notificacaoERRO", "OCORREU ALGUM ERRO AO DELETAR O HÓSPEDE!");
		}
		
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		mv.addObject("hospedes", new DadosHospede().hospedes(d.getCnpj()));
		
		return mv;
	}
}
