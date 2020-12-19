package ms.ynov.webclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ms.ynov.webclient.model.Article;
import ms.ynov.webclient.model.Category;
import ms.ynov.webclient.model.User;
import ms.ynov.webclient.repository.ArticleProxy;
import ms.ynov.webclient.repository.CategoryProxy;
import ms.ynov.webclient.repository.UserProxy;

@Controller
public class WebClientController {
	
	private String error = null;
	
    @Autowired
    private UserProxy userProxy;

    @Autowired
    private CategoryProxy categoryProxy;
    
    @Autowired
    private ArticleProxy articleProxy;
    
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
    
    @GetMapping("/article/{id}")
	public String getArticlePage(@PathVariable("id") int id, Model model) {
    	Article article = articleProxy.getArticle(id);
    	model.addAttribute("article", article);
    	
		return "article";
	}
    
    @GetMapping("/signup")
	public String createUser(Model model) {
		User user = new User();
		model.addAttribute("user", user)
		.addAttribute("error", this.getError());
		
		this.setError(null);
		return "signup";
	}
    
    @PostMapping("/save/user")
	public ModelAndView saveUser(@ModelAttribute User user) {
    	if (user.getPassword().equals(user.getVerifyPassword())) {
    		userProxy.createUser(user);
    		return new ModelAndView("redirect:/");
    	}
    	this.setError("vos 2 mot de passes ne sont pas identique");
    	return new ModelAndView("redirect:/signup");
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
