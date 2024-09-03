package com.brightskies.biker_system.store.controller;

import com.brightskies.biker_system.store.DTO.ProductDTO;
import com.brightskies.biker_system.store.mapper.ProductConverter;
import com.brightskies.biker_system.store.model.Product;
import com.brightskies.biker_system.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/product")
public class ProductController
{
    private ProductService productService;
    private ProductConverter productConverter;

    @Autowired
    public ProductController(ProductService productService, ProductConverter productConverter)
    {
        this.productService = productService;
        this.productConverter = productConverter;
    }

    @Operation(summary = "Add a new product",
            description = "Add a new product to the store. Accessible by MANAGER and ADMIN roles.")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO)
    {
        Product newProduct = productConverter.toEntity(productDTO);
        Product product = productService.addProduct(newProduct);
        ProductDTO newDto = productConverter.toDTO(product);
        return ResponseEntity.ok(newDto);
    }

    @Operation(summary = "Delete a product by ID",
            description = "Delete a product from the store by its ID. Accessible by MANAGER and ADMIN roles.")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductByName(@PathVariable Long id)
    {
        productService.deleteProductById(id);
        return ResponseEntity.ok("Product is deleted");
    }
}