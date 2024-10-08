package com.brightskies.biker_system.order.model;

import com.brightskies.biker_system.store.model.Product;
import com.brightskies.biker_system.store.model.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderHistory
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id" , nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id" , nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "store_id" , nullable = false)
    private Store store;

    public OrderHistory(Order order, Product product, Integer quantity,Store store) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.store = store;
    }
}
