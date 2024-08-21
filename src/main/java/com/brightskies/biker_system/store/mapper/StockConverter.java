package com.brightskies.biker_system.store.mapper;

import com.brightskies.biker_system.store.DTO.StockDTO;
import com.brightskies.biker_system.store.model.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockConverter
{
    public StockDTO toDTO(Stock stock)
    {
        StockDTO dto = new StockDTO();
        dto.setId(stock.getId());
        dto.setProduct(stock.getProduct());
        dto.setStore(stock.getStore());
        dto.setQuantity(stock.getQuantity());
        return dto;
    }

    public Stock toEntity(StockDTO dto)
    {
        Stock stock = new Stock();
        stock.setId(dto.getId());
        stock.setProduct(dto.getProduct());
        stock.setStore(dto.getStore());
        stock.setQuantity(dto.getQuantity());
        return stock;
    }
}
