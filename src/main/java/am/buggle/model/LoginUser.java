package am.buggle.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Seda Ghazaryan
 * @author Ara Hovhannisyan
 * @class LoginUser
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginUser {

    private String username;
    private String password;

}
