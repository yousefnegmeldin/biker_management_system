package com.brightskies.biker_system.search.controller;

import com.brightskies.biker_system.authentication.dto.UserDTO;
import com.brightskies.biker_system.authentication.mapper.UserMapper;
import com.brightskies.biker_system.biker.dto.BikerDto;
import com.brightskies.biker_system.biker.enums.BikerStatus;
import com.brightskies.biker_system.biker.mapper.BikerMapper;
import com.brightskies.biker_system.general.enums.ViewUserDTO;
import com.brightskies.biker_system.biker.model.Biker;
import com.brightskies.biker_system.customer.model.Customer;
import com.brightskies.biker_system.general.models.User;
import com.brightskies.biker_system.search.service.SearchService;
import com.brightskies.biker_system.store.DTO.ProductDTO;
import com.brightskies.biker_system.store.DTO.StoreDTO;
import com.brightskies.biker_system.store.model.Product;
import com.brightskies.biker_system.store.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

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

    @Operation(summary = "Search for users by name",
            description = "Search for users by their name. Accessible by MANAGER and ADMIN roles.")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<ViewUserDTO>> searchForUsersByName(@RequestParam String name) {
        List<User> users = searchService.searchForUsersByName(name);
        List<ViewUserDTO> viewUserDTOList = users
                .stream()
                .map(
                        user -> new ViewUserDTO(user.getId(),user.getName(),user.getEmail(),user.getPhone(),user.getRole()))
                .toList();
        return ResponseEntity.ok(viewUserDTOList);
    }

    @Operation(summary = "Search for products by name",
            description = "Search for products by their name. Accessible by CUSTOMER, MANAGER, and ADMIN roles.")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER','ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> searchForProducts(@RequestParam String name) {
        List<Product> products = searchService.searchForProducts(name);
        List<ProductDTO> productDTOList = products
                .stream()
                .map(
                        product -> new ProductDTO(product.getId(),
                                product.getName(),
                                product.getDescription(),
                                product.getBarcode(),
                                product.getCategory(),
                                product.getPrice()))
                .toList();
        return ResponseEntity.ok(productDTOList);
    }

    @Operation(summary = "Search for a store by name",
            description = "Search for a store by its name. Accessible by CUSTOMER, MANAGER, and ADMIN roles.")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER','ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("/stores")
    public ResponseEntity<StoreDTO> searchForStore(@RequestParam String name) {
        Store store = searchService.searchForStore(name);
        return ResponseEntity.ok(new StoreDTO(store.getId(),store.getName(),store.getArea()));
    }

    @Operation(summary = "Get all bikers",
            description = "Get a list of all bikers. Accessible by MANAGER and ADMIN roles.")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("/bikers")
    public ResponseEntity<List<BikerDto>> getAllBikers() {
        List<Biker> bikers = searchService.getAllBikers();
        List<BikerDto> bikerDtos = bikers.stream().map(BikerMapper::toDTO).toList();
        return ResponseEntity.ok(bikerDtos);
    }

    @Operation(summary = "Get bikers by name",
            description = "Get a list of bikers by their name. Accessible by MANAGER and ADMIN roles.")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("/bikers/name")
    public ResponseEntity<List<BikerDto>> getBikerByName(@RequestParam String name) {
        List<Biker> bikers = searchService.getBikerByName(name);
        List<BikerDto> bikerDtos = bikers.stream().map(BikerMapper::toDTO).toList();
        return ResponseEntity.ok(bikerDtos);
    }

    @Operation(summary = "Get biker by phone",
            description = "Get a biker by their phone number. Accessible by MANAGER and ADMIN roles.")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("/bikers/phone")
    public ResponseEntity<BikerDto> getBikerByPhone(@RequestParam String phone) {
        return ResponseEntity.ok(BikerMapper.toDTO(searchService.getBikerByPhone(phone)));
    }

    @Operation(summary = "Get bikers by status",
            description = "Get a list of bikers by their status. Accessible by MANAGER and ADMIN roles.")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("/bikers/status")
    public ResponseEntity<List<BikerDto>> getBikersByStatus(@RequestParam BikerStatus status) {
        List<Biker> bikers = searchService.getBikersByStatus(status);
        List<BikerDto> bikerDtos = bikers.stream().map(BikerMapper::toDTO).toList();
        return ResponseEntity.ok(bikerDtos);
    }

    @Operation(summary = "Get biker by ID",
            description = "Get a biker by their ID. Accessible by MANAGER and ADMIN roles.")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("/bikers/{id}")
    public ResponseEntity<BikerDto> getBikerById(@PathVariable Long id) {
        return ResponseEntity.ok(BikerMapper.toDTO(searchService.getBikerById(id)));
    }

    @Operation(summary = "Search for products by category",
            description = "Search for products by their category. Accessible by CUSTOMER, MANAGER, and ADMIN roles.")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER','ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("/products/category")
    public ResponseEntity<List<ProductDTO>> searchForProductsByCategory(@RequestParam String category) {
        List<Product> products = searchService.searchForProductsByCategory(category);
        List<ProductDTO> productDTOList = products
                .stream()
                .map(product ->
                        new ProductDTO(product.getId(),product.getName(),product.getDescription(),product.getBarcode(),product.getCategory(),product.getPrice()))
                .toList();
        return ResponseEntity.ok(productDTOList);
    }

    @Operation(summary = "Search for product by barcode",
            description = "Search for a product by its barcode. Accessible by CUSTOMER, MANAGER, and ADMIN roles.")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER','ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("/products/barcode")
    public ResponseEntity<ProductDTO> searchForProductByBarcode(@RequestParam String barcode) {
        Product product = searchService.searchForProductByBarcode(barcode);
        ProductDTO productDTO = new ProductDTO(product.getId(),product.getName(),product.getDescription(),product.getBarcode(),product.getCategory(),product.getPrice());
        return ResponseEntity.ok(productDTO);
    }

    @Operation(summary = "Find product by ID",
            description = "Find a product by its ID. Accessible by CUSTOMER, MANAGER, and ADMIN roles.")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER','ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("/products/{id}")
    public ResponseEntity<?> findProductById(@PathVariable Long id) {
        Optional<Product> product = searchService.findProductById(id);
        if(product.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Product presentProduct = product.get();
        ProductDTO productDTO = new ProductDTO(presentProduct.getId(),presentProduct.getName(),presentProduct.getDescription(),presentProduct.getBarcode(),presentProduct.getCategory(),presentProduct.getPrice());
        return ResponseEntity.ok(productDTO);
    }

    @Operation(summary = "Find customer by ID",
            description = "Find a customer by their ID. Accessible by MANAGER and ADMIN roles.")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("/customers/id/{id}")
    public ResponseEntity<?> findCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = searchService.findCustomerById(id);
        if(customer.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Customer presentCustomer = customer.get();
        return ResponseEntity.ok(UserMapper.toUserDTO(presentCustomer));
    }

    @Operation(summary = "Find quantity of product in stock",
            description = "Find the quantity of a product in stock for a specific store. Accessible by CUSTOMER, MANAGER, and ADMIN roles.")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER','ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("/stock/quantity")
    public ResponseEntity<Integer> findQuantityOfProductInStock(@RequestParam Long productId, @RequestParam Long storeId) {
        return ResponseEntity.ok(searchService.findQuantityOfProductInStock(productId, storeId));
    }

    @Operation(summary = "Get all customers",
            description = "Get a list of all customers. Accessible by MANAGER and ADMIN roles.")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("/customers")
    public ResponseEntity<List<UserDTO>> getAllCustomers() {
        List<Customer> users = searchService.getAllCustomers();
        return ResponseEntity.ok(users.stream().map(UserMapper::toUserDTO).toList());
    }

    @Operation(summary = "Get all products",
            description = "Get a list of all products. Accessible by CUSTOMER, MANAGER, and ADMIN roles.")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER','ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("/all-products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<Product> products = searchService.getAllProducts();
        List<ProductDTO> productDTOList = products
                .stream()
                .map(product ->
                        new ProductDTO(product.getId(),product.getName(),product.getDescription(),product.getBarcode(),product.getCategory(),product.getPrice()))
                .toList();
        return ResponseEntity.ok(productDTOList);
    }

    @Operation(summary = "Get all stores",
            description = "Get a list of all stores. Accessible by CUSTOMER, MANAGER, and ADMIN roles.")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER','ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("/all-stores")
    public ResponseEntity<List<StoreDTO>> getAllStores() {
        List<Store> stores = searchService.getAllStores();
        List<StoreDTO> storeDTOS = stores.stream().map(store -> new StoreDTO(store.getId(),store.getName(),store.getArea())).toList();
        return ResponseEntity.ok(storeDTOS);
    }

    @Operation(summary = "Get stores by zone",
            description = "Get a list of stores by their zone. Accessible by CUSTOMER, MANAGER, and ADMIN roles.")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER','ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("/stores/zone")
    public ResponseEntity<List<StoreDTO>> getStoresByZone(@RequestParam String area) {
        List<Store> stores = searchService.getStoresByZone(area);
        List<StoreDTO> storeDTOS = stores.stream().map(store -> new StoreDTO(store.getId(),store.getName(),store.getArea())).toList();
        return ResponseEntity.ok(storeDTOS);
    }

    @Operation(summary = "Get store by ID",
            description = "Get a store by its ID. Accessible by CUSTOMER, MANAGER, and ADMIN roles.")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER','ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("/stores/id/{id}")
    public ResponseEntity<?> getStoreById(@PathVariable Long id) {
        Optional<Store> store = searchService.getStoreById(id);
        if(store.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new StoreDTO(store.get().getId(),store.get().getName(),store.get().getArea()));
    }
}