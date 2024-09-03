package com.brightskies.biker_system.store.controller;

import com.brightskies.biker_system.store.DTO.StoreDTO;
import com.brightskies.biker_system.store.mapper.StoreConverter;
import com.brightskies.biker_system.store.model.Store;
import com.brightskies.biker_system.store.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store")
public class StoreController
{
    private StoreService storeService;
    private StoreConverter storeConverter;

    @Autowired
    public StoreController(StoreService storeService, StoreConverter storeConverter) {
        this.storeService = storeService;
        this.storeConverter = storeConverter;
    }
    @Operation(summary = "Add a new store",
            description = "Add a new store to the system. Accessible by ADMIN role.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<StoreDTO> addStore(@RequestBody StoreDTO storeDTO)
    {
        Store newstoreDTO = storeConverter.toEntity(storeDTO);
        Store store = storeService.addStore(newstoreDTO);
        StoreDTO newDto = storeConverter.toDTO(store);
        return ResponseEntity.ok(newDto);
    }
    @Operation(summary = "Delete a store by ID",
            description = "Delete a store from the system by its ID. Accessible by ADMIN role.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStore(@PathVariable Long id)
    {
        storeService.deleteStore(id);
        return ResponseEntity.ok("Store is deleted");
    }



}
