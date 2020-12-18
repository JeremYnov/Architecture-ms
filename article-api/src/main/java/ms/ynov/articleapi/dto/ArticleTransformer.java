package ms.ynov.articleapi.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ms.ynov.articleapi.model.Article;
import ms.ynov.articleapi.repository.CategoryProxy;
import ms.ynov.articleapi.repository.CommentProxy;
import ms.ynov.articleapi.repository.UserProxy;



@Component
public class ArticleTransformer {
	
	@Autowired
	private UserProxy userProxy;
	
	@Autowired
	private CategoryProxy categoryProxy;
	
	@Autowired
	private CommentProxy commentProxy;
	
	public ArticleR transform(Article article) {
		
		ArticleR articleR = new ArticleR();
		articleR.setId(article.getId());
		articleR.setDate(article.getDate());
		articleR.setContent(article.getContent());
		
		User user = userProxy.getUser(article.getUser());
		articleR.setUser(user);
		
		Category category = categoryProxy.getCategory(article.getCategory());
		articleR.setCategory(category);
		
		Iterable<Comment> comments = commentProxy.getComments(article.getId());
		articleR.setComments(comments);
		
		return articleR;
	}
}
