package com.brightskies.biker_system.exception.model;

public class StoreNotFoundException extends RuntimeException{

    public StoreNotFoundException(Long id){
        super("Store Not Found with the specified id: " + id);
    }
}
