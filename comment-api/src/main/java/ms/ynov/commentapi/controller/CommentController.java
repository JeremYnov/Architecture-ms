package ms.ynov.commentapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ms.ynov.commentapi.model.Comment;
import ms.ynov.commentapi.repository.CommentRepository;


@RestController
public class CommentController {
	
    @Autowired
    private CommentRepository commentRepository;
    
    @GetMapping("/comment")
	public Iterable<Comment> getUsers(){
		Iterable<Comment> users = commentRepository.findAll();
		return users;
	}
}
