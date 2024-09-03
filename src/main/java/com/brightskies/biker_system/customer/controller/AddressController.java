package com.brightskies.biker_system.customer.controller;

import com.brightskies.biker_system.customer.dto.AddressDTO;
import com.brightskies.biker_system.customer.dto.AddressMapper;
import com.brightskies.biker_system.customer.dto.UpdateAddressDTO;
import com.brightskies.biker_system.customer.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceNotFoundException;

@RequestMapping("/customer/address")
@RestController
@PreAuthorize("hasAnyRole('ROLE_customer')")
@Tag(name = "Address Controller", description = "API for managing customer addresses. Accessible by role customer.")
public class AddressController {
    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Operation(summary = "Adds a new address", description = "Customer cannot have repeated address labels.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content =
                    { @Content(mediaType = "application/json", schema = @Schema(implementation = AddressDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Address label is repeated", content =
                    { @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    { @Content(mediaType = "application/json") })
    })
    @PostMapping()
    public ResponseEntity<?> addAddress(@RequestBody AddressDTO addressDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                AddressMapper.toDTO(
                        addressService.addAddress(addressDTO)
                )
        );
    }

    @Operation(summary = "Deletes an address", description = "Deletes an address by its respective ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted"),
            @ApiResponse(responseCode = "404", description = "Address does not exist"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    { @Content(mediaType = "application/json") })
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long id) {
        addressService.removeAddress(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Updates an address", description = "Updates address details by its respective ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated"),
            @ApiResponse(responseCode = "404", description = "Address does not exist"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    { @Content(mediaType = "application/json") })
    })
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateAddressDetails(@PathVariable Long id, @RequestBody UpdateAddressDTO addressDTO) {
        addressService.updateAddressDetails(id, addressDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
