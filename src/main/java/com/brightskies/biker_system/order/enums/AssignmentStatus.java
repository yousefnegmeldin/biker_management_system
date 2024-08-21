package com.brightskies.biker_system.order.enums;

public enum AssignmentStatus {
    pending("pending"),
    picking_up("picking_up"),
    delivering("delivering"),
    delivered("delivered"),
    cancelled("cancelled");

    private String name;
    AssignmentStatus(String name) {
        this.name = name;
    }
}
