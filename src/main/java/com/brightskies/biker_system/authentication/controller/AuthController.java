package com.brightskies.biker_system.authentication.controller;

import com.brightskies.biker_system.authentication.dto.*;
import com.brightskies.biker_system.authentication.mapper.UserMapper;
import com.brightskies.biker_system.authentication.service.AuthenticationService;
import com.brightskies.biker_system.authentication.service.JwtService;
import com.brightskies.biker_system.customer.service.CustomerService;
import com.brightskies.biker_system.general.models.User;
import com.brightskies.biker_system.general.enums.UserRole;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Sign up as a customer",
            description = "Sign up as a customer and return the user data accessible to anyone.")
    @PostMapping("/signup/customer")
    public ResponseEntity<UserDTO> register(@RequestBody RegisterCustomerDTO registerCustomerDto) {
        User registeredUser = authenticationService.signUpCustomer(registerCustomerDto);
        return ResponseEntity.ok(UserMapper.toUserDTO(registeredUser));
    }

    @Operation(summary = "Sign up as a biker",
            description = "Sign up as a biker and return the user data. Accessible by ADMIN and MANAGER roles.")
    @PreAuthorize("hasAnyRole('ROLE_admin','ROLE_manager')")
    @PostMapping("/signup/biker")
    public ResponseEntity<UserDTO> registerBiker(@RequestBody RegisterBikerDTO registerBikerDto) {
        User registeredUser = authenticationService.signUpBiker(registerBikerDto);
        return ResponseEntity.ok(UserMapper.toUserDTO(registeredUser));
    }

    @Operation(summary = "Sign up as a manager",
            description = "Sign up as a manager and return the user data. Accessible by ADMIN role.")
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    @PostMapping("/signup/manager")
    public ResponseEntity<UserDTO> registerManager(@RequestBody RegisterManagerDTO registerManagerDto) {
        User registeredUser = authenticationService.signUpManager(registerManagerDto);
        return ResponseEntity.ok(UserMapper.toUserDTO(registeredUser));
    }

    @Operation(summary = "Login",
            description = "Authenticate a user and return a JWT token.")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> authenticate(@RequestBody LoginUserDTO loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        if (authenticatedUser.getRole() == UserRole.customer)
            customerService.updateLastLogin(authenticatedUser);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponseDTO loginResponse = new LoginResponseDTO(jwtToken, jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }

    @Operation(summary = "Sign up as an admin",
            description = "Sign up as an admin and return the user data. Accessible by ADMIN role.")
//    @PreAuthorize("hasAnyRole('ROLE_admin')")
    @PostMapping("/signup/admin")
    public ResponseEntity<UserDTO> registerAdmin(@RequestBody RegisterManagerDTO registerManagerDto) {
        User registeredUser = authenticationService.signupAdmin(registerManagerDto);
        return ResponseEntity.ok(UserMapper.toUserDTO(registeredUser));
    }
}
