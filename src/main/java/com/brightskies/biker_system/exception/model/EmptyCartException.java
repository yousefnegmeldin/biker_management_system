package com.brightskies.biker_system.exception.model;

public class EmptyCartException extends RuntimeException {

    public EmptyCartException() {
        super("Cart is empty");
    }

}
