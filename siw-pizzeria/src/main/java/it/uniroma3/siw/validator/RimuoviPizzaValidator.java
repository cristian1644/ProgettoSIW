package it.uniroma3.siw.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Pizza;
import it.uniroma3.siw.repository.PizzaRepository;

@Component
public class RimuoviPizzaValidator implements Validator{
	
	@Autowired PizzaRepository pizzaRepository;
	
	 @Override
	  public void validate(Object o, Errors errors) {
	    Pizza pizza = (Pizza)o;
	    if ( !(pizza.getNome()!=null && pizzaRepository.existsByNomeIgnoreCase(pizza.getNome())) ) {
	      errors.reject("pizza.notExists");
	    }
	  }

	 @Override
	    public boolean supports(Class<?> aClass) {
	      return Pizza.class.equals(aClass);
	    }
}
