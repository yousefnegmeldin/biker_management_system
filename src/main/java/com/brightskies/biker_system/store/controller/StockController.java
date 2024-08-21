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
    private StockService stockService;
    private StockConverter stockConverter;

    @Autowired
    public StockController(StockService stockService, StockConverter stockConverter) {
        this.stockService = stockService;
        this.stockConverter = stockConverter;
    }

    @PostMapping("/add_stock")
    public ResponseEntity<StockDTO> addStock(@RequestBody StockDTO stockDTO)
    {
        Stock newstockDTO = stockConverter.toEntity(stockDTO);
        Stock stock = stockService.addStock(newstockDTO);
        StockDTO newDto = stockConverter.toDTO(stock);
        return ResponseEntity.ok(newDto);
    }

//    @PostMapping("/update stock/{storeId}/{productId}/{quantity}")
//    public Stock updateStock(@PathVariable Long storeId, Long productId, int quantity)
//    {
//        return stockService.updateStock(storeId,productId,quantity);
//    }
}
