package com.brightskies.biker_system.exception.model;

public class AddressLabelRepeatedException extends RuntimeException {

    public AddressLabelRepeatedException() {
        super("Customer already has an address with that label");
    }
}

