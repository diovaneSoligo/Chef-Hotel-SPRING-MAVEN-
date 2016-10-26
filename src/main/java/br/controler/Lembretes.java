package br.controler;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.dao.DadosLembretes;
import br.dao.DadosUsuarioLogado;
import br.modelo.DadosCadastro;
import br.modelo.InfoLembretes;

@Controller
public class Lembretes {
	
@RequestMapping("lembretes")
	public ModelAndView lembretes(HttpSession session){
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		System.out.println("...chamou LEMBRETES");
		ModelAndView mv  = new ModelAndView("lembretes");
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		mv.addObject("lembretes", new DadosLembretes().lembretes(d.getCnpj()));
		
		return mv;
	}
	
@RequestMapping("adicionalembrete")
	public ModelAndView adicionaLembrete(InfoLembretes lembrete,HttpSession session){
		System.out.println("...chamou ADICIONAR LEMBRETES");
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		System.out.println("Título: "+lembrete.getTitulo());
		System.out.println("Descrição: "+lembrete.getDescricao());
		
		ModelAndView mv  = new ModelAndView("lembretes");
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		if(new DadosLembretes().adicionaLembrete(d.getCnpj(),lembrete)){
			mv.addObject("notificacaoOK", "SEU LEMBRETE FOI ADICIONADO A LISTA!");
		}else{
			mv.addObject("notificacaoERRO", "OCORREU ALGUM ERRO AO INSERIR SEU LEMBRETE!");
		}
		
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		mv.addObject("lembretes", new DadosLembretes().lembretes(d.getCnpj()));
		
		return mv;
	}
	
@RequestMapping("apagarlembrete")
	public ModelAndView apagaLembrete(InfoLembretes lembrete,HttpSession session){
		System.out.println("...chamou APAGAR LEMBRETE");
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		System.out.println("Código: "+lembrete.getCodigo());
		
		ModelAndView mv  = new ModelAndView("lembretes");
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		if(new DadosLembretes().apagaLembrete(d.getCnpj(),lembrete)){
			mv.addObject("notificacaoOK", "LEMBRETE DELETADO!");
		}else{
			mv.addObject("notificacaoERRO", "OCORREU ALGUM ERRO AO DELETAR SEU LEMBRETE!");
		}
		
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		mv.addObject("lembretes", new DadosLembretes().lembretes(d.getCnpj()));
		
		return mv;
	}
	
@RequestMapping("editarlembrete")
	public ModelAndView editarLembrete(InfoLembretes lembrete,HttpSession session){
		System.out.println("...chamou EDITAR LEMBRETE");
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		System.out.println("Código: "+lembrete.getCodigo());
		
		ModelAndView mv  = new ModelAndView("lembretes");
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		
		mv.addObject("lembretealterar", new DadosLembretes().buscaLembrete(d.getCnpj(),lembrete));
		
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		
		return mv;
	}
	
@RequestMapping("atualizalembrete")
	public ModelAndView atualizalembrete(InfoLembretes lembrete,HttpSession session){
		System.out.println("...chamou ATUALIZA LEMBRETE");
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		System.out.println("Código: "+lembrete.getCodigo());
		
		ModelAndView mv  = new ModelAndView("lembretes");
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		if(new DadosLembretes().atualizaLembrete(d.getCnpj(),lembrete)){
			mv.addObject("notificacaoOK", "LEMBRETE ALTERADO!");
		}else{
			mv.addObject("notificacaoERRO", "OCORREU ALGUM ERRO AO ALTERAR SEU LEMBRETE!");
		}
		
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		mv.addObject("lembretes", new DadosLembretes().lembretes(d.getCnpj()));
		
		return mv;
	}
}
