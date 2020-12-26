package ms.ynov.articleapi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import ms.ynov.articleapi.config.CustomProperties;
import ms.ynov.articleapi.model.Category;


@Repository
public class CategoryProxy {
	@Autowired
    private RestTemplate restTemplate;
    
    @Autowired
	private CustomProperties props;
    
    public Category getCategory(int id){
        String getCategoryUrl = props.getApiUrl() + "/category/" + id;
        ResponseEntity<Category> response = restTemplate.exchange(
                getCategoryUrl, HttpMethod.GET, null, Category.class);
                
        return response.getBody();
    }
}
