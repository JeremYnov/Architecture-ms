package ms.ynov.webclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ms.ynov.webclient.dto.ArticleW;
import ms.ynov.webclient.model.Article;
import ms.ynov.webclient.model.Category;
import ms.ynov.webclient.model.Comment;
import ms.ynov.webclient.model.CommentR;
import ms.ynov.webclient.model.User;
import ms.ynov.webclient.repository.ArticleProxy;
import ms.ynov.webclient.repository.CategoryProxy;
import ms.ynov.webclient.repository.CommentProxy;
import ms.ynov.webclient.repository.UserProxy;


@Controller
public class WebClientController {
	
	private String error = null;

	private User session = null;
	
	private Boolean isConnected = false;
	
    @Autowired
    private UserProxy userProxy;
    
    @Autowired
    private CommentProxy commentProxy;

    @Autowired
    private CategoryProxy categoryProxy;
    
    @Autowired
    private ArticleProxy articleProxy;
    
    @GetMapping("/category")
	public String getCategories(Model model) {
		Iterable<Category> categories = categoryProxy.getCategory();
		model.addAttribute("categories", categories).addAttribute("isConnected", this.isConnected);
		return "listCategory";
	}
    
    @GetMapping("/create/category")
	public String addCategory(Model model) {
		Iterable<Category> categories = categoryProxy.getCategory();
		model.addAttribute("categories", categories).addAttribute("isConnected", this.isConnected);

		Category category = new Category();
		model.addAttribute("category", category);
		
		return "addCategory";
	}
    
    @GetMapping("/modify/category/{id}")
	public String modifyCategory(@PathVariable("id") int id, Model model) {
		Iterable<Category> categories = categoryProxy.getCategory();
		Category category = categoryProxy.getCategory(id);
		model.addAttribute("category", category).addAttribute("categories", categories).addAttribute("isConnected", this.isConnected);
		return "modifyCategory";
	}
    
    @GetMapping("/delete/category/{id}")
	public ModelAndView deleteCategory(@PathVariable("id") int id) {
		categoryProxy.deleteCategory(id);
		return new ModelAndView("redirect:/");
	}

    @GetMapping("/user")
	public String getUserPage(Model model) {
		Iterable<User> users = userProxy.getUsers();
        model.addAttribute("users", users).addAttribute("isConnected", this.isConnected);
        
		return "user";
	}

	@GetMapping("/")
	public String getHomePage(Model model) {
		Iterable<Category> categories = categoryProxy.getCategory();
		model.addAttribute("categories", categories)
		.addAttribute("isConnected", this.isConnected);
		return "homePage";
	}
	
	@GetMapping("/formLogin")
	public String login(Model model) {
		Iterable<Category> categories = categoryProxy.getCategory();
		model.addAttribute("categories", categories);

		User user = new User();
		model.addAttribute("user", user)
		.addAttribute("error", this.getError()).addAttribute("isConnected", this.isConnected);
		
		this.setError(null);
		return "login";
	}

	@GetMapping("/navbar")
	public String getNavbar(Model model) {
		return "navbar.html";
	}
    
    @GetMapping("/article/{id}")
	public String getArticlePage(@PathVariable("id") int id, Model model) {
		Iterable<Category> categories = categoryProxy.getCategory();
		model.addAttribute("categories", categories).addAttribute("isConnected", this.isConnected);
		if(this.isConnected) {
			model.addAttribute("sessionId", this.session.getId());
		}
    	Article article = articleProxy.getArticle(id);
		model.addAttribute("article", article);

		CommentR comment = new CommentR();
		model.addAttribute("comment", comment);
    	
		return "article";
	}

	@GetMapping("/category/{id}/articles")
	public String getArticleByCategory(@PathVariable("id") int id, Model model) {
		Iterable<Category> categories = categoryProxy.getCategory();
		model.addAttribute("categories", categories);

    	Iterable<Article> articles = articleProxy.getArticleByCategory(id);
		model.addAttribute("articles", articles).addAttribute("isConnected", this.isConnected);

    	int count = 0;
    	while (articles.iterator().hasNext()) {
    		count += 1;
    		if(count == 1) { break; }
    	}
    	if (count == 0) {
    		articles = null;
    	}
    	
		model.addAttribute("articles", articles).addAttribute("isConnected", this.isConnected);
		
		return "articlesByCategory";
	}

	@GetMapping("/add/article")
	public String addArticle(Model model) {
		Iterable<Category> categories = categoryProxy.getCategory();
		model.addAttribute("categories", categories).addAttribute("isConnected", this.isConnected);

		ArticleW article = new ArticleW();
		model.addAttribute("article", article);
		
		return "addArticle";
	}
	
	@GetMapping("/modify/article/{id}")
	public String modifyArticle(@PathVariable("id") int id, Model model) {
		Iterable<Category> categories = categoryProxy.getCategory();
		Article article = articleProxy.getArticle(id);
		ArticleW articleW = new ArticleW();
		articleW.setId(id);
		articleW.setContent(article.getContent());
		articleW.setIdCategory(String.valueOf(article.getCategory().getId()));
		System.out.println(articleW.getId());
		model.addAttribute("article", articleW).addAttribute("categories", categories).addAttribute("isConnected", this.isConnected);
		return "modifyArticle";
	}
	
	@GetMapping("/delete/article/{id}")
	public ModelAndView deleteUser(@PathVariable("id") int id) {
		articleProxy.deleteArticle(id);
		return new ModelAndView("redirect:/");
	}
	
    
    @GetMapping("/signup")
	public String createUser(Model model) {
		Iterable<Category> categories = categoryProxy.getCategory();
		model.addAttribute("categories", categories).addAttribute("isConnected", this.isConnected);

		User user = new User();
		model.addAttribute("user", user)
		.addAttribute("error", this.getError());
		
		this.setError(null);
		return "signup";
	}


	@PostMapping("/login")
	public ModelAndView loginUser(@ModelAttribute User user, Model model) {
		Iterable<Category> categories = categoryProxy.getCategory();
		model.addAttribute("categories", categories).addAttribute("isConnected", this.isConnected);

		User auth = userProxy.loginUser(user);
		if (auth != null) {

			this.session = auth;
			this.isConnected = true;
			
			return new ModelAndView("redirect:/");
			
		}
		this.setError("error d'authentification");
		return new ModelAndView("redirect:/formLogin");

	}
	
	@GetMapping("/logout")
	public ModelAndView logout() {
		
		this.session = null;
		this.isConnected = false;

		return new ModelAndView("redirect:/");
	}
    
    @PostMapping("/save/user")
	public ModelAndView saveUser(@ModelAttribute User user) {
    	if (user.getPassword().equals(user.getVerifyPassword())) {
    		userProxy.createUser(user);
    		return new ModelAndView("redirect:/");
    	}
    	this.setError("vos 2 mot de passes ne sont pas identique");
    	return new ModelAndView("redirect:/");
	}
    
    @PostMapping("/save/comment")
	public ModelAndView saveComment(@ModelAttribute CommentR comment) {
    	commentProxy.createComment(comment, this.session);
    	
    	return new ModelAndView("redirect:/");
	}
    @PostMapping("/save/article")
	public ModelAndView saveArticle(@ModelAttribute ArticleW articleW) {
    	if(articleW.getId() != null) {
			articleProxy.updateArticle(articleW);
		}else {
			articleW.setIdUser(this.session.getId());
	    	articleProxy.createArticle(articleW);
		}
    	
    	return new ModelAndView("redirect:/");
	}
    
    @PostMapping("/save/category")
	public ModelAndView saveCategory(@ModelAttribute Category category) {
    	if(category.getId() != null) {
			categoryProxy.updateCategory(category);
		}else {
			categoryProxy.createCategory(category);
		}
    	return new ModelAndView("redirect:/");
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
