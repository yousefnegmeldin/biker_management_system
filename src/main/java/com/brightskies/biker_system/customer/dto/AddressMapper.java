package com.brightskies.biker_system.customer.dto;

import com.brightskies.biker_system.customer.model.Address;

public class AddressMapper {
    public static AddressDTO toDTO (Address address) {
        return new AddressDTO(
                address.getLabel(),
                address.getCity(),
                address.getStreet(),
                address.getApartment()
        );
    }
}
