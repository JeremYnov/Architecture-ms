package ms.ynov.webclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ms.ynov.webclient.model.User;
import ms.ynov.webclient.repository.UserProxy;

@Controller
public class WebClientController {
	
    @Autowired
    private UserProxy userProxy;
    
    @GetMapping("/")
	public String getHomePage(Model model) {
		Iterable<User> users = userProxy.getUsers();
        model.addAttribute("users", users);
        
		return "user";
	}
}
