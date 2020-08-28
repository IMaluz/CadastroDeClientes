package com.Luiza.cadastroCliente.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.Luiza.cadastroCliente.functions.Data;
import com.Luiza.cadastroCliente.models.Cliente;
import com.Luiza.cadastroCliente.models.Contatos;
import com.Luiza.cadastroCliente.repository.ClienteRepository;
import com.Luiza.cadastroCliente.repository.ContatosRepository;

@Controller
public class ClienteController {

	@Autowired
	private ClienteRepository cr;
	
	@Autowired
	private ContatosRepository contr;
	
	@RequestMapping(value="/cadastrarCliente", method=RequestMethod.GET)
	public String form() {
		return "cliente/formCliente";
	}
	
	@RequestMapping(value="/cadastrarCliente", method=RequestMethod.POST)
	public String form(Cliente cliente) {
		cliente.setDataRegistro(Data.getDateTime());
		cr.save(cliente);
		
		return "redirect:/cadastrarCliente";
	}
	
	@RequestMapping("/clientes")
	public ModelAndView listaClientes() {
		ModelAndView mv = new ModelAndView("cliente/clientes");
		Iterable<Cliente> clientes = cr.findAll();
		mv.addObject("clientes", clientes);
		return mv;
	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalhesCliente(@PathVariable("codigo") long codigo) {
		Cliente cliente = cr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("cliente/detalhesCliente");
		mv.addObject("cliente", cliente);
		
		Iterable<Contatos> contatos = contr.findByCliente(cliente);
		mv.addObject("contatos", contatos);
		
		return mv;
	}
	
	@RequestMapping("/deletarCliente/{codigo}")
	public String deletarCliente(@PathVariable("codigo") long codigo){
		Cliente cliente = cr.findByCodigo(codigo);
		cr.delete(cliente);
		return "redirect:/clientes";
	}
	
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	public String detalhesCliente(@PathVariable("codigo") long codigo, Contatos contatos) {
		Cliente cliente = cr.findByCodigo(codigo);
		contatos.setCliente(cliente);
		contr.save(contatos);
		return "redirect:/{codigo}";
	}
	
	@RequestMapping(value="/editarCliente/{codigo}", method=RequestMethod.GET)
	public ModelAndView editarCliente(@PathVariable("codigo") long codigo) {
		Cliente cliente = cr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("cliente/editarCliente");
		mv.addObject("cliente", cliente);
		System.out.println(cliente);
		return mv;
	}
	
	@RequestMapping(value="/editar-cliente", method=RequestMethod.POST)
	public String edit(Cliente cliente) {
		
		cr.save(cliente);
		
		return "redirect:/clientes";
	}
	
	@RequestMapping("/deletarContato/{rg}")
	public String deletarContato(@PathVariable("rg") String rg) {
		Contatos contatos = contr.findByRg(rg);
		contr.delete(contatos);
		
		Cliente cliente = contatos.getCliente();
		long codigoLong = cliente.getCodigo();
		String codigo = "" + codigoLong;
		
		return "redirect:/" + codigo;
	}
	

	@RequestMapping(value="/editarContato/{rg}", method=RequestMethod.GET)
	public ModelAndView editarContato(@PathVariable("rg") String rg) {
		Contatos contatos = contr.findByRg(rg);
		ModelAndView mv = new ModelAndView("cliente/editarContato");
		mv.addObject("contatos", contatos);

		return mv;
	}
	
	
	@RequestMapping(value="/editar-contato", method=RequestMethod.POST)
	public String editarCont(Contatos contatos, Cliente clientes) {
		
		Cliente cliente = cr.findByCodigo(contatos.getCliente().getCodigo());
		contatos.setCliente(cliente);
		long codigoLong = cliente.getCodigo();
		String codigo = "" + codigoLong;
		
		contr.save(contatos);
		
		return "redirect:/" + codigo;
	}
	
}