package com.brightskies.biker_system.store.repository;

import com.brightskies.biker_system.store.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("SELECT s FROM Stock s WHERE s.product.id = :prodId AND s.store.id = :storeId")
    public Stock findByProdIdAndStoreId(Long prodId , Long storeId);
    //public Stock updateStock(Stock stock);

    //Stock updateStock(Long storeId, Long productId, int quantity);
}
