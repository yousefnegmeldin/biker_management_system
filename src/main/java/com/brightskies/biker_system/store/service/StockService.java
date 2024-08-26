package com.brightskies.biker_system.store.service;

import com.brightskies.biker_system.order.model.CartItem;
import com.brightskies.biker_system.store.model.Stock;
import com.brightskies.biker_system.store.repository.ProductRepository;
import com.brightskies.biker_system.store.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

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
            return stockRepository.save(stock);
    }


    // you have to specify a store here
    //

    ///
    public int getProductQuantity (CartItem cartItem) {
        return stockRepository.findByProdID(
                        productRepository.selectById(cartItem.getProduct().getId())
                                .getId())
                .getQuantity();
    }

    public int getProductQuantity (Long productId) {
        return stockRepository.findByProdID(productId).getQuantity();
    }

    public void setProductQuantity(CartItem cartItem, int incdec) {
        stockRepository.findByProdID(
                        productRepository.selectById(cartItem.getProduct().getId())
                                .getId())
                .setQuantity(incdec);
    }
}