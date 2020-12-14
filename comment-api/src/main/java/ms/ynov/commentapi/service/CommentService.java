package ms.ynov.commentapi.service;

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
}
