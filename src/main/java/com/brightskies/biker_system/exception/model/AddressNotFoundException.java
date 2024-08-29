package com.brightskies.biker_system.exception.model;

public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException(Long id) {
        super("Address with Id: " + id + " doesn't exist");
    }
}
