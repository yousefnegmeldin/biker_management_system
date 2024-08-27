package com.brightskies.biker_system.customer.dto;

import com.brightskies.biker_system.general.enums.Zone;

public record AddressDTO(String label, Zone zone, String city, String street, String apartment){
}
