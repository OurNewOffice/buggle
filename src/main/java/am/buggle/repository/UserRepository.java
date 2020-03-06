package am.buggle.repository;

import am.buggle.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Seda Ghazaryan
 * @author Ara Hovhannisyan
 * @interface UserRepository
 */

@Repository
public interface UserRepository extends CrudRepository<User, Long> {


    /**
     * @param username String
     * @return User user
     */
    User findByPhone(String username);
}
