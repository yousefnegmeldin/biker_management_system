package com.brightskies.biker_system.store.controller;

import com.brightskies.biker_system.store.DTO.StockDTO;
import com.brightskies.biker_system.store.mapper.StockConverter;
import com.brightskies.biker_system.store.model.Stock;
import com.brightskies.biker_system.store.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
public class StockController
{
    private final StockService stockService;
    private final StockConverter stockConverter;

    @Autowired
    public StockController(StockService stockService, StockConverter stockConverter) {
        this.stockService = stockService;
        this.stockConverter = stockConverter;
    }
    @Operation(summary = "Add a new stock",
            description = "Add a new stock to the store. Accessible by MANAGER and ADMIN roles.")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addStock(@RequestBody StockDTO stockDTO)
    {
        Stock stockEntity = stockConverter.toEntity(stockDTO);
        Stock stockSaved = stockService.addStock(stockEntity);
        return ResponseEntity.ok(stockConverter.toDTO(stockSaved));
    }

//    @PostMapping("/update stock/{storeId}/{productId}/{quantity}")
//    public Stock updateStock(@PathVariable Long storeId, Long productId, int quantity)
//    {
//        return stockService.updateStock(storeId,productId,quantity);
//    }
}
