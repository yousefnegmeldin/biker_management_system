package com.brightskies.biker_system.authentication.service;

import com.brightskies.biker_system.authentication.dto.*;
import com.brightskies.biker_system.biker.model.Biker;
import com.brightskies.biker_system.customer.model.Customer;
import com.brightskies.biker_system.general.models.User;
import com.brightskies.biker_system.general.repositories.UserRepository;
import com.brightskies.biker_system.manager.model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

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


    public Customer signUpCustomer(RegisterCustomerDTO input) {
        if (userRepository.existsByEmail(input.email()) && userRepository.existsByPhone(input.phone())) {
            throw new RuntimeException("Email already exists");
        }
        if (userRepository.existsByPhone(input.phone())) {
            throw new RuntimeException("Phone already exists");
        }
        Customer customer = new Customer();
        customer.setEmail(input.email());
        customer.setName(input.firstName() + " " + input.lastName());
        customer.setPassword(passwordEncoder.encode(input.password()));
        customer.setPhone(input.phone());
        customer.setRole(input.role());
        customer.setJoinedAt(LocalDate.now());
        customer.setLastLogin(LocalDate.now());
        return userRepository.save(customer);
    }

    public Biker signUpBiker(RegisterBikerDTO input){
        if (userRepository.existsByEmail(input.email()) && userRepository.existsByPhone(input.phone())) {
            throw new RuntimeException("Email already exists");
        }
        if (userRepository.existsByPhone(input.phone())) {
            throw new RuntimeException("Phone already exists");
        }
        Biker biker = new Biker();
        biker.setEmail(input.email());
        biker.setName(input.firstName() + " " + input.lastName());
        biker.setPassword(passwordEncoder.encode(input.password()));
        biker.setPhone(input.phone());
        biker.setRole(input.role());
        biker.setJoinedAt(LocalDate.now());
        biker.setStatus(input.status());
        biker.setZone(input.zone());
        return userRepository.save(biker);
    }

    public Manager signUpManager(RegisterManagerDTO input){
        if (userRepository.existsByEmail(input.email()) && userRepository.existsByPhone(input.phone())) {
            throw new RuntimeException("Email already exists");
        }
        if (userRepository.existsByPhone(input.phone())) {
            throw new RuntimeException("Phone already exists");
        }
        Manager manager = new Manager();
        manager.setEmail(input.email());
        manager.setName(input.firstName() + " " + input.lastName());
        manager.setPassword(passwordEncoder.encode(input.password()));
        manager.setPhone(input.phone());
        manager.setRole(input.role());
        manager.setJoinedAt(LocalDate.now());
        return userRepository.save(manager);
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