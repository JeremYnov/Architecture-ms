package ms.ynov.articleapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ms.ynov.articleapi.model.Article;
import ms.ynov.articleapi.repository.ArticleRepository;



@RestController
public class ArticleController {
	 @Autowired
	    private ArticleRepository articleRepository;
	    
	 @GetMapping("/article/{id}")
		public Optional<Article> getArticle(@PathVariable("id") int id){
			Optional<Article> article = articleRepository.findById(id);
			return article;		
		}
}
