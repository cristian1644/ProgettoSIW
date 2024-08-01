package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public Optional<User> getUser(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user;
	}
	
	public void saveUser(User user) {
		userRepository.save(user);
	}

}
