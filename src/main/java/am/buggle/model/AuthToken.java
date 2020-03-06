package am.buggle.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Seda Ghazaryan
 * @author Ara Hovhannisyan
 * @class AuthToken
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthToken {

    private String token;
    private User user;

}
