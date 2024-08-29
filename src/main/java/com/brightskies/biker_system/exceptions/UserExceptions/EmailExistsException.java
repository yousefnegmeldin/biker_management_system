package com.brightskies.biker_system.exceptions.UserExceptions;

public class EmailExistsException extends Exception{
    public EmailExistsException(){
        super("Email Already signed up in application");
    }
}
