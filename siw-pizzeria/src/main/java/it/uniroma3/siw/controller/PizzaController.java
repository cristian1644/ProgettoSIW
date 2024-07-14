package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.service.PizzaService;

@Controller
public class PizzaController {

	private PizzaService pizzaService;
	
	@GetMapping("/pizza/{id}")
	  public String getPizza(@PathVariable("id") Long id,Model model) {
	    model.addAttribute("pizza",this.pizzaService.findById(id));
	    return "pizza.html";
	  }
	  
	  @GetMapping("/pizze")
	  public String showPizze(Model model) {
	    model.addAttribute("pizze",this.pizzaService.findAll());
	    return "pizze.html";
	  }
}
