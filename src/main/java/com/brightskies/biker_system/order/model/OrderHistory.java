package com.brightskies.biker_system.order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
public class OrderHistory
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String orderId;
    @Column(nullable = false)
    String productId;
    @Column(nullable = false)
    int quantity;

    public OrderHistory(){}

    public OrderHistory(int quantity, String productId, String orderId) {
        this.quantity = quantity;
        this.productId = productId;
        this.orderId = orderId;
    }

    public String getId() {
        return orderId;
    }

    public void setId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
