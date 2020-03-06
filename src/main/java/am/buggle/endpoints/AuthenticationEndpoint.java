package am.buggle.endpoints;

import am.buggle.config.TokenProvider;
import am.buggle.model.AuthToken;
import am.buggle.model.LoginUser;
import am.buggle.model.User;
import am.buggle.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Seda Ghazaryan
 * @author Ara Hovhannisyan
 * @class AuthenticationEndpoint
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "All DONE"),
        @ApiResponse(code = 401, message = "NOT AUTHORIZED"),
        @ApiResponse(code = 404, message = "NOT FOUND")})
public class AuthenticationEndpoint {
    private final AuthenticationManager authenticationManager;
    private final TokenProvider jwtTokenUtil;
    private final UserService userService;

    public AuthenticationEndpoint(AuthenticationManager authenticationManager,
                                  TokenProvider jwtTokenUtil,
                                  UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }


    /**
     * @param loginUser loginUser
     * @return ResponseEntity status Ok with AuthToken authToken orElse ResponseEntity status NOT_FOUND
     * @throws AuthenticationException authenticationException
     */
    @ApiOperation(httpMethod = "GET", value = "Get token with user", response = AuthToken.class)
    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity<?> register(@ApiParam(name = "loginUser", required = true, value = "Phone number and password")
                                      @Valid @RequestBody LoginUser loginUser)
            throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        User one = userService.findOne(loginUser.getUsername());
        if (one != null) {
            AuthToken authToken = new AuthToken(token, one);
            return ResponseEntity.status(HttpStatus.OK).body(authToken);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
