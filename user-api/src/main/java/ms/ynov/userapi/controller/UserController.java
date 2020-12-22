package ms.ynov.userapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.pattern.color.BoldCyanCompositeConverter;
import ms.ynov.userapi.config.SecurityConfig;
import ms.ynov.userapi.model.User;
import ms.ynov.userapi.repository.UserRepository;
import ms.ynov.userapi.service.CustomUserDetailsService;

@RestController
public class UserController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
    @Autowired
    private SecurityConfig securityConfig;
    
    @GetMapping("/user")
	public Iterable<User> getUsers(){
		Iterable<User> users = userRepository.findAll();
		return users;
	}

	@GetMapping("/user/{id}")
	public Optional<User> getUser(@PathVariable("id") int id){
		Optional<User> user = userRepository.findById(id);
		return user;
    }
	
	@PostMapping("/user/auth")
	public Optional<User> getUserByMail(@RequestBody User user){
		Optional<User> auth = userRepository.findByMail(user.getMail());
		if(auth != null && BCrypt.checkpw(user.getPassword(), auth.get().getPassword())) {
			return auth;
		}
		return null;
    }
	
	// @GetMapping("/user/co")
	// public Optional<User> getUserco(){
	// 	UserDetails user = customUserDetailsService.getCurrentUser();
	// 	Optional<User> currentUser = userRepository.findByMail(user.getUsername());
	// 	return currentUser;
    // }
	
	// Inscription
	@PostMapping("/user/create")
	public User createUser(@RequestBody User user) {
		user.setPassword(securityConfig.bCryptPasswordEncoder().encode(user.getPassword()));
		user = userRepository.save(user);
		return user;
	}

}
