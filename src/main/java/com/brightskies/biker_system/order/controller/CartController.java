package com.brightskies.biker_system.order.controller;

import com.brightskies.biker_system.order.model.CartItem;
import com.brightskies.biker_system.order.service.CartService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    CartService cartService;

    @Autowired
    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @PostMapping("/add/{prodId}/{quantity}")
    public ResponseEntity<CartItem> addProduct(@PathVariable Long prodId, @PathVariable Long quantity){
        try {
            return new ResponseEntity<>(cartService.addProduct(prodId,quantity),HttpStatus.OK);
        }catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{prodId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long prodId){
       try {
           return new ResponseEntity<>(cartService.deleteProduct(prodId),HttpStatus.OK);
       }catch (EntityNotFoundException e) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }

    @PutMapping("/update/{prodId}/{incdec}")
    public ResponseEntity<CartItem> updateProductQuantity(@PathVariable Long prodId, @PathVariable Long incdec){
        try {
           return new ResponseEntity<>(cartService.updateProductQuantity(prodId,incdec),HttpStatus.OK);
        }catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/show")
    public ResponseEntity<List<CartItem>> showCart(){
        try {
            return new ResponseEntity<>(cartService.getAll(), HttpStatus.OK);
        }catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
