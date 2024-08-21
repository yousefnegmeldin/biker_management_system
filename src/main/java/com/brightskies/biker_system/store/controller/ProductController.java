package com.brightskies.biker_system.store.controller;

import com.brightskies.biker_system.store.DTO.ProductDTO;
import com.brightskies.biker_system.store.mapper.ProductConverter;
import com.brightskies.biker_system.store.model.Product;
import com.brightskies.biker_system.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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

    @PostMapping("/add product")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO)
    {
        Product newproductDTO = productConverter.toEntity(productDTO);
        Product product = productService.addProduct(newproductDTO);
        ProductDTO newDto = productConverter.toDTO(product);
        return ResponseEntity.ok(newDto);
    }

    @DeleteMapping("/delete product /{name}")
    public ResponseEntity<?> deleteProductByName(@PathVariable String name)
    {
        productService.deleteProductByName(name);
        return ResponseEntity.ok("Product is deleted");
    }

}
