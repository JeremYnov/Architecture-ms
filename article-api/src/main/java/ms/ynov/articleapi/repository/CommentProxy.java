package ms.ynov.articleapi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import ms.ynov.articleapi.config.CustomProperties;
import ms.ynov.articleapi.dto.Comment;

@Repository
public class CommentProxy {
	@Autowired
    private RestTemplate restTemplate;
    
    @Autowired
	private CustomProperties props;
    
    public Iterable<Comment> getComments(int id_article){
        String getCommentsUrl = props.getApiUrl() + "/comment/article/" + id_article;
        ResponseEntity<Iterable<Comment>> response = restTemplate.exchange(
				getCommentsUrl, HttpMethod.GET, null, 
				new ParameterizedTypeReference<Iterable<Comment>>() {});
                
        return response.getBody();
    }
}
