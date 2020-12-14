package ms.ynov.categoryapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ms.ynov.categoryapi.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

}
