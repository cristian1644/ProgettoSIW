package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.repository.CredentialsRepository;

@Service
public class CredentialsService {

	@Autowired
	private CredentialsRepository credentialsRepository;
	
	public Optional<Credentials> getCredentials(Long id) {
		Optional<Credentials> credentials = credentialsRepository.findById(id);
		return credentials;
	}
	
	public Optional<Credentials> getCredentials(String username) {
		Optional<Credentials> credentials = credentialsRepository.findByUsername(username);
		return credentials;
	}
	
	public void saveCredentials(Credentials credentials) {
		credentialsRepository.save(credentials);
	}
}
