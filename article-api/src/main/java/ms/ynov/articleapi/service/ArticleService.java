package ms.ynov.articleapi.service;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import ms.ynov.articleapi.dto.ArticleR;
import ms.ynov.articleapi.dto.ArticleTransformer;
import ms.ynov.articleapi.model.Article;
import ms.ynov.articleapi.repository.ArticleRepository;




@Service
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private ArticleTransformer articleTransformer;
	
	public ArticleR getArticle(int id) {
		Article article = articleRepository.findById(id).get();
		ArticleR articleR = articleTransformer.transform(article);

		return articleR;
	}
	
	public Iterable<ArticleR> getArticles(Integer category) {
		Iterable<Article> articles = articleRepository.findByCategory(category);
		Iterator<Article> iterator = articles.iterator();
		ArrayList<ArticleR> listArticle = new ArrayList<ArticleR>();

		while (iterator.hasNext()) {
			Article p = iterator.next();
			ArticleR articleR = articleTransformer.transform(p);

			listArticle.add(articleR);
		}

		return listArticle;
	}
	
	public Article createArticle(Article article) {
		article = articleRepository.save(article);
		return article;
	}
	
	public void deleteArticle(Integer id) {
		articleRepository.deleteById(id);
	}
	
	public Article updateArticle(int id, Article article) {
		Article currentArticle = articleRepository.findById(id).get();
		
		Integer category = article.getCategory();
		if(category != null) {
			currentArticle.setCategory(category);;
		}
		
		String content = article.getContent();
		if(content != null) {
			currentArticle.setContent(content);
		}
		
		java.util.Date d = new java.util.Date();
		java.sql.Date date = new java.sql.Date(d.getTime());
		currentArticle.setDate(date);
		
		
		currentArticle = articleRepository.save(currentArticle);
		return currentArticle;
	}
}
