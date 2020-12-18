package ms.ynov.articleapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ms.ynov.articleapi.dto.Category;
import ms.ynov.articleapi.model.Article;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Integer>{
	Iterable<Article> findByCategory(Integer category);
}
