package com.brightskies.biker_system.store.controller;

import com.brightskies.biker_system.store.DTO.ProductDTO;
import com.brightskies.biker_system.store.mapper.ProductConverter;
import com.brightskies.biker_system.store.model.Product;
import com.brightskies.biker_system.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
        this.productConverter=productConverter;
    }
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO)
    {
        Product newproductDTO = productConverter.toEntity(productDTO);
        Product product = productService.addProduct(newproductDTO);
        ProductDTO newDto = productConverter.toDTO(product);
        return ResponseEntity.ok(newDto);
    }
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProductByName(@PathVariable Long id)
    {
        productService.deleteProductById(id);
        return ResponseEntity.ok("Product is deleted");
    }

}
