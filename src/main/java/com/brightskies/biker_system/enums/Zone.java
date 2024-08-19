package com.brightskies.biker_system.enums;

public enum Zone {
    SheikhZayed("sheikh zayed"),
    October("october"),
    Zamalek("zamalek"),
    Mohandessin("mohandessin"),
    Dokki("dokki"),
    Downtown("downtown"),
    Maadi("maadi"),
    NasrCity("nasr city"),
    Rehab("rehab"),
    FifthSettlement("fifth settlement");

    private String name;
    Zone(String name) {
        this.name = name;
    }
}
