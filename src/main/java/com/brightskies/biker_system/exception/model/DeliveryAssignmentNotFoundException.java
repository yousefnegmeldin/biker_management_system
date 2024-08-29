package com.brightskies.biker_system.exception.model;

public class DeliveryAssignmentNotFoundException extends DeliveryAssignmentException {

    public DeliveryAssignmentNotFoundException(Long Id){
        super("No delivery assignment found for biker with Id: " + Id);
    }
}
