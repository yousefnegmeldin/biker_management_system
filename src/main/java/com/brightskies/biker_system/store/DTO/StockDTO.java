package com.brightskies.biker_system.store.DTO;

import com.brightskies.biker_system.store.model.Product;
import com.brightskies.biker_system.store.model.StockId;
import com.brightskies.biker_system.store.model.Store;

public class StockDTO
{
    private StockId id;
    private Store store;
    private Product product;
    private int quantity;

    public StockId getId() {
        return id;
    }

    public void setId(StockId id) {
        this.id = id;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
