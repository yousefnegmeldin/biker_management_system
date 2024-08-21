package com.brightskies.biker_system.authentication.controller;

import com.brightskies.biker_system.authentication.dto.*;
import com.brightskies.biker_system.authentication.mapper.UserMapper;
import com.brightskies.biker_system.authentication.service.AuthenticationService;
import com.brightskies.biker_system.authentication.service.JwtService;
import com.brightskies.biker_system.customer.service.CustomerService;
import com.brightskies.biker_system.generalmodels.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final CustomerService customerService;

    @Autowired
    public AuthController(JwtService jwtService, AuthenticationService authenticationService, CustomerService customerService){
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.customerService = customerService;
    }

    @PostMapping("/signup/customer")
    public ResponseEntity<UserDTO> register(@RequestBody RegisterCustomerDTO registerCustomerDto) {
        User registeredUser = authenticationService.signUpCustomer(registerCustomerDto);
        return ResponseEntity.ok(UserMapper.toUserDTO(registeredUser));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @PostMapping("/signup/biker")
    public ResponseEntity<UserDTO> registerBiker(@RequestBody RegisterBikerDTO registerBikerDto) {
        User registeredUser = authenticationService.signUpBiker(registerBikerDto);
        return ResponseEntity.ok(UserMapper.toUserDTO(registeredUser));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/signup/manager")
    public ResponseEntity<UserDTO> registerManager(@RequestBody RegisterManagerDTO registerManagerDto) {
        User registeredUser = authenticationService.signUpManager(registerManagerDto);
        return ResponseEntity.ok(UserMapper.toUserDTO(registeredUser));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> authenticate(@RequestBody LoginUserDTO loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        customerService.updateLastLogin(authenticatedUser);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponseDTO loginResponse = new LoginResponseDTO(jwtToken, jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}
