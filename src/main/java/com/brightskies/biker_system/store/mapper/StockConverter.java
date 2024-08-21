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

    public StockDTO toDTO(Stock stock) {
        StockDTO dto = new StockDTO();
        dto.setId(stock.getId());
        dto.setProductId(stock.getProduct().getId());
        dto.setStoreId(stock.getStore().getId());
        dto.setQuantity(stock.getQuantity());
        return dto;
    }

    public Stock toEntity(StockDTO dto) {
        Stock stock = new Stock();
        stock.setId(dto.getId());

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        stock.setProduct(product);

        Store store = storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));
        stock.setStore(store);

        stock.setQuantity(dto.getQuantity());
        return stock;
    }
}