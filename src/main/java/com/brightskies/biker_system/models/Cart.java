package com.brightskies.biker_system.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Cart
{
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customerId")
    private Customer customer;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="productId",nullable = false)
    private Product product;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="storeId",nullable = false)
    private Store store;
    @Column(nullable = false)
    private int quantity;

    public Cart(){}

    public Cart(Customer customer, Product product, Store store, int quantity) {
        this.customer = customer;
        this.product=product;
        this.store=store;
        this.quantity = quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
