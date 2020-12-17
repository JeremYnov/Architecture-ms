package ms.ynov.webclient.repository;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import ms.ynov.webclient.model.Category;

@Repository
public class CategoryProxy extends GenericProxy {
    public Iterable<Category> getCategory() {
		String getCategoryUrl = this.props.getApiUrl() + "/category";
		
		ResponseEntity<Iterable<Category>> response = restTemplate.exchange(
				getCategoryUrl, HttpMethod.GET, null, 
				new ParameterizedTypeReference<Iterable<Category>>() {});
		
		return response.getBody();
	}
}
