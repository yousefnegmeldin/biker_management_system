package com.brightskies.biker_system.store.service;

import com.brightskies.biker_system.order.model.CartItem;
import com.brightskies.biker_system.store.model.Stock;
import com.brightskies.biker_system.store.model.Store;
import com.brightskies.biker_system.store.repository.ProductRepository;
import com.brightskies.biker_system.store.repository.StockRepository;
import com.brightskies.biker_system.store.repository.StoreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

@Service
public class StockService {

    private final StockRepository stockRepository;
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    @Autowired
    public StockService(StockRepository stockRepository,
                        ProductRepository productRepository,
                        StoreRepository storeRepository) {
        this.stockRepository = stockRepository;
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
    }

    public Stock addStock(Stock stock) {
        Stock stock2 = null;
        try{
            stock2 = stockRepository.save(stock);
            return stock2;
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return stock2;
    }

    public int getProductQuantity (Long productId, Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(()->new EntityNotFoundException("Store not found"));
        return stockRepository.findByProdIdAndStoreId(
                        productRepository.selectById(productId)
                                .getId(), store.getId())
                .getQuantity();
    }

    public void setProductQuantity(Long productId, int incdec, Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(()->new EntityNotFoundException("Store not found"));
        Stock stock = stockRepository.findByProdIdAndStoreId(
                        productRepository.selectById(productId)
                                .getId(),store.getId());
        stock.setQuantity(incdec);
        stockRepository.save(stock);
    }
}