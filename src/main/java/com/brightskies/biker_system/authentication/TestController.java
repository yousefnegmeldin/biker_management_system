package com.brightskies.biker_system.Authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public static ResponseEntity<?> admin(){
        return ResponseEntity.ok("Admin");
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/manager")
    public static ResponseEntity<?> manager(){
        return ResponseEntity.ok("Manager");

    }
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("/customer")
    public static ResponseEntity<?> customer(){
        return ResponseEntity.ok("Customer");

    }
    @PreAuthorize("hasRole('ROLE_BIKER')")
    @GetMapping("/biker")
    public static ResponseEntity<?> biker(){
        return ResponseEntity.ok("biker");

    }
}
