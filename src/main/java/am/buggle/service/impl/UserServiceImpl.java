package am.buggle.service.impl;

import am.buggle.model.User;
import am.buggle.model.UserDto;
import am.buggle.repository.UserRepository;
import am.buggle.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Seda Ghazaryan
 * @author Ara Hovhannisyan
 * @class UserServiceImpl
 */

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bcryptEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder bcryptEncoder) {
        this.userRepository = userRepository;
        this.bcryptEncoder = bcryptEncoder;
    }

    /**
     * @param username String
     * @return User user
     * @throws UsernameNotFoundException usernameNotFoundException
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByPhone(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getPhone(), user.getPassword(), getAuthority(user));
    }

    /**
     * @param user User
     * @return Set<SimpleGrantedAuthority> authorities
     */
    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getUserTypes().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name())));
        return authorities;
    }

	/**
	 * @return List<User> users
	 */
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }


	/**
	 * @param id long
	 */
    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }


	/**
	 * @param username String
	 * @return User user
	 */
    @Override
    public User findOne(String username) {
        return userRepository.findByPhone(username);
    }

	/**
	 * @param id Long
	 * @return Optional<User> optionalUser
	 */
    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }


	/**
	 * @param user User
	 * @return User user
	 */
    @Override
    public User save(UserDto user) {
        User newUser = new User();
        newUser.setPhone(user.getPhone());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(newUser);
    }
}
