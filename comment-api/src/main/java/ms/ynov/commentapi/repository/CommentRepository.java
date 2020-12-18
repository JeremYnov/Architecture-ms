package ms.ynov.commentapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ms.ynov.commentapi.model.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer>{
	
	Iterable<Comment> findByArticle(Integer article);
}
