package com.brightskies.biker_system.customer.controller;

import com.brightskies.biker_system.customer.dto.AddressDTO;
import com.brightskies.biker_system.customer.dto.AddressMapper;
import com.brightskies.biker_system.customer.dto.UpdateAddressDTO;
import com.brightskies.biker_system.customer.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceNotFoundException;

@RequestMapping("/customer/address")
@RestController
public class AddressController {
    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping()
    public ResponseEntity<?> addAddress(@RequestBody AddressDTO addressDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                AddressMapper.toDTO(
                        addressService.addAddress(addressDTO)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long id) {
        addressService.removeAddress(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateAddressDetails(@PathVariable Long id, @RequestBody UpdateAddressDTO addressDTO) {
        addressService.updateAddressDetails(id, addressDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
