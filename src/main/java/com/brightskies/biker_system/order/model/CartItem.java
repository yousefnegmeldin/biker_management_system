package com.brightskies.biker_system.order.model;

import com.brightskies.biker_system.customer.model.Customer;
import com.brightskies.biker_system.store.model.Product;
import com.brightskies.biker_system.store.model.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Long quantity;

    //**** to be implemented ****///
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "store_id", nullable = false)
//    private Store store;



    public CartItem(Customer customer, Product product, Long quantity) {
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        //this.store = store;
    }
}