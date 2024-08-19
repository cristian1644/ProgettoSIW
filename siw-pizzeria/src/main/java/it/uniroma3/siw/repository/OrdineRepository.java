package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Ordine;

public interface OrdineRepository extends CrudRepository<Ordine, Long>{

	List<Ordine> findByUtenteId(Long utenteId);
}
