package com.brightskies.biker_system.store.service;

import com.brightskies.biker_system.order.model.CartItem;
import com.brightskies.biker_system.store.model.Stock;
import com.brightskies.biker_system.store.repository.ProductRepository;
import com.brightskies.biker_system.store.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    private final StockRepository stockRepository;
    private final ProductRepository productRepository;

    @Autowired
    public StockService(StockRepository stockRepository,
                        ProductRepository productRepository) {
        this.stockRepository = stockRepository;
        this.productRepository = productRepository;
    }

    public Stock addStock(Stock stock) {
        Stock stock2 = stockRepository.save(stock);
        return stock2;
    }

    public int getProductQuantity (CartItem cartItem) {
        return stockRepository.findByProdID(
                        productRepository.selectById(cartItem.getProduct().getId())
                                .getId())
                .getQuantity();
    }

    public void setProductQuantity(CartItem cartItem, int incdec) {
        stockRepository.findByProdID(
                        productRepository.selectById(cartItem.getProduct().getId())
                                .getId())
                .setQuantity(incdec);
    }
}