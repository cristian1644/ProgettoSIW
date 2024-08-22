package it.uniroma3.siw.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Ordine;
import it.uniroma3.siw.model.RigaOrdine;
import it.uniroma3.siw.repository.OrdineRepository;
import jakarta.transaction.Transactional;

@Service
public class OrdineService {

	@Autowired private OrdineRepository ordineRepository;
    
	@Transactional
    public Ordine salvaOrdine(Ordine ordine, Set<RigaOrdine> righeOrdine) {
        ordine.setRigheOrdine(righeOrdine);
        for (RigaOrdine riga : righeOrdine) {
            riga.setOrdine(ordine);
        }
        return ordineRepository.save(ordine);
    }
	
	 public List<Ordine> getOrdiniByUtente(Long utenteId) {
        return ordineRepository.findByUtenteId(utenteId);
    }
	 
	 public Optional<Ordine> getOrdineById(Long id) {
		 return this.ordineRepository.findById(id);
	 }
}
