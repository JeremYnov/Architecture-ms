package ms.ynov.webclient.repository;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import ms.ynov.webclient.model.User;

@Repository
public class UserProxy extends GenericProxy {
    
    public Iterable<User> getUsers() {
		
		String getUsersUrl = props.getApiUrl() + "/user";
		
		ResponseEntity<Iterable<User>> response = restTemplate.exchange(
				getUsersUrl, 
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<Iterable<User>>() {});
		
		return response.getBody();
	}

}
