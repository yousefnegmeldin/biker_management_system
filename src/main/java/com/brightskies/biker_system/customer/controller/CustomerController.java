package com.brightskies.biker_system.customer.controller;

import com.brightskies.biker_system.customer.dto.AddressDTO;
import com.brightskies.biker_system.customer.dto.UpdateAddressDTO;
import com.brightskies.biker_system.customer.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceNotFoundException;

@RequestMapping("/customer/address")
@RestController
public class CustomerController {
    private AddressService addressService;

    @Autowired
    public CustomerController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping()
    public ResponseEntity<?> addAddress(@RequestBody AddressDTO addressDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.addAddress(addressDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long id) {
        try {
            addressService.removeAddress(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch(InstanceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateAddressDetails(@PathVariable Long id, @RequestBody UpdateAddressDTO addressDTO) {
        try {
            addressService.updateAddressDetails(id, addressDTO);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch(InstanceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }
}
