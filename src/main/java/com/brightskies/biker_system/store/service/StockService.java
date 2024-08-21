package com.brightskies.biker_system.store.service;

import com.brightskies.biker_system.store.model.Stock;
import com.brightskies.biker_system.store.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService
{
    private StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Stock addStock(Stock stock)
    {
        return stockRepository.save(stock);
    }

    public Stock updateStock(Long storeId, Long productId, int quantity)
    {
        return stockRepository.updateStock(storeId,productId,quantity);
    }

}
