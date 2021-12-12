package com.springapp.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springapp.projeto.model.Evento;
import com.springapp.projeto.repository.EventoRepository;

@Controller
public class EventoController {

	@Autowired
	private EventoRepository er;
	
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.GET)
	public String form() {
		return "paginas/formEvento";
	}
	
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.POST)
	public String form(Evento evento) {
		
		// cadastrar evento
		er.save(evento);
		
		return "redirect:/";
		
	}
	
	@GetMapping
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Evento> eventos = er.findAll();
		mv.addObject("eventos",eventos);
		return mv;
	}
	
	@RequestMapping(value="/alterarEvento/{codigoEvento}", method=RequestMethod.GET)
	public ModelAndView alterarEvento(@PathVariable("codigoEvento") long codigoEvento) {
		Evento evento = er.findByCodigoEvento(codigoEvento);
		ModelAndView mv = new ModelAndView("paginas/alterarEvento");
		mv.addObject("evento", evento);
		return mv;
	}
	
	@RequestMapping(value="/alterarEvento/{codigoEvento}", method=RequestMethod.POST)
	public String alterarEventoPost(@PathVariable("codigoEvento") long codigoEvento, Evento evento){
		er.save(evento);
		return "redirect:/";
		}
	
	@RequestMapping("/deletar")
	public String deletarEvento(long codigoEvento) {
		Evento evento = er.findByCodigoEvento(codigoEvento);
		er.delete(evento);
		return "redirect:/";
	}
}
