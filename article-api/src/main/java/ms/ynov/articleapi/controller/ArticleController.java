package ms.ynov.articleapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ms.ynov.articleapi.dto.ArticleR;
import ms.ynov.articleapi.service.ArticleService;



@RestController
public class ArticleController {
	
	@Autowired
    private ArticleService articleService;
	 
	 @GetMapping("/article/{id}")
	 public ArticleR getArticle(@PathVariable("id") int id){
		 ArticleR article = articleService.getArticle(id);
	     return article;
	}
	 
	@GetMapping("/article/category/{id}")
	public Iterable<ArticleR> getArticles(@PathVariable("id") int id_category){
		Iterable<ArticleR> articles = articleService.getArticles(id_category);
	    return articles;
	    } 
	
}

