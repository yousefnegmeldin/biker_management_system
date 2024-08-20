package com.brightskies.biker_system.authentication.service;

import com.brightskies.biker_system.authentication.dto.LoginUserDTO;
import com.brightskies.biker_system.authentication.dto.RegisterUserDTO;
import com.brightskies.biker_system.generalmodels.User;
import com.brightskies.biker_system.generalrepositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    public AuthenticationService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public User signup(RegisterUserDTO input) {
        if (userRepository.existsByEmail(input.email()) && userRepository.existsByPhone(input.phone())) {
            throw new RuntimeException("Email already exists");
        }
        if (userRepository.existsByPhone(input.phone())) {
            throw new RuntimeException("Phone already exists");
        }
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
                            input.email(),
                            input.password()
                    )
            );
        return userRepository.findByEmail(input.email())
                .orElseThrow(() -> {
                    return new RuntimeException("User not found");
                });
    }
}