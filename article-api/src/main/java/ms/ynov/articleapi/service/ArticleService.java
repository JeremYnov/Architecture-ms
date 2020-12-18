package ms.ynov.articleapi.service;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
