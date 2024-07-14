package it.uniroma3.siw.service;


import org.springframework.beans.factory.annotation.Autowired;

import it.uniroma3.siw.model.Pizza;
import it.uniroma3.siw.repository.PizzaRepository;

public class PizzaService {

	@Autowired
	private PizzaRepository pizzaRepository;
	
	public Pizza findById(Long id) {
	    return pizzaRepository.findById(id).get();
	  }
	  
	  public Pizza findByNome(String nome) {
	    // TODO Auto-generated method stub
	    return pizzaRepository.findByNome(nome);
	  }
	  
	  public Iterable<Pizza> findAll() {
	    return pizzaRepository.findAll();
	  }
	  
	  public void save(Pizza pizza) {
	    // TODO Auto-generated method stub
	    pizzaRepository.save(pizza);
	  }
}
