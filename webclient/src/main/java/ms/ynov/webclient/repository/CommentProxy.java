package ms.ynov.webclient.repository;

import java.sql.Date;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import ms.ynov.webclient.dto.ArticleW;
import ms.ynov.webclient.dto.CommentR;
import ms.ynov.webclient.model.Comment;
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
	
	public CommentR updateComment(CommentR comment) {
		String updateCommentUrl = this.props.getApiUrl() + "/comment/update/" + comment.getId();

		HttpEntity<CommentR> requestEntity = new HttpEntity<CommentR>(comment);

		requestEntity.getBody().setDate(new Date(System.currentTimeMillis()));	
		
		ResponseEntity<CommentR> response = restTemplate.exchange(
				updateCommentUrl, HttpMethod.PUT, requestEntity, 
				CommentR.class);
		
		return response.getBody();
	}
	
	public Comment getComment(int id) {
		String getArticleUrl = this.props.getApiUrl() + "/comment/" + id;

		ResponseEntity<Comment> response = restTemplate.exchange(getArticleUrl, HttpMethod.GET, null, Comment.class);

		return response.getBody();
	}
	
	public void deleteComment(Integer id) {
		String deleteArticleUrl = this.props.getApiUrl() + "/comment/delete/" + id;
		
		restTemplate.exchange(
			deleteArticleUrl, HttpMethod.DELETE, null, 
			Void.class);
	}
}
