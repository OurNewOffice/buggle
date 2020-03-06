package am.buggle.service;

import am.buggle.model.User;
import am.buggle.model.UserDto;

import java.util.List;
import java.util.Optional;

/**
 * @author Seda Ghazaryan
 * @author Ara Hovhannisyan
 * @interface UserService
 */

public interface UserService {

    /**
     * @param user User
     * @return User user
     */
    User save(UserDto user);

    /**
     * @return List<User> usersList
     */
    List<User> findAll();


    /**
     * @param id long
     */
    void delete(long id);


    /**
     * @param username String
     * @return User user
     */
    User findOne(String username);


    /**
     * @param id Long
     * @return Optional<User> optionalUser
     */
    Optional<User> findById(Long id);
}