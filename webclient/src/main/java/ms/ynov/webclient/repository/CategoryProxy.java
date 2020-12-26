package ms.ynov.webclient.repository;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import ms.ynov.webclient.dto.ArticleW;
import ms.ynov.webclient.model.Article;
import ms.ynov.webclient.model.Category;

@Repository
public class CategoryProxy extends GenericProxy {
	
	public Category getCategory(int id) {
		String getCategoryUrl = this.props.getApiUrl() + "/category/" + id;

		ResponseEntity<Category> response = restTemplate.exchange(getCategoryUrl, HttpMethod.GET, null, Category.class);

		return response.getBody();
	}
	
    public Iterable<Category> getCategory() {
		String getCategoryUrl = this.props.getApiUrl() + "/category";
		
		ResponseEntity<Iterable<Category>> response = restTemplate.exchange(
				getCategoryUrl, HttpMethod.GET, null, 
				new ParameterizedTypeReference<Iterable<Category>>() {});
		
		return response.getBody();
	}
    
    public Category createCategory(Category category) {
		String createCategoryUrl = this.props.getApiUrl() + "/category/create";
		HttpEntity<Category> requestEntity = new HttpEntity<Category>(category);

		ResponseEntity<Category> response = restTemplate.exchange(createCategoryUrl, HttpMethod.POST, requestEntity,
				Category.class);

		return response.getBody();
	}
    
    public Category updateCategory(Category category) {
		String updateCategoryUrl = this.props.getApiUrl() + "/category/update/" + category.getId();

		HttpEntity<Category> requestEntity = new HttpEntity<Category>(category);
		
		ResponseEntity<Category> response = restTemplate.exchange(
				updateCategoryUrl, HttpMethod.PUT, requestEntity, 
				Category.class);
		
		return response.getBody();
	}
    
    public void deleteCategory(Integer id) {
		String deleteCategoryUrl = this.props.getApiUrl() + "/category/delete/" + id;

		
		restTemplate.exchange(
			deleteCategoryUrl, HttpMethod.DELETE, null, 
			Void.class);
	}
}
