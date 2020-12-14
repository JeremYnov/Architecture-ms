package ms.ynov.userapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
