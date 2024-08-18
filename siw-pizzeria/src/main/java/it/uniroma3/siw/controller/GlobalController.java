package it.uniroma3.siw.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import it.uniroma3.siw.model.Carrello;
import it.uniroma3.siw.model.Pizza;
import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalController {

	@ModelAttribute("userDetails")
	  	public UserDetails getUser() {
	    UserDetails user = null;
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (!(authentication instanceof AnonymousAuthenticationToken)) {
	      user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    }
	    return user;
	  }
	
	@ModelAttribute
    public void addGlobalAttributes(Model model, HttpSession session) {
        // Recupera il carrello dalla sessione
        Carrello carrello = (Carrello) session.getAttribute("carrello");

        // Calcola il numero totale delle pizze
        int totalPizzaCount = carrello != null 
            ? carrello.getItems().stream().mapToInt(item -> item.getQuantity()).sum() 
            : 0;
        
        model.addAttribute("searchPizza", new Pizza());
        model.addAttribute("totalPizzaCount", totalPizzaCount);
    }
}
