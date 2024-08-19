package com.brightskies.biker_system.Authentication.service;


import com.brightskies.biker_system.Authentication.dto.LoginUserDTO;
import com.brightskies.biker_system.Authentication.dto.RegisterUserDTO;
import com.brightskies.biker_system.GeneralRepositories.UserRepository;

import com.brightskies.biker_system.GeneralModels.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDTO input) {
        User user = new User();
        user.setEmail(input.email());
        user.setName(input.firstName() + " " + input.lastName());
        user.setPassword(passwordEncoder.encode(input.password()));
        user.setPhone(input.phone());
        user.setRole(input.role());
        return userRepository.save(user);
    }

    public User authenticate(LoginUserDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.username(),
                        input.password()
                )
        );

        return userRepository.findByEmail(input.username())
                .orElseThrow();
    }
}