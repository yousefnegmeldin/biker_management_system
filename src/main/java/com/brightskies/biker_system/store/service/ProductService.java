package com.brightskies.biker_system.store.service;

import com.brightskies.biker_system.store.model.Product;
import com.brightskies.biker_system.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService
{
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product)
    {
        return productRepository.save(product);
    }


    public void deleteProductByName(String name)
    {
        productRepository.deleteByname(name);
    }

}
