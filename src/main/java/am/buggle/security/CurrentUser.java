package am.buggle.security;

import am.buggle.model.User;
import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {
    private User user;


    public CurrentUser(User user) {
        super(user.getPhone(), user.getPassword(), AuthorityUtils.createAuthorityList("USER", "DRIVER"));
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
