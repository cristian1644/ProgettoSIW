package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Pizza;
import it.uniroma3.siw.repository.PizzaRepository;
import it.uniroma3.siw.service.PizzaService;
import it.uniroma3.siw.validator.NewPizzaValidator;
import it.uniroma3.siw.validator.SearchPizzaValidator;
import jakarta.validation.Valid;

@Controller
public class PizzaController {

	@Autowired private PizzaService pizzaService;
	@Autowired private NewPizzaValidator newPizzaValidator;
	@Autowired private SearchPizzaValidator searchPizzaValidator;
	@Autowired private PizzaRepository pizzaRepository;

	@GetMapping("/formNewPizza")
	public String formNewPizza(Model model) {
		model.addAttribute("pizza", new Pizza());
		return "formNewPizza.html";
	}

	@PostMapping("/pizze")
	public String newPizza(@Valid @ModelAttribute("pizza") Pizza pizza,BindingResult bindingResult, Model model) {
		this.newPizzaValidator.validate(pizza,bindingResult);
	    if (!bindingResult.hasErrors()) {
	      this.pizzaService.save(pizza);
	      model.addAttribute("pizza", pizza);
	      return "redirect:pizza/" + pizza.getId();
	    } else {
	    	 bindingResult.rejectValue("nome", "pizza.duplicate");
	      return "formNewPizza.html";
	    }

	}
	
	 @ModelAttribute("searchPizza")
	    public Pizza createSearchPizzaModel() {
	        return new Pizza(); // Oggetto modello per il form di ricerca
	    }
	
	@GetMapping("/formSearchPizza")
	public String formSearchPizza(Model model) {
		model.addAttribute("pizza", new Pizza());
		return "formSearchPizza.html";
	}

	 @GetMapping("/")
	    public String index(Model model) {
	        model.addAttribute("searchPizza", new Pizza());
	        return "index"; 
	    }
	
	@PostMapping("/searchPizza") 
	public String searchPizza(@Valid @ModelAttribute("searchPizza") Pizza searchPizza,BindingResult bindingResult, Model model) {
		
		this.searchPizzaValidator.validate(searchPizza,bindingResult);
	    if (!bindingResult.hasErrors()) {
	    	 Pizza foundPizza = pizzaRepository.findByNome(searchPizza.getNome());
	    	 model.addAttribute("searchPizza", foundPizza);
	    	return "redirect:pizza/" + foundPizza.getId();
	    } else {
	    	 bindingResult.rejectValue("nome", "pizza.notExists");		
		return "index";
	    }
	}


	@GetMapping("/pizza/{id}")
	public String getPizza(@PathVariable("id") Long id,Model model) {
		model.addAttribute("pizza",this.pizzaService.findById(id));
		return "pizza.html";
	}

	@GetMapping("/pizze")
	public String showPizze(Model model) {
		model.addAttribute("pizze",this.pizzaService.findAll());
		model.addAttribute("pizza", new Pizza()); // Aggiungi l'oggetto pizza al modello
		return "pizze.html";
	}
}
