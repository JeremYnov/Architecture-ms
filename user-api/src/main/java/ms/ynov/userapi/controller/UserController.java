package ms.ynov.userapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ms.ynov.userapi.model.User;
import ms.ynov.userapi.repository.UserRepository;

@RestController
public class UserController {
    
    @Autowired
    private UserRepository userRepository;
    
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
}
