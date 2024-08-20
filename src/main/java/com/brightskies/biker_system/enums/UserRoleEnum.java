package com.brightskies.biker_system.enums;

public enum UserRoleEnum {
    customer("customer"),
    biker("biker"),
    manager("manager"),
    admin("admin");
    private String name;
    UserRoleEnum(String name) {
        this.name = name;
    }
}