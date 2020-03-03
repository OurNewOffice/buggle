package am.buggle.repository;

import am.buggle.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByPhone(String phone);
}
