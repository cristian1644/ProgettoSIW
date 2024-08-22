package it.uniroma3.siw.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Ordine;

public interface OrdineRepository extends CrudRepository<Ordine, Long>{

	List<Ordine> findByUtenteId(Long utenteId);
	public Optional<Ordine> findById(Long id);
	
}
