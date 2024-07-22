package it.uniroma3.siw.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Pizza;
import it.uniroma3.siw.repository.PizzaRepository;

@Component
public class NewPizzaValidator implements Validator {
  @Autowired
  private PizzaRepository pizzaRepository;

  @Override
  public void validate(Object o, Errors errors) {
    Pizza pizza = (Pizza)o;
    if ( (pizza.getNome()!=null && pizzaRepository.existsByNome(pizza.getNome())) ) {
      errors.reject("pizza.duplicate");
    }
  }
  
  @Override
    public boolean supports(Class<?> aClass) {
      return Pizza.class.equals(aClass);
    }

}
