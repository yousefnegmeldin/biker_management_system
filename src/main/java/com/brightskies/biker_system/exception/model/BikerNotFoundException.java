package com.brightskies.biker_system.exception.model;

public class BikerNotFoundException extends RuntimeException {

    public BikerNotFoundException(Long id) {
        super("Biker Not Found with the specified id: " + id);
    }

}
