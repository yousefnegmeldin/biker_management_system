package com.brightskies.biker_system.store.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Stock {

    @EmbeddedId
    private StockId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("storeId")
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    // Ensure StockId is properly initialized
    public void setStore(Store store) {
        this.store = store;
        if (this.id == null) {
            this.id = new StockId();
        }
        this.id.setStoreId(store.getId());
    }

    public void setProduct(Product product) {
        this.product = product;
        if (this.id == null) {
            this.id = new StockId();
        }
        this.id.setProductId(product.getId());
    }
}