package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Carrello;
import it.uniroma3.siw.model.Pizza;
import it.uniroma3.siw.service.PizzaService;
import jakarta.servlet.http.HttpSession;

@Controller
public class CarrelloController {
	
	@Autowired PizzaService pizzaService;
	
	@PostMapping("/carrello/aggiungi/{pizzaId}")
    public String aggiungiAlCarrello(@PathVariable("pizzaId") Long pizzaId,
                                     @RequestParam("quantita") int quantita, 
                                     HttpSession session) {
        Pizza pizza = pizzaService.findById(pizzaId);
        
        // Recupera o crea un nuovo carrello nella sessione
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        if (carrello == null) {
            carrello = new Carrello();
            session.setAttribute("carrello", carrello);
        }

        // Aggiunge la pizza al carrello con la quantità specificata
        carrello.addPizza(pizza, quantita);
        
        return "redirect:/carrello";  // Reindirizza alla pagina del carrello
    }
	
	@GetMapping("/carrello")
    public String showCarrello(Model model, HttpSession session, Authentication authentication) {
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        
        // Se non c'è un carrello in sessione, crea un nuovo carrello vuoto
        if (carrello == null) {
            carrello = new Carrello();
            session.setAttribute("carrello", carrello);
        }
        
        model.addAttribute("carrello", carrello); 
        model.addAttribute("authentication", authentication);
        
        return "carrello"; // Nome della tua pagina HTML
    }
	
	@PostMapping("/carrello/rimuovi/{pizzaId}")
    public String removePizzaFromCarrello(@PathVariable("pizzaId") Long pizzaId, HttpSession session) {
		Pizza pizza = pizzaService.findById(pizzaId);
	    if (pizza == null) {
	        System.out.println("Pizza non trovata per l'ID: " + pizzaId);
	        return "redirect:/carrello?error=pizza-not-found";
	    }
	    
	    Carrello carrello = (Carrello) session.getAttribute("carrello");
	    if (carrello == null) {
	        System.out.println("Carrello non trovato nella sessione.");
	        return "redirect:/carrello?error=carrello-not-found";
	    }

	    carrello.removePizza(pizza);
	    return "redirect:/carrello";

    }
	
	@PostMapping("/carrello/checkout")
    public String showCheckoutPage(Model model, HttpSession session) {
        // Recupera il carrello dalla sessione
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        model.addAttribute("carrello", carrello);
        
        if (carrello.getItems().size() == 0) {
        	model.addAttribute("error", "Il carrello è vuoto. Aggiungi articoli prima di procedere al checkout.");
            return "carrello"; // Reindirizza al carrello se non ci sono articoli
        }

        return "checkout";
    }
	
	@PostMapping("/carrello/confermaOrdine")
    public String processCheckout(HttpSession session) {
		 // Recupera il carrello dalla sessione
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        
        if (carrello == null || carrello.getItems().isEmpty()) {
            return "redirect:/carrello"; // Reindirizza se non ci sono articoli nel carrello
        }

        // Logica per processare l'ordine
        // Esempio: salvare l'ordine nel database, inviare email di conferma, ecc.

        // Dopo che l'ordine è confermato, puoi svuotare il carrello
        session.removeAttribute("carrello");

        return "redirect:/conferma"; // Reindirizza alla pagina di conferma dell'ordine
    }
}
