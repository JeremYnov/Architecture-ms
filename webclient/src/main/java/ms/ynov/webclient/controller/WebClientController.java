package ms.ynov.webclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ms.ynov.webclient.dto.ArticleW;
import ms.ynov.webclient.dto.CommentR;
import ms.ynov.webclient.model.Article;
import ms.ynov.webclient.model.Category;
import ms.ynov.webclient.model.Comment;
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
    
    @GetMapping("/create/category")
	public String addCategory(Model model) {
		if (!this.isConnected) {
			return "redirect:/formLogin";
		}

		Iterable<Category> categories = categoryProxy.getCategory();
		model.addAttribute("categories", categories).addAttribute("isConnected", this.isConnected);

		Category category = new Category();
		model.addAttribute("category", category);
		
		return "addCategory";
	}
    
    @GetMapping("/modify/category/{id}")
	public String modifyCategory(@PathVariable("id") int id, Model model) {
		if (!this.isConnected) {
			return "redirect:/formLogin";
		}

		Iterable<Category> categories = categoryProxy.getCategory();
		Category category = categoryProxy.getCategory(id);
		model.addAttribute("category", category).addAttribute("categories", categories).addAttribute("isConnected", this.isConnected);
		return "modifyCategory";
	}
    
    @GetMapping("/delete/category/{id}")
	public ModelAndView deleteCategory(@PathVariable("id") int id) {
		if (!this.isConnected) {
			return new ModelAndView("redirect:login");
		}

		categoryProxy.deleteCategory(id);
		return new ModelAndView("redirect:/");
	}

	@GetMapping("/")
	public String getHomePage(Model model) {
		Iterable<Category> categories = categoryProxy.getCategory();
		model.addAttribute("categories", categories)
		.addAttribute("isConnected", this.isConnected);
		return "homePage";
	}

	@GetMapping("/profile")
	public String getUserPage(Model model) {
		if (!this.isConnected) {
			return "redirect:/formLogin";
		}

        Iterable<Category> categories = categoryProxy.getCategory();
		model.addAttribute("categories", categories).addAttribute("user",this.session)
		.addAttribute("isConnected", this.isConnected);
		
		return "profile";
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
		if (!this.isConnected) {
			return "redirect:/formLogin";
		}

		Iterable<Category> categories = categoryProxy.getCategory();
		model.addAttribute("categories", categories).addAttribute("isConnected", this.isConnected);

		ArticleW article = new ArticleW();
		model.addAttribute("article", article);
		
		return "addArticle";
	}
	
	@GetMapping("/modify/article/{id}")
	public String modifyArticle(@PathVariable("id") int id, Model model) {
		if (!this.isConnected) {
			return "redirect:/formLogin";
		}

		Iterable<Category> categories = categoryProxy.getCategory();
		Article article = articleProxy.getArticle(id);
		ArticleW articleW = new ArticleW();
		articleW.setId(id);
		articleW.setContent(article.getContent());
		articleW.setIdCategory(String.valueOf(article.getCategory().getId()));
		
		model.addAttribute("article", articleW).addAttribute("categories", categories).addAttribute("isConnected", this.isConnected);
		return "modifyArticle";
	}
	
	@GetMapping("/delete/article/{id}")
	public ModelAndView deleteUser(@PathVariable("id") Integer id, @RequestParam(required = false) Integer idCategory) {
		if (!this.isConnected) {
			return new ModelAndView("redirect:/formLogin");
		}

		articleProxy.deleteArticle(id);
		return new ModelAndView("redirect:/category/" + idCategory.toString() + "/articles");
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
		if (!this.isConnected) {
			return new ModelAndView("redirect:/formLogin");
		}
		
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
	public ModelAndView saveComment(@ModelAttribute CommentR comment, @RequestParam(required = false) Integer idArticle) {
		if (!this.isConnected) {
			return new ModelAndView("redirect:/formLogin");
		}
		
    	if(comment.getId() != null) {
			commentProxy.updateComment(comment);
		}else {
			commentProxy.createComment(comment, this.session);
		}
    	return new ModelAndView("redirect:/article/" + idArticle.toString());
	}

	@GetMapping("/modify/comment/{id}")
	public String modifyComment(@PathVariable("id") int id, Model model) {	
		if (!this.isConnected) {
			return "redirect:/formLogin";
		}

		Iterable<Category> categories = categoryProxy.getCategory();

		Comment comment = commentProxy.getComment(id);

		CommentR newComment = new CommentR();
		
		newComment.setId(comment.getId());
		newComment.setContent(comment.getContent());
		newComment.setDate(comment.getDate());
		newComment.setUser(comment.getUser().getId());
		newComment.setArticle(comment.getArticle());

		model.addAttribute("comment", newComment).addAttribute("categories", categories).addAttribute("isConnected", this.isConnected);
		
		return "modifyComment";
	}
	
	@GetMapping("/delete/comment/{id}")
	public ModelAndView deleteComment(@PathVariable("id") int id, @RequestParam(required = false) Integer idArticle) {
		if (!this.isConnected) {
			return new ModelAndView("redirect:/formLogin");
		}

		commentProxy.deleteComment(id);
		return new ModelAndView("redirect:/article/" + idArticle.toString());
	}

    @PostMapping("/save/article")
	public ModelAndView saveArticle(@ModelAttribute ArticleW articleW, @RequestParam(required = false) Integer idArticle) {
		if (!this.isConnected) {
			return new ModelAndView("redirect:/formLogin");
		}

    	if(articleW.getId() != null) {
			articleProxy.updateArticle(articleW);

			return new ModelAndView("redirect:/article/" + idArticle.toString());
		}else {
			articleW.setIdUser(this.session.getId());
	    	articleProxy.createArticle(articleW);
		}
    	
    	return new ModelAndView("redirect:/");
	}
    
    @PostMapping("/save/category")
	public ModelAndView saveCategory(@ModelAttribute Category category) {
		if (!this.isConnected) {
			return new ModelAndView("redirect:/formLogin");
		}

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
