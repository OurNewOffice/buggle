package am.buggle.controller;

import am.buggle.dto.AuthenticationRequest;
import am.buggle.dto.AuthenticationResponse;
import am.buggle.dto.UserDto;
import am.buggle.model.User;
import am.buggle.repository.UserRepository;
import am.buggle.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;

    public UserController(UserRepository userRepository,
                          JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthenticationResponse> auth(@RequestBody AuthenticationRequest authenticationRequest){

        User byPhone = userRepository.findByPhone(authenticationRequest.getPhone());

        if (byPhone!=null && passwordEncoder.matches(authenticationRequest.getPassword(), byPhone.getPassword())){
            String token = jwtTokenUtil.generateToken(byPhone.getPhone());
            return ResponseEntity.status(HttpStatus.OK).body(AuthenticationResponse.builder()
                    .token(token)
                    .userDto(UserDto.builder()
                            .cars(byPhone.getCars())
                            .id(byPhone.getId())
                            .name(byPhone.getName())
                            .phone(byPhone.getPhone())
                            .regDate(byPhone.getRegDate())
                            .userTypes(byPhone.getUserTypes())
                            .build())
                    .build());
        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
