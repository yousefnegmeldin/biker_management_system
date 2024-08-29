package com.brightskies.biker_system.exception.model;

public class StockQuantityExceededException extends RuntimeException{

    public StockQuantityExceededException() {
        super("Requested quantity is greater than product stock quantity.");
    }
}
