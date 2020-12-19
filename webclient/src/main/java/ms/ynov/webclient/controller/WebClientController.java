package ms.ynov.webclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ms.ynov.webclient.model.Category;
import ms.ynov.webclient.model.User;
import ms.ynov.webclient.repository.CategoryProxy;
import ms.ynov.webclient.repository.UserProxy;

@Controller
public class WebClientController {
	
    @Autowired
    private UserProxy userProxy;

    @Autowired
    private CategoryProxy categoryProxy;
    
    @GetMapping("/category")
	public String getCategories(Model model) {
		Iterable<Category> categories = categoryProxy.getCategory();
		model.addAttribute("categories", categories);
		return "listCategory";
	}

    @GetMapping("/user")
	public String getUserPage(Model model) {
		Iterable<User> users = userProxy.getUsers();
        model.addAttribute("users", users);
        
		return "user";
	}

	@GetMapping("/")
	public String getHomePage() {
		return "homePage";
	}
}
