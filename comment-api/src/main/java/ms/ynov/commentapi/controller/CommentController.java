package ms.ynov.commentapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ms.ynov.commentapi.dto.CommentR;
import ms.ynov.commentapi.model.Comment;
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
    
    // Creation d'un commentaire
 	@PostMapping("/comment/create")
 	public Comment createCategory(@RequestBody Comment comment) {
 		System.out.println(comment);
 		comment = commentService.createComment(comment);
 		return comment;
 	}
 	
 	// Suppresion d'une categorie
 	@DeleteMapping("/comment/delete/{id}")
 	public void deleteComment(@PathVariable("id") int id) {
 		commentService.deleteComment(id);
 	}
 	
	// Modification d'une categorie
	@PutMapping("/comment/update/{id}")
	public Comment updateComment(@PathVariable("id") int id, @RequestBody Comment comment) {
		comment = commentService.updateComment(id, comment);
		return comment;
	}
}
