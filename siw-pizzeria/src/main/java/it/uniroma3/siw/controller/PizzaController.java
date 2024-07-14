package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Pizza;
import it.uniroma3.siw.service.PizzaService;

@Controller
public class PizzaController {

	private PizzaService pizzaService;

	@GetMapping("/formNewPizza")
	public String formNewPizza(Model model) {
		model.addAttribute("pizza", new Pizza());
		return "formNewPizza.html";
	}

	@PostMapping("/pizza")
	public String newPizza(@ModelAttribute("pizza") Pizza pizza, Model model) {
		this.pizzaService.save(pizza);
		model.addAttribute("pizza", pizza);
		return "redirect:pizza/" + pizza.getId();
	}


	@GetMapping("/formSearchPizza")
	public String formSearchPizza() {
	  return "formSearchPizza.html";
	}

	@PostMapping("/searchRicetta")
	public String searchRicetta(Model model, @RequestParam String nome) {
		model.addAttribute("pizza", this.pizzaService.findByNome(nome));
		return "pizza.html";
	}


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
