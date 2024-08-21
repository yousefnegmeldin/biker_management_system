package com.brightskies.biker_system.store.controller;

import com.brightskies.biker_system.store.DTO.ProductDTO;
import com.brightskies.biker_system.store.DTO.StockDTO;
import com.brightskies.biker_system.store.mapper.StockConverter;
import com.brightskies.biker_system.store.model.Product;
import com.brightskies.biker_system.store.model.Stock;
import com.brightskies.biker_system.store.model.Store;
import com.brightskies.biker_system.store.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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