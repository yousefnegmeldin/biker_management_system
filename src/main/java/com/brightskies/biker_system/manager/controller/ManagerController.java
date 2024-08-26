package com.brightskies.biker_system.manager.controller;

import com.brightskies.biker_system.biker.dto.BikerDto;
import com.brightskies.biker_system.biker.enums.BikerStatus;
import com.brightskies.biker_system.biker.mapper.BikerMapper;
import com.brightskies.biker_system.biker.model.Biker;
import com.brightskies.biker_system.biker.service.BikerService;
import com.brightskies.biker_system.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    SearchService searchService;
    BikerService bikerService;

    @Autowired
    public ManagerController(SearchService searchService, BikerService bikerService) {
        this.searchService = searchService;
        this.bikerService = bikerService;
    }

    @GetMapping("/available-bikers")
    public ResponseEntity<List<BikerDto>> getAvailableBikers(){

        List<Biker> bikers = searchService.getBikersByStatus(String.valueOf(BikerStatus.available));

        List<BikerDto> bikerDtos = bikers
                .stream()
                .map(BikerMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(bikerDtos);
    }

    @PatchMapping("/biker/{id}")
    public ResponseEntity<?> updateBiker(@PathVariable Long id, @RequestBody BikerDto bikerDTO){
        bikerService.updateBiker(id, bikerDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBiker(@PathVariable Long id){
        bikerService.deleteBiker(id);
        return ResponseEntity.noContent().build();
    }

}
