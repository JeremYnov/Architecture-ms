package ms.ynov.commentapi.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ms.ynov.commentapi.model.Comment;
import ms.ynov.commentapi.model.User;
import ms.ynov.commentapi.repository.UserProxy;

@Component
public class CommentTransformer {
	
	@Autowired
	private UserProxy userProxy;
	
	public CommentR transform(Comment comment) {
		
		CommentR commentR = new CommentR();
		commentR.setId(comment.getId());
		commentR.setDate(comment.getDate());
		commentR.setContent(comment.getContent());
		commentR.setArticle(comment.getArticle());
		
		User user = userProxy.getUser(comment.getUser());
		commentR.setUser(user);
		
		return commentR;
	}
	
}
