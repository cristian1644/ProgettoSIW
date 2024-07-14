package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.repository.IngredienteRepository;
import it.uniroma3.siw.service.IngredienteService;
//import it.uniroma3.siw.validator.IngredienteValidator;

@Controller
public class IngredienteController {

  @Autowired IngredienteRepository ingredienteRepository;
  @Autowired IngredienteService ingredienteService;
  
  @GetMapping("/ingrediente/{id}")
  public String getIngrediente(@PathVariable("id") Long id,Model model) {
    model.addAttribute("ingrediente",this.ingredienteService.findById(id));
    return "ingrediente.html";
  }
  
  @GetMapping("/ingredienti")
  public String showIngredienti(Model model) {
    model.addAttribute("ingredienti",this.ingredienteService.findAll());
    return "ingredienti.html";
  }
  

}
