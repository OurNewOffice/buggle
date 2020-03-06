package am.buggle.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Seda Ghazaryan
 * @author Ara Hovhannisyan
 * @class UserDto
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String phone;
    private String password;
}
