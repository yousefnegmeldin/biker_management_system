package com.brightskies.biker_system.exception.model;

public class CartItemNotFoundException extends RuntimeException {

    public CartItemNotFoundException(Long id) {
        super("Cart item with id: " + id + " not found");
    }
}
