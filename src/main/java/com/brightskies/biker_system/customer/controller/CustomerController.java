package com.brightskies.biker_system.customer.controller;

import com.brightskies.biker_system.customer.dto.AddressDTO;
import com.brightskies.biker_system.feedback.dto.FeedBackDTO;
import com.brightskies.biker_system.feedback.dto.FeedBackMapper;
import com.brightskies.biker_system.feedback.service.FeedBackService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
@Tag(name = "Customer Controller", description = "API for managing customer operations. Accessible by role customer.")
public class CustomerController {
    private FeedBackService feedbackService;

    @Autowired
    public CustomerController(FeedBackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @Operation(summary = "Provides feedback for order", description = "Customer provides a rating out of 5 and an optional text as feedback for the order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content =
                    { @Content(mediaType = "application/json", schema = @Schema(implementation = FeedBackDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Customer did not place the specified order", content =
                    { @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "404", description = "Order does not exist", content =
                    { @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "406", description = "Order has not been assigned/delivered, nothing to rate", content =
                    { @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    { @Content(mediaType = "application/json") })
    })
    @PostMapping("/feedback")
    public ResponseEntity<?> addFeedback(@RequestBody FeedBackDTO feedbackDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                FeedBackMapper.toDTO(
                        feedbackService.addFeedback(feedbackDTO)
                )
        );
    }
}
