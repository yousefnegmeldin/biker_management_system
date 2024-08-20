package com.brightskies.biker_system.authentication;

import com.brightskies.biker_system.authentication.dto.LoginResponseDTO;
import com.brightskies.biker_system.authentication.dto.LoginUserDTO;
import com.brightskies.biker_system.authentication.dto.RegisterUserDTO;
import com.brightskies.biker_system.authentication.dto.UserDTO;
import com.brightskies.biker_system.authentication.mapper.UserMapper;
import com.brightskies.biker_system.authentication.service.AuthenticationService;
import com.brightskies.biker_system.authentication.service.JwtService;
import com.brightskies.biker_system.generalmodels.User;
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
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponseDTO loginResponse = new LoginResponseDTO(jwtToken,jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}