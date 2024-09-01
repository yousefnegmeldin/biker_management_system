package com.brightskies.biker_system.manager.controller;

import com.brightskies.biker_system.biker.dto.BikerDto;
import com.brightskies.biker_system.biker.enums.BikerStatus;
import com.brightskies.biker_system.biker.mapper.BikerMapper;
import com.brightskies.biker_system.biker.model.Biker;
import com.brightskies.biker_system.biker.service.BikerService;
import com.brightskies.biker_system.feedback.service.FeedBackService;
import com.brightskies.biker_system.manager.service.ManagerService;
import com.brightskies.biker_system.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/manager")
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
public class ManagerController {
    SearchService searchService;
    BikerService bikerService;
    FeedBackService feedBackService;
    ManagerService managerService;

    @Autowired
    public ManagerController(SearchService searchService,
                             BikerService bikerService,
                             FeedBackService feedBackService,
                             ManagerService managerService
                             ) {
        this.searchService = searchService;
        this.bikerService = bikerService;
        this.feedBackService = feedBackService;
        this.managerService = managerService;
    }

    @GetMapping("/available-bikers")
    public ResponseEntity<List<BikerDto>> getAvailableBikers(){
        List<Biker> bikers = searchService.getBikersByStatus(BikerStatus.available);

        List<BikerDto> bikerDtos = bikers
                .stream()
                .map(BikerMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(bikerDtos);
    }

    //add validation for updating fields, like email
    @PatchMapping("/biker/{id}")
    public ResponseEntity<?> updateBiker(@PathVariable Long id, @RequestBody BikerDto bikerDTO){
        bikerService.updateBiker(id, bikerDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/biker/{id}")
    public ResponseEntity<?> deleteBiker(@PathVariable Long id){
        bikerService.deleteBiker(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/biker/feedback/{bikerId}")
    public ResponseEntity<?> getFeedback(@PathVariable Long bikerId) {
        return ResponseEntity.status(HttpStatus.OK).body(feedBackService.allBikerFeedback(bikerId));
    }

    @PostMapping("/assign-biker")
    public ResponseEntity<?> assignBikerToOrder(@RequestParam Long bikerId, @RequestParam Long orderId) {
        managerService.assignBikerToOrder(bikerId, orderId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
