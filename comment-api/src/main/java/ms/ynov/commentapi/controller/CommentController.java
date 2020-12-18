package ms.ynov.commentapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ms.ynov.commentapi.dto.CommentR;

import ms.ynov.commentapi.service.CommentService;


@RestController
public class CommentController {
	
    @Autowired
    private CommentService commentService;
    
    @GetMapping("/comment")
	public Iterable<CommentR> getComments(){
		Iterable<CommentR> comments = commentService.getComments();
        return comments;
    } 
    
    @GetMapping("/comment/{id}")
    public CommentR getComment(@PathVariable("id") int id){
        CommentR comment = commentService.getComment(id);
        return comment;
	}
    
    @GetMapping("/comment/article/{id}")
    public Iterable<CommentR> getCommentByArticle(@PathVariable("id") int id){
    	Iterable<CommentR> comments = commentService.getCommentsByArticle(id);
        return comments;
	}
}
