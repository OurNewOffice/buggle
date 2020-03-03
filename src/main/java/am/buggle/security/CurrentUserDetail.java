package am.buggle.security;

import am.buggle.model.User;
import lombok.Getter;
import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUserDetail extends org.springframework.security.core.userdetails.User{

    @Getter
    private User user;

    public CurrentUserDetail(User user) {
        super(user.getPhone(),user.getPassword(), AuthorityUtils.createAuthorityList("USER", "DRIVER"));
        this.user = user;
    }
}