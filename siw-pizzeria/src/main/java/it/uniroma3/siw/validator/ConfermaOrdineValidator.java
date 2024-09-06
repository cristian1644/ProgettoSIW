package it.uniroma3.siw.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Ordine;


@Component
public class ConfermaOrdineValidator implements Validator{
	
	@Override
	  public void validate(Object o, Errors errors) {
	    Ordine ordine = (Ordine)o;
	    
	    //controllo se la via inserita è valida
	    if(ordine.getIndirizzo() != null && !ordine.getIndirizzo().startsWith("via")) {
	    	errors.rejectValue("indirizzo", "indirizzo.invalid");
	    }
	    
	  }
	  
	  @Override
	    public boolean supports(Class<?> aClass) {
	      return Ordine.class.equals(aClass);
	    }
}
