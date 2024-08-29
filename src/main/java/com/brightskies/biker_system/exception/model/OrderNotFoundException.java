package com.brightskies.biker_system.exception.model;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Long Id) {
        super("Order with id " + Id + " does not exist");
    }
}
