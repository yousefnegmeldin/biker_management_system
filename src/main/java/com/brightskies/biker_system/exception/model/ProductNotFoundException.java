package com.brightskies.biker_system.exception.model;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super("Product Not Found with the specified id: " + id);
    }

}
