package ms.ynov.commentapi.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ms.ynov.commentapi.dto.CommentR;
import ms.ynov.commentapi.dto.CommentTransformer;
import ms.ynov.commentapi.model.Comment;
import ms.ynov.commentapi.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private CommentTransformer commentTransformer;

	public CommentR getComment(int id) {
		Comment comment = commentRepository.findById(id).get();
		CommentR commentR = commentTransformer.transform(comment);

		return commentR;
	}

	public Iterable<CommentR> getComments() {
		Iterable<Comment> comments = commentRepository.findAll();
		Iterator<Comment> iterator = comments.iterator();
		ArrayList<CommentR> listComment = new ArrayList<CommentR>();

		while (iterator.hasNext()) {
			Comment p = iterator.next();
			CommentR commentR = commentTransformer.transform(p);

			listComment.add(commentR);
		}

		return listComment;
	}
	
	public Iterable<CommentR> getCommentsByArticle(int id) {
		Iterable<Comment> comments = commentRepository.findByArticle(id);
		Iterator<Comment> iterator = comments.iterator();
		ArrayList<CommentR> listComment = new ArrayList<CommentR>();

		while (iterator.hasNext()) {
			Comment p = iterator.next();
			CommentR commentR = commentTransformer.transform(p);

			listComment.add(commentR);
		}

		return listComment;
	}
	
	//Creation d'un commentaire
	public Comment createComment(Comment comment) {
		comment = commentRepository.save(comment);
 		return comment;
 	}
	
	//Suppresion d'un commentaire
	public void deleteComment(int id) {
		commentRepository.deleteById(id);
	}
	
	// Modification d'une categorie
	public Comment updateComment(int id, Comment comment) {
		Comment currentComment = commentRepository.findById(id).get();
			
		String content = comment.getContent();
		Date date = comment.getDate(); 
		
		if(content != null) {
			currentComment.setContent(content);
		}
		
		if(date != null) {
			currentComment.setDate(date);
		}
			
		currentComment = commentRepository.save(currentComment);
		return currentComment;
	}
}
