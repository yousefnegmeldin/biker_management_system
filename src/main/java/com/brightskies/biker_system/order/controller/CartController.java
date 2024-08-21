package com.brightskies.biker_system.order.controller;

import com.brightskies.biker_system.order.model.CartItem;
import com.brightskies.biker_system.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController( CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/showall")
    public ResponseEntity<List<CartItem>> showAll() {
        try {
            List<CartItem> items = cartService.getAllCartItems();
            return new ResponseEntity<>(items, HttpStatus.OK);
        }
        catch(NullPointerException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/additem")
    public ResponseEntity<CartItem> addCartItem(@PathVariable Long prodId, @PathVariable Long quantity) {
        try{
            return new ResponseEntity<>(cartService.addCartItem(prodId, quantity), HttpStatus.OK);
        }
        catch(NullPointerException e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/deleteitem")
    public ResponseEntity<String> deleteCartItem(@PathVariable Long prodId) {
        try {
            return new ResponseEntity<>(cartService.deleteCartitem(prodId), HttpStatus.OK);
        }catch (NullPointerException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping ("/update")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable Long prodId, @PathVariable int incdec) {
        try{
            return new ResponseEntity<>(cartService.updateCartItem(prodId,incdec),HttpStatus.OK);
        }
        catch(NullPointerException e){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
}
