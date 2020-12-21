package ms.ynov.webclient.repository;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import ms.ynov.webclient.model.Article;

@Repository
public class ArticleProxy extends GenericProxy{
	
	 public Article getArticle(int id){
		 String getArticleUrl = this.props.getApiUrl() + "/article/" + id;
		 
			ResponseEntity<Article> response = restTemplate.exchange(
					getArticleUrl, 
					HttpMethod.GET, 
					null, 
					Article.class);
		 
	     return response.getBody();
	}

	public Iterable<Article> getArticleByCategory(int id){
		String getArticleUrl = this.props.getApiUrl() + "/article/category/" + id;
		
		ResponseEntity<Iterable<Article>> response = restTemplate.exchange(
			getArticleUrl, HttpMethod.GET, null, 
			new ParameterizedTypeReference<Iterable<Article>>() {});
		
		return response.getBody();
   }

   public Article createArticle(Article article) {
	String createArticleUrl = this.props.getApiUrl() + "/article/create";
	HttpEntity<Article> requestEntity = new HttpEntity<Article>(article);
	
	ResponseEntity<Article> response = restTemplate.exchange(
			createArticleUrl, HttpMethod.POST, requestEntity, 
			Article.class);
	
	return response.getBody();
}
}
