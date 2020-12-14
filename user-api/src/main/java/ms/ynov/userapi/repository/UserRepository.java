package ms.ynov.userapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ms.ynov.userapi.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
