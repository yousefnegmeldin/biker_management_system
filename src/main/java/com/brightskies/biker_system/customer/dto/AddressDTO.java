package com.brightskies.biker_system.customer.dto;

import com.brightskies.biker_system.general.enums.Zone;
import jakarta.validation.constraints.NotNull;

public record AddressDTO(@NotNull String label,@NotNull Zone zone,@NotNull String city,@NotNull String street, String apartment){
}
