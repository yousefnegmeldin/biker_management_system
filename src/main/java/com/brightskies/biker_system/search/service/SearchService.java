package com.brightskies.biker_system.search.service;

import com.brightskies.biker_system.biker.model.Biker;
import com.brightskies.biker_system.biker.repository.BikerRepository;
import com.brightskies.biker_system.customer.model.Customer;
import com.brightskies.biker_system.customer.repository.CustomerRepository;
import com.brightskies.biker_system.generalmodels.User;
import com.brightskies.biker_system.generalrepositories.UserRepository;
import com.brightskies.biker_system.store.model.Product;
import com.brightskies.biker_system.store.model.Store;
import com.brightskies.biker_system.store.repository.ProductRepository;
import com.brightskies.biker_system.store.repository.StockRepository;
import com.brightskies.biker_system.store.repository.StoreRepository;
import com.brightskies.biker_system.store.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SearchService {

    UserRepository userRepository;
    ProductRepository productRepository;
    StoreRepository storeRepository;
    BikerRepository bikerRepository;
    CustomerRepository customerRepository;
    StockRepository stockRepository;
    StockService stockService;

    @Autowired
    public SearchService(UserRepository userRepository,
                         ProductRepository productRepository,
                         StoreRepository storeRepository,
                         BikerRepository bikerRepository,
                         CustomerRepository customerRepository,
                         StockService stockService) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
        this.customerRepository = customerRepository;
        this.stockService = stockService;
    }

    public List<User> searchForUsersByName(String name) {
        return userRepository.findUsersByName(name);
    }

    public List<Product> searchForProducts(String name) {
        return productRepository.findProductsByName(name);
    }

    public List<Store> searchForStore(String name){
        return storeRepository.findStoreByName(name);
    }

    public List<Biker> getAllBikers(){
        return bikerRepository.findAll();
    }

    public List<Biker> getBikerByName(String name){
        return bikerRepository.findBikersByName(name);
    }

    public List<Product> searchForProductsByCategory(String category){
        return productRepository.findByCategory(category);
    }

    public Product searchForProductByBarcode(String barcode){
        return productRepository.findByBarcode(barcode);
    }

    public Optional<Product> findProductById(Long id){
        return productRepository.findById(id);
    }

    public Optional<Customer> findCustomerById(Long id){
        return customerRepository.findById(id);
    }


    public int findQuantityOfProductInStock(Long productId, Long store_id){
        return stockService.getProductQuantity(productId)
    }
    
}
