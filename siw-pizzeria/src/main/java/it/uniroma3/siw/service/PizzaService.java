package it.uniroma3.siw.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Pizza;
import it.uniroma3.siw.repository.PizzaRepository;

@Service
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
	  
	  public void delete(Pizza pizza) {
		  pizzaRepository.delete(pizza);
	  }
	  
	  public Pizza findByNomeIgnoreCase(String nome) {
		  return this.pizzaRepository.findByNomeIgnoreCase(nome);
	  }
}
