package com.brightskies.biker_system.search.service;

import com.brightskies.biker_system.biker.model.Biker;
import com.brightskies.biker_system.biker.repository.BikerRepository;
import com.brightskies.biker_system.generalmodels.User;
import com.brightskies.biker_system.generalrepositories.UserRepository;
import com.brightskies.biker_system.store.model.Product;
import com.brightskies.biker_system.store.model.Store;
import com.brightskies.biker_system.store.repository.ProductRepository;
import com.brightskies.biker_system.store.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SearchService {

    UserRepository userRepository;
    ProductRepository productRepository;
    StoreRepository storeRepository;
    BikerRepository bikerRepository;

    @Autowired
    public SearchService(UserRepository userRepository,
                         ProductRepository productRepository,
                         StoreRepository storeRepository,
                         BikerRepository bikerRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
    }

    public List<User> searchForUsers(String name) {
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

    /*public List<Biker> getBikerByName(){
        return bikerRepository.findBikerByName();
    }*/

    
}
