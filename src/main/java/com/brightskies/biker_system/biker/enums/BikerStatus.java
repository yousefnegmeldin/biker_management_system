package com.brightskies.biker_system.biker.enums;

public enum BikerStatus {
    unavailable("unavailable"),
    busy("busy"),
    available("available");

    private String name;
    BikerStatus(String name) {
        this.name = name;
    }
}
