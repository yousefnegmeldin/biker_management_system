package com.brightskies.biker_system.store.service;

import com.brightskies.biker_system.store.model.Product;
import com.brightskies.biker_system.store.model.Store;
import com.brightskies.biker_system.store.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void deleteProductById(Long id)
    {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Store not found"));
        productRepository.deleteById(id);
    }

}
