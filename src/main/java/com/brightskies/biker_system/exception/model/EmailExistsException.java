package com.brightskies.biker_system.exception.model;

public class EmailExistsException extends RuntimeException{
    public EmailExistsException(String message){
        super(message);
    }

}
