package com.brightskies.biker_system.search.controller;

import com.brightskies.biker_system.biker.enums.Zone;
import com.brightskies.biker_system.biker.model.Biker;
import com.brightskies.biker_system.customer.model.Customer;
import com.brightskies.biker_system.generalmodels.User;
import com.brightskies.biker_system.search.service.SearchService;
import com.brightskies.biker_system.store.model.Product;
import com.brightskies.biker_system.store.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> searchForUsersByName(@RequestParam String name) {
        return ResponseEntity.ok(searchService.searchForUsersByName(name));
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> searchForProducts(@RequestParam String name) {
        return ResponseEntity.ok(searchService.searchForProducts(name));
    }

    @GetMapping("/stores")
    public ResponseEntity<Store> searchForStore(@RequestParam String name) {
        return ResponseEntity.ok(searchService.searchForStore(name));
    }

    @GetMapping("/bikers")
    public ResponseEntity<List<Biker>> getAllBikers() {
        return ResponseEntity.ok(searchService.getAllBikers());
    }

    @GetMapping("/bikers/name")
    public ResponseEntity<List<Biker>> getBikerByName(@RequestParam String name) {
        return ResponseEntity.ok(searchService.getBikerByName(name));
    }

    @GetMapping("/bikers/id/{id}")
    public ResponseEntity<Biker> getBikerById(@PathVariable Long id) {
        return ResponseEntity.of(Optional.ofNullable(searchService.getBikerById(id)));
    }

    @GetMapping("/bikers/phone")
    public ResponseEntity<Biker> getBikerByPhone(@RequestParam String phone) {
        return ResponseEntity.ok(searchService.getBikerByPhone(phone));
    }

    @GetMapping("/products/category")
    public ResponseEntity<List<Product>> searchForProductsByCategory(@RequestParam String category) {
        return ResponseEntity.ok(searchService.searchForProductsByCategory(category));
    }

    @GetMapping("/products/barcode")
    public ResponseEntity<Product> searchForProductByBarcode(@RequestParam String barcode) {
        return ResponseEntity.ok(searchService.searchForProductByBarcode(barcode));
    }

    @GetMapping("/products/id/{id}")
    public ResponseEntity<Optional<Product>> findProductById(@PathVariable Long id) {
        return ResponseEntity.ok(searchService.findProductById(id));
    }

    @GetMapping("/customers/id/{id}")
    public ResponseEntity<Optional<Customer>> findCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(searchService.findCustomerById(id));
    }

    @GetMapping("/stock/quantity")
    public ResponseEntity<Integer> findQuantityOfProductInStock(@RequestParam Long productId, @RequestParam Long storeId) {
        return ResponseEntity.ok(searchService.findQuantityOfProductInStock(productId, storeId));
    }

    @GetMapping("/bikers/status")
    public ResponseEntity<List<Biker>> getBikersByStatus(@RequestParam String status) {
        return ResponseEntity.ok(searchService.getBikersByStatus(status));
    }

    @GetMapping("/bikers/zone")
    public ResponseEntity<?> getBikersByZone(@RequestParam Zone zone) {
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(searchService.getAllCustomers());
    }

    @GetMapping("/all-products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(searchService.getAllProducts());
    }

    @GetMapping("/all-stores")
    public ResponseEntity<List<Store>> getAllStores() {
        return ResponseEntity.ok(searchService.getAllStores());
    }

    @GetMapping("/stores/zone")
    public ResponseEntity<List<Store>> getStoresByZone(@RequestParam String area) {
        return ResponseEntity.ok(searchService.getStoresByZone(area));
    }

    @GetMapping("/stores/id/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable Long id) {
        return ResponseEntity.of(Optional.ofNullable(searchService.getStoreById(id)));
    }
}
