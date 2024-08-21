package com.brightskies.biker_system.order.controller;

import com.brightskies.biker_system.authentication.utility.SecurityUtils;
import com.brightskies.biker_system.generalmodels.User;
import com.brightskies.biker_system.generalrepositories.UserRepository;
import com.brightskies.biker_system.order.model.Cart;
import com.brightskies.biker_system.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<Cart> addItemToCart(@RequestParam Long productId, @RequestParam int quantity) {
        User user = userRepository.findById(SecurityUtils.getCurrentUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartService.addItemToCart(user, productId, quantity);
        return ResponseEntity.ok(cart);
    }

    @GetMapping
    public ResponseEntity<Cart> getCart() {
        User user = userRepository.findById(SecurityUtils.getCurrentUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartService.getCartByUser(user);
        return ResponseEntity.ok(cart);
    }

    // Other endpoints for cart management
}

