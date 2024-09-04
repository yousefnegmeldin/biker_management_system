package com.brightskies.biker_system.exception.model;

public class CartItemAlreadyExistsException extends Exception {
    public CartItemAlreadyExistsException(String message) {
        super(message);
    }
}
