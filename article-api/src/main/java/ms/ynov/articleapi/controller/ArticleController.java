package ms.ynov.articleapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ms.ynov.articleapi.dto.ArticleR;
import ms.ynov.articleapi.model.Article;
import ms.ynov.articleapi.service.ArticleService;
import ms.ynov.categoryapi.model.Category;



@RestController
public class ArticleController {
	
	@Autowired
    private ArticleService articleService;
	 
	// recuperation d'un article
	 @GetMapping("/article/{id}")
	 public ArticleR getArticle(@PathVariable("id") int id){
		 ArticleR article = articleService.getArticle(id);
	     return article;
	}
	 
	 //	recuperation des articles par rapport à une categorie
	@GetMapping("/article/category/{id}")
	public Iterable<ArticleR> getArticles(@PathVariable("id") int id_category){
		Iterable<ArticleR> articles = articleService.getArticles(id_category);
	    return articles;
	 } 
	
	//	Creation d'un article
	@PostMapping("/article/create")
	public Article createArticle(@RequestBody Article article) {
		article = articleService.createArticle(article);
		return article;
	}
	
	// Suppresion d'un article
	@DeleteMapping("/article/delete/{id}")
	public void deleteArticle(@PathVariable("id") int id) {
		articleService.deleteArticle(id);
	}
	
	// Modification d'une categorie
	@PutMapping("/article/update/{id}")
	public Article updateArticle(@PathVariable("id") int id, @RequestBody Article article) {
		article = articleService.updateArticle(id, article);
		return article;
	}
	
}

