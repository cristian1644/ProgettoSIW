package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Pizza;

public interface PizzaRepository extends CrudRepository<Pizza, Long>{

	public Pizza findByNomeIgnoreCase(String nome);
	public Pizza findByNome(String nome);
	public boolean existsByNomeIgnoreCase(String nome);
	public boolean existsByNome(String nome);
}
