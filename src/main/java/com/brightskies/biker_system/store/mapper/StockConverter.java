package com.brightskies.biker_system.store.mapper;

import com.brightskies.biker_system.store.DTO.StockDTO;
import com.brightskies.biker_system.store.model.Product;
import com.brightskies.biker_system.store.model.Stock;
import com.brightskies.biker_system.store.model.Store;
import com.brightskies.biker_system.store.repository.ProductRepository;
import com.brightskies.biker_system.store.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockConverter {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    @Autowired
    public StockConverter(ProductRepository productRepository, StoreRepository storeRepository) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
    }

    public StockDTO toDTO(Stock stock)
    {
        return new StockDTO
                (
                        stock.getStore().getId(),
                        stock.getProduct().getId(),
                        stock.getQuantity()
                );
    }

    public Stock toEntity(StockDTO dto) {
        Stock stock = new Stock();

        Product product = productRepository.findById(dto.productId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        stock.setProduct(product);

        Store store = storeRepository.findById(dto.storeId())
                .orElseThrow(() -> new RuntimeException("Store not found"));
        stock.setStore(store);

        stock.setQuantity(dto.quantity());
        return stock;
    }
}