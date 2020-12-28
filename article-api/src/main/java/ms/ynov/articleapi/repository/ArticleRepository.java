package ms.ynov.articleapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ms.ynov.articleapi.model.Article;
import ms.ynov.articleapi.model.Category;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Integer>{
	Iterable<Article> findByCategory(Integer category);
}
