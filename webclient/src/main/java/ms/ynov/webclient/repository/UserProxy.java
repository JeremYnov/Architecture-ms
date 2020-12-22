package ms.ynov.webclient.repository;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import ms.ynov.webclient.model.Article;
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
    
    public User createUser(User user) {
		String createUserUrl = this.props.getApiUrl() + "/user/create";
		HttpEntity<User> requestEntity = new HttpEntity<User>(user);
		
		ResponseEntity<User> response = restTemplate.exchange(
				createUserUrl, HttpMethod.POST, requestEntity, 
				User.class);
		
		return response.getBody();
	}
    
    public User getUserByMail(String mail){
		 String getUserUrl = this.props.getApiUrl() + "/user/co/" + mail;
		 
			ResponseEntity<User> response = restTemplate.exchange(
					getUserUrl, 
					HttpMethod.GET, 
					null, 
					User.class);
		 
	     return response.getBody();
	}

	public User loginUser(User user){
		String getUserUrl = this.props.getApiUrl() + "/user/auth";
		
		HttpEntity<User> requestEntity = new HttpEntity<User>(user);
		
		ResponseEntity<User> response = restTemplate.exchange(
			getUserUrl, HttpMethod.POST, requestEntity, 
				User.class);
		System.out.println(response);
		if(response != null) {
			return response.getBody();
		}
		
		return null;
   }

}
