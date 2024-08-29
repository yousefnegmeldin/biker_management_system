package com.brightskies.biker_system.store.service;
import com.brightskies.biker_system.exception.model.StoreNotFoundException;
import com.brightskies.biker_system.store.model.Stock;
import com.brightskies.biker_system.store.model.Store;
import com.brightskies.biker_system.store.repository.ProductRepository;
import com.brightskies.biker_system.store.repository.StockRepository;
import com.brightskies.biker_system.store.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    //add code to update quantity and not upsert it
    public Stock addStock(Stock stock) {
            return stockRepository.save(stock);
    }

    public int getProductQuantity (Long productId, Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(()->new StoreNotFoundException(storeId));
        return stockRepository.findByProdIdAndStoreId(
                        productRepository.selectById(productId)
                                .getId(), store.getId())
                .getQuantity();
    }

    public void setProductQuantity(Long productId, int incdec, Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(()->new StoreNotFoundException(storeId));
        Stock stock = stockRepository.findByProdIdAndStoreId(
                        productRepository.selectById(productId)
                                .getId(),store.getId());
        stock.setQuantity(incdec);
        stockRepository.save(stock);
    }
}