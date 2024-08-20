package com.brightskies.biker_system.Authentication;

import com.brightskies.biker_system.Authentication.dto.LoginResponseDTO;
import com.brightskies.biker_system.Authentication.dto.LoginUserDTO;
import com.brightskies.biker_system.Authentication.dto.RegisterUserDTO;
import com.brightskies.biker_system.Authentication.dto.UserDTO;
import com.brightskies.biker_system.Authentication.mapper.UserMapper;
import com.brightskies.biker_system.Authentication.service.AuthenticationService;
import com.brightskies.biker_system.Authentication.service.JwtService;
import com.brightskies.biker_system.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    @Autowired
    public AuthController(JwtService jwtService, AuthenticationService authenticationService){
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> register(@RequestBody RegisterUserDTO registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(UserMapper.toUserDTO(registeredUser));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> authenticate(@RequestBody LoginUserDTO loginUserDto) {
        logger.info("Authenticating user with email: {}", loginUserDto.email());
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        logger.info("Authentication successful for user with email: {}", loginUserDto.email());
        String jwtToken = jwtService.generateToken(authenticatedUser);
        logger.info("JWT token generated for user with email: {}", loginUserDto.email());
        LoginResponseDTO loginResponse = new LoginResponseDTO(jwtToken, jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
