package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Pizza;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.UserService;

@Controller
public class RegistrazioneController {

	@Autowired private UserService userService;
	@Autowired private CredentialsService credentialsService;
	@Autowired private PasswordEncoder passwordEncoder;
	
	@ModelAttribute("searchPizza")
    public Pizza createSearchPizzaModel() {
        return new Pizza();
    }
	
	@PostMapping("/registrazione")
    public String registraUtente(@RequestParam String nome, @RequestParam String cognome, @RequestParam String email,
            @RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) {
    	
        if (credentialsService.findByEmail(email) != null) {
        	redirectAttributes.addAttribute("errorMessage", "Errore: Email già utilizzata.");
            return "redirect:/login"; // Torna alla pagina di registrazione con un messaggio di errore
        }

        Utente user = userService.creaUtente(nome, cognome);
        this.userService.saveUser(user);
        
        String encodedPassword = passwordEncoder.encode(password); //codifico la password prima di salvarla nel db
        Credentials credentials = credentialsService.creaCredentials(username, encodedPassword ,email, user);
        this.credentialsService.saveCredentials(credentials);
        redirectAttributes.addFlashAttribute("successMessage", "Registrazione avvenuta con successo!");

        return "redirect:/login";
    }
}
