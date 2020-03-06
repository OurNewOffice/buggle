package am.buggle.endpoints;

import am.buggle.model.User;
import am.buggle.model.UserDto;
import am.buggle.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Seda Ghazaryan
 * @author Ara Hovhannisyan
 * @class UserEndpoint
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController

@ApiResponses(value = {
        @ApiResponse(code = 200, message = "All DONE"),
        @ApiResponse(code = 201, message = "CREATED"),
        @ApiResponse(code = 404, message = "NOT FOUND")})
public class UserEndpoint {

    private final UserService userService;

    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(httpMethod = "GET", value = "Get users", response = User.class)
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> listUser() {
        return userService.findAll();
    }


    /**
     * @param id Long
     * @return {@link User user}
     */
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User getOne(@PathVariable(value = "id") Long id) {
        return userService.findById(id).orElse(null);
    }

    /**
     * @param user User
     * @return User user
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public User saveUser(@RequestBody UserDto user) {
        return userService.save(user);
    }
}
