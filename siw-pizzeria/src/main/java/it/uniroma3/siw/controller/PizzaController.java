 package it.uniroma3.siw.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Pizza;
import it.uniroma3.siw.repository.PizzaRepository;
import it.uniroma3.siw.service.PizzaService;
import it.uniroma3.siw.validator.NewPizzaValidator;
import it.uniroma3.siw.validator.RimuoviPizzaValidator;
import it.uniroma3.siw.validator.SearchPizzaValidator;
import jakarta.validation.Valid;

@Controller
public class PizzaController {

	@Autowired private PizzaService pizzaService;
	@Autowired private NewPizzaValidator newPizzaValidator;
	@Autowired private SearchPizzaValidator searchPizzaValidator;
	@Autowired private PizzaRepository pizzaRepository;
	@Autowired private RimuoviPizzaValidator rimuoviPizzaValidator;
	
	@Value("${file.upload-dir}")
    private String uploadDir;

	@GetMapping("/formNewPizza")
	public String formNewPizza(Model model) {
		model.addAttribute("pizza", new Pizza());
		return "formNewPizza.html";
	}
	
	@PostMapping("/pizze")
	public String newPizza(@Valid @ModelAttribute("pizza") Pizza pizza,BindingResult bindingResult,
			@RequestParam("file") MultipartFile file,Model model) {
		
		this.newPizzaValidator.validate(pizza,bindingResult);
	    if (!bindingResult.hasErrors()) {
	    	try {
	    	//gestione del file
	    	if (!file.isEmpty()) {
                Path dirPath = Paths.get(uploadDir);
                if (!Files.exists(dirPath)) {
                    Files.createDirectories(dirPath);
                }

                String filename = file.getOriginalFilename();
                Path filePath = dirPath.resolve(filename);
                Files.write(filePath, file.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

                // Imposta il percorso dell'immagine come URL accessibile
                String fileUrl = filename;
                pizza.setPathImage(fileUrl);

            }
	    	
	      this.pizzaService.save(pizza);
	      model.addAttribute("pizza", pizza);
	      return "redirect:pizza/" + pizza.getId();
	      
	    	} catch (IOException e) {
                e.printStackTrace();
                bindingResult.rejectValue("file", "upload.failed", "Failed to upload file: " + e.getMessage());
            }
	      
	    } else {
	    	model.addAttribute("pizzaRemove", new Pizza());
	    }
	    return "admin-gestionePizze";
	}	
	
	 @ModelAttribute("searchPizza")
	    public Pizza createSearchPizzaModel() {
	        return new Pizza();
	    }

	 @GetMapping("/")
	    public String index() {    
	        return "index"; 
	    }
	
	@PostMapping("/searchPizza") 
	public String searchPizza(@Valid @ModelAttribute("searchPizza") Pizza searchPizza,BindingResult bindingResult, Model model) {
		
		this.searchPizzaValidator.validate(searchPizza,bindingResult);
	    if (!bindingResult.hasErrors()) {
	    	 Pizza foundPizza = pizzaRepository.findByNomeIgnoreCase(searchPizza.getNome());
	    	 model.addAttribute("searchPizza", foundPizza);
	    	return "redirect:pizza/" + foundPizza.getId();
	    } else {
	    	 bindingResult.rejectValue("nome", "pizza.notExists");		
		return "index";
	    }
	}


	@GetMapping("/pizza/{id}")
	public String getPizza(@PathVariable("id") Long id,Model model) {
		model.addAttribute("pizza",this.pizzaService.findById(id));
		return "pizza.html";
	}

	@GetMapping("/pizze")
	public String showPizze(Model model) {
		model.addAttribute("pizze",this.pizzaService.findAll());
		model.addAttribute("pizza", new Pizza());
		return "pizze.html";
	}
	
	@PostMapping("/rimuovi")
    public String removePizza(@Valid @ModelAttribute("pizzaRemove") Pizza pizzaRemove, BindingResult bindingResult, Model model) {
        this.rimuoviPizzaValidator.validate(pizzaRemove, bindingResult);
        if(!bindingResult.hasErrors()) {
        	//recupero la pizza direttamente dal db per poter eliminare la foto
        	 Pizza pizzaToDelete = this.pizzaRepository.findByNomeIgnoreCase(pizzaRemove.getNome());
        	
        	//rimuovo la foto
        	if(pizzaToDelete.getPathImage() != null) {
        		Path imagePath = Paths.get(uploadDir, pizzaToDelete.getPathImage());
        		try {
                    Files.deleteIfExists(imagePath);
                    System.out.println("Image deleted: " + imagePath);
                } catch (IOException e) {
                    System.err.println("Failed to delete image: " + e.getMessage());
                    bindingResult.rejectValue("nome", "delete.failed", "Errore nella rimozione dell'immagine.");
                    return "admin-gestionePizze";
                }
        	}
        	
        	this.pizzaService.delete(this.pizzaRepository.findByNomeIgnoreCase(pizzaRemove.getNome()));
        	model.addAttribute("successMessage", "Pizza rimossa con successo!");
        }else {
        	bindingResult.rejectValue("nome", "pizza.notExists");
        }
        
        model.addAttribute("pizza", new Pizza());
        return "admin-gestionePizze";
    }
	
	@GetMapping("/orari")
	public String orariPage() {
		return "orari";
	}
}
