package com.brightskies.biker_system.manager.controller;

import com.brightskies.biker_system.biker.dto.BikerDto;
import com.brightskies.biker_system.biker.enums.BikerStatus;
import com.brightskies.biker_system.biker.mapper.BikerMapper;
import com.brightskies.biker_system.biker.model.Biker;
import com.brightskies.biker_system.biker.service.BikerService;
import com.brightskies.biker_system.feedback.dto.FeedBackDTO;
import com.brightskies.biker_system.feedback.dto.ViewFeedBackDTO;
import com.brightskies.biker_system.feedback.service.FeedBackService;
import com.brightskies.biker_system.manager.service.ManagerService;
import com.brightskies.biker_system.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/manager")
@PreAuthorize("hasAnyRole('ROLE_admin','ROLE_manager')")
public class ManagerController {
    SearchService searchService;
    BikerService bikerService;
    FeedBackService feedBackService;
    ManagerService managerService;

    @Autowired
    public ManagerController(SearchService searchService,
                             BikerService bikerService,
                             FeedBackService feedBackService,
                             ManagerService managerService) {
        this.searchService = searchService;
        this.bikerService = bikerService;
        this.feedBackService = feedBackService;
        this.managerService = managerService;
    }

    @Operation(summary = "Get available bikers",
            description = "Get a list of bikers who are currently available. Accessible by ADMIN and MANAGER roles.")
    @GetMapping("/available-bikers")
    public ResponseEntity<List<BikerDto>> getAvailableBikers() {
        List<Biker> bikers = searchService.getBikersByStatus(BikerStatus.available);
        List<BikerDto> bikerDtos = bikers
                .stream()
                .map(BikerMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(bikerDtos);
    }

    @Operation(summary = "Update biker information",
            description = "Update the information of a biker. Accessible by ADMIN and MANAGER roles.")
    @PatchMapping("/biker/{id}")
    public ResponseEntity<?> updateBiker(@PathVariable Long id, @RequestBody BikerDto bikerDTO) throws Exception {
        bikerService.updateBiker(id, bikerDTO);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a biker",
            description = "Delete a biker by their ID. Accessible by ADMIN and MANAGER roles.")
    @DeleteMapping("/biker/{id}")
    public ResponseEntity<?> deleteBiker(@PathVariable Long id) {
        bikerService.deleteBiker(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get feedback for a biker",
            description = "Get all feedback for a specific biker. Accessible by ADMIN and MANAGER roles.")
    @GetMapping("/biker/feedback/{bikerId}")
    public ResponseEntity<?> getFeedback(@PathVariable Long bikerId) {
        return ResponseEntity.status(HttpStatus.OK).body(feedBackService.allBikerFeedback(bikerId));
    }

    @Operation(summary = "Assign a biker to an order",
            description = "Assign a biker to a specific order. Accessible by ADMIN and MANAGER roles.")
    @PostMapping("/assign-biker")
    public ResponseEntity<?> assignBikerToOrder(@RequestParam Long bikerId, @RequestParam Long orderId) {
        managerService.assignBikerToOrder(bikerId, orderId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}