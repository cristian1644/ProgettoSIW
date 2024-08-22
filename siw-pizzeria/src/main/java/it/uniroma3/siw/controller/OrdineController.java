package it.uniroma3.siw.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Carrello;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Ordine;
import it.uniroma3.siw.model.RigaOrdine;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.OrdineService;
import jakarta.servlet.http.HttpSession;

@Controller
public class OrdineController {

	@Autowired private OrdineService ordineService;
	@Autowired private CredentialsService credentialsService;
	
	@PostMapping("/submit-ordine")
	public String submitOrdine(@ModelAttribute Ordine ordine, 
	        @RequestParam String date, 
	        @RequestParam String time, HttpSession session, Principal principal, 
	        Model model) {
		
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		
		if(carrello != null) {
		
		String dataOraConsegnaString = date + "T" + time;
        LocalDateTime dataOraConsegna = LocalDateTime.parse(dataOraConsegnaString);
        ordine.setDataOraConsegna(dataOraConsegna);
		
	    // Crea un set di righe ordine a partire dal carrello
	    Set<RigaOrdine> righeOrdine = carrello.getItems().stream()
	            .map(item -> new RigaOrdine(item.getPizza().getNome(), item.getQuantity(), item.getPizza().getPrezzo()))
	            .collect(Collectors.toSet());

	    // Calcola il totale dell'ordine e lo imposta nell'oggetto Ordine
	    ordine.setTotale(calcolaTotaleOrdine(righeOrdine, 5.00)); // 5.00 è un esempio di costo di spedizione
	    ordine.setRigheOrdine(righeOrdine); // Imposta il set di righe ordine nell'oggetto Ordine
	    
	    String username = principal.getName();
        Credentials credentials = credentialsService.findByUsername(username);
        Utente utente = credentials.getUser();
        ordine.setUtente(utente);

	    // Salva l'ordine
	    ordineService.salvaOrdine(ordine, righeOrdine);

	    // Svuota il carrello dopo aver registrato l'ordine
	    carrello.svuota();

	    model.addAttribute("ordine", ordine);
		}
	    return "redirect:user";  // La pagina di conferma dell'ordine
	}

    // Metodo per calcolare il totale dell'ordine
    private double calcolaTotaleOrdine(Set<RigaOrdine> righeOrdine, double costoSpedizione) {
        double totalePizze = righeOrdine.stream()
                .mapToDouble(riga -> riga.getPrezzoUnitario() * riga.getQuantita())
                .sum();
        return totalePizze + costoSpedizione;
    }
    
    @GetMapping("/ordini")
    public String listaOrdini(Model model, Principal principal, Authentication authentication) {
        

    	// Recupera il nome utente dal Principal
        String username = principal.getName();
        
        // Usa il nome utente per recuperare l'ID utente
        Credentials currentCredentials = credentialsService.findByUsername(username);
        Long utenteId = currentCredentials.getUser().getId();
    	
        List<Ordine> orders = ordineService.getOrdiniByUtente(utenteId);
        model.addAttribute("authentication", authentication);
        model.addAttribute("ordini", orders);
        return "ordini"; 
    }
    
    @GetMapping("/ordini/dettagli-ordine/{id}")
    public String dettagliOrdine(@PathVariable Long id, Model model, Authentication authentication,Principal principal) {
    	
    	// Recupera il nome utente dal Principal
        String username = principal.getName();
        
        // Usa il nome utente per recuperare l'ID utente
        Credentials currentCredentials = credentialsService.findByUsername(username);
        Long utenteId = currentCredentials.getUser().getId();
    	
        List<Ordine> orders = ordineService.getOrdiniByUtente(utenteId);
    	
    	 Optional<Ordine> optionalOrdine = ordineService.getOrdineById(id);
         if (optionalOrdine.isPresent()) {
             Ordine ordine = optionalOrdine.get();
             model.addAttribute("dettaglioOrdine", ordine);
         } else {
             model.addAttribute("dettaglioOrdine", null);
         }
         model.addAttribute("ordini", orders);
         model.addAttribute("authentication", authentication);
         return "ordini-dettagliOrdine";
    }
}
