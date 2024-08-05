package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Utente;

public interface UserRepository extends CrudRepository<Utente, Long>{

	public Utente findByNome(String nome);
	public boolean existsByNome(String nome);
	
}
