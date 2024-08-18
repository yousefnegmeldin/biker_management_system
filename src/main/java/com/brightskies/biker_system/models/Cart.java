package com.brightskies.biker_system.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Cart
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String costumerId;
    @Column(nullable = false)
    String productId;
    @Column(nullable = false)
    String storeId;
    @Column(nullable = false)
    int quantity;

    public Cart(){}

    public Cart(String costumerId, String productId, String storeId, int quantity) {
        this.costumerId = costumerId;
        this.productId = productId;
        this.storeId = storeId;
        this.quantity = quantity;
    }

    public String getCostumerId() {
        return costumerId;
    }

    public void setCostumerId(String costumerId) {
        this.costumerId = costumerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
