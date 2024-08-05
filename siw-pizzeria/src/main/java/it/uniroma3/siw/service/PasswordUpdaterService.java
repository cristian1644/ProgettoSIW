package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.repository.CredentialsRepository;
import jakarta.transaction.Transactional;

@Service
public class PasswordUpdaterService {

	@Autowired
    private CredentialsRepository credentialsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void updatePasswords() {
        Iterable<Credentials> users = credentialsRepository.findAll();
        for (Credentials user : users) {
            // Codifica la password esistente
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            // Salva la password codificata
            user.setPassword(encodedPassword);
            credentialsRepository.save(user);
        }
    }
}
