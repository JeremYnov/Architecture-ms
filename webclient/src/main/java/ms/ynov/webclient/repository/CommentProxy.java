package ms.ynov.webclient.repository;

import java.sql.Date;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import ms.ynov.webclient.model.CommentR;
import ms.ynov.webclient.model.User;

@Repository
public class CommentProxy extends GenericProxy{
	
	public CommentR createComment(CommentR comment, User session) {
		String createCommentUrl = this.props.getApiUrl() + "/comment/create";
		HttpEntity<CommentR> requestEntity = new HttpEntity<CommentR>(comment);
		
		requestEntity.getBody().setDate(new Date(System.currentTimeMillis()));	
		requestEntity.getBody().setUser(session.getId());
		
		ResponseEntity<CommentR> response = restTemplate.exchange(
				createCommentUrl, HttpMethod.POST, requestEntity, 
				CommentR.class);
		
		return response.getBody();
	}
}
