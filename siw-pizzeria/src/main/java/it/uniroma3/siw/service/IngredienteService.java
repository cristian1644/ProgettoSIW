package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Ingrediente;
import it.uniroma3.siw.repository.IngredienteRepository;

@Service
public class IngredienteService {

  @Autowired
  private IngredienteRepository ingredienteRepository;
  
  public Ingrediente findById(Long id) {
    return ingredienteRepository.findById(id).get();
  }
  
  public List<Ingrediente> findByNome(String nome) {
    // TODO Auto-generated method stub
    return ingredienteRepository.findByNome(nome);
  }
  
  public Iterable<Ingrediente> findAll() {
    return ingredienteRepository.findAll();
  }
  
  public void save(Ingrediente ingrediente) {
    // TODO Auto-generated method stub
    ingredienteRepository.save(ingrediente);
  }
}
