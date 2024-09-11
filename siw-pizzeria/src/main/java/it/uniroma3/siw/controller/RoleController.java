package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Pizza;
import it.uniroma3.siw.repository.CredentialsRepository;
import it.uniroma3.siw.service.PizzaService;

@Controller
public class RoleController {

	@Autowired private CredentialsRepository credentialsRepository;
	@Autowired PizzaService pizzaService;
	
	@GetMapping("/redirectByRole")
    public String redirectByRole(Authentication authentication) {
        if (authentication != null) {
            String username = authentication.getName();
            Credentials credentials = credentialsRepository.findByUsername(username);
            String role = credentials.getRole();

            if ("ROLE_ADMIN".equals(role)) {
                return "redirect:/admin/pizze";
            } else {
                return "redirect:/pizze";
            }
        }

        // Se l'utente non è autenticato, reindirizza alla pagina di login
        return "redirect:/login";
    }
	
	@GetMapping("/admin/pizze")
    public String adminPizzePage(Model model) {
		model.addAttribute("pizza", new Pizza());
		model.addAttribute("pizze",this.pizzaService.findAll());
        return "admin-pizze";
    }
	
	@GetMapping("/admin/gestionePizze")
	public String gestionePizzePage(Model model) {
		model.addAttribute("pizza", new Pizza());
		model.addAttribute("pizzaRemove", new Pizza());
		return "admin-gestionePizze";
	}
	
	@GetMapping("/access-denied")
	public String accessDeniedPage() {
		return "access-denied";
	}
}
