package br.controler;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.dao.DadosConfQuartos;
import br.dao.DadosUsuarioLogado;
import br.modelo.DadosCadastro;
import br.modelo.DadosQuartos;

@Controller
public class ConfiguracaoDeQuartos {
	
@RequestMapping("configuracaodequarto")
	public ModelAndView confquartos(HttpSession session){
		System.out.println("CHAMOU CONFIGURAÇÃO DE QUARTOS...");
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		
		ModelAndView mv  = new ModelAndView("configuracao-de-quarto");
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		mv.addObject("quartos", new DadosConfQuartos().quartos(d.getCnpj()));
		return mv;
	}
	
@RequestMapping("cadastroquarto")
	public ModelAndView cadastraquarto(DadosQuartos quarto,HttpSession session){
		System.out.println("...chamou CADASTRA QUARTO");
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		System.out.println("nome: "+quarto.getNome());
		
		ModelAndView mv  = new ModelAndView("configuracao-de-quarto");
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		if(new DadosConfQuartos().adicionaQuarto(d.getCnpj(),quarto)){
			mv.addObject("notificacaoOK", "QUARTO CADASTRADO");
		}else{
			mv.addObject("notificacaoERRO", "OCORREU ALGUM ERRO AO CADASTRAR O QUARTO!");
		}
		
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		mv.addObject("quartos", new DadosConfQuartos().quartos(d.getCnpj()));
		return mv;
	}
	
@RequestMapping("buscaquarto")
	public ModelAndView buscaquarto(DadosQuartos quarto,HttpSession session){
		System.out.println("...chamou BUSCA QUARTO");
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		System.out.println("string a buscar: "+quarto.getNome());
		
		ModelAndView mv  = new ModelAndView("configuracao-de-quarto");
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		
		mv.addObject("quartos", new DadosConfQuartos().buscaQuartos(d.getCnpj(),quarto));
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		
		return mv;
	}
	
@RequestMapping("verquarto")
	public ModelAndView buscahospede2(DadosQuartos quarto,HttpSession session){
		System.out.println("...chamou VER QUARTO");
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		System.out.println("CODIGO: "+quarto.getCodigo());
		
		ModelAndView mv  = new ModelAndView("configuracao-de-quarto");
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		
		mv.addObject("ver", new DadosConfQuartos().buscaQuarto(d.getCnpj(),quarto));
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		
		return mv;
	}
	
@RequestMapping("atualizaquarto")
	public ModelAndView atualizaquarto(DadosQuartos quarto,HttpSession session){
		System.out.println("...chamou ATUALIZA QUARTO");
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		System.out.println("codigo: "+quarto.getCodigo());
		
		ModelAndView mv  = new ModelAndView("configuracao-de-quarto");
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		if(new DadosConfQuartos().atualizaQuarto(d.getCnpj(),quarto)){
			mv.addObject("notificacaoOK", "QUARTO ATUALIZADO");
		}else{
			mv.addObject("notificacaoERRO", "OCORREU ALGUM ERRO AO ATUALIZAR O QUARTO!");
		}
		
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		mv.addObject("quartos", new DadosConfQuartos().quartos(d.getCnpj()));
		
		return mv;
	}
	
@RequestMapping("exluirquarto")
	public ModelAndView excluiquarto(DadosQuartos quarto,HttpSession session){
		System.out.println("...chamou EXCLUIR QUARTO");
		
		if(session.getAttribute("usuarioLogado") == null){
			System.out.println("sessão nula, retorna para página inicial...");
			ModelAndView mv  = new ModelAndView("../../index");
			return mv;
		}
		
		System.out.println("codigo: "+quarto.getCodigo());
		
		ModelAndView mv  = new ModelAndView("configuracao-de-quarto");
		
		DadosCadastro d = (DadosCadastro) session.getAttribute("usuarioLogado");
		if(new DadosConfQuartos().excluiQuarto(d.getCnpj(),quarto)){
			mv.addObject("notificacaoOK", "QUARTO EXCLUIDO");
		}else{
			mv.addObject("notificacaoERRO", "OCORREU ALGUM ERRO AO DELETAR O QUARTO!");
		}
		
		mv.addObject("numeros", new DadosUsuarioLogado().numeros(d.getCnpj()));
		mv.addObject("quartos", new DadosConfQuartos().quartos(d.getCnpj()));
		
		return mv;
	}
}

