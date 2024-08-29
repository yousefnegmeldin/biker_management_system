package com.brightskies.biker_system.exception.model;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Long id) {
        super("Customer Not Found with the specified id: " + id);
    }
}
