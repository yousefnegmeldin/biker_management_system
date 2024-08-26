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

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/showall")
    public ResponseEntity<?> showAll() {
        try {
            List<CartItem> items = cartService.getAllCartItems();
            return new ResponseEntity<>(items, HttpStatus.OK);
        }
        catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/additem")
    public ResponseEntity<?> addCartItem(@RequestParam Long prodId, @RequestParam Long quantity) {
        try{
            return new ResponseEntity<>(cartService.addCartItem(prodId, quantity), HttpStatus.OK);
        }
        catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/deleteitem")
    public ResponseEntity<String> deleteCartItem(@RequestParam Long prodId) {
        try {
            return new ResponseEntity<>(cartService.deleteCartitem(prodId), HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping ("/update/inc")
    public ResponseEntity<?> increaseCartItemAmount(@RequestParam Long cartItemId, @RequestParam int inc) {
        try{
            return new ResponseEntity<>(cartService.increaseCartItemAmount(cartItemId,inc),HttpStatus.OK);
        }
        catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_MODIFIED);
        }
    }

    @PutMapping ("/update/dec")
    public ResponseEntity<?> decreaseCartItemAmount(@RequestParam Long cartItemId, @RequestParam int dec) {
        try{
            return new ResponseEntity<>(cartService.decreaseCartItemAmount(cartItemId,dec),HttpStatus.OK);
        }
        catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_MODIFIED);
        }
        catch (NullPointerException e) {
            return new ResponseEntity<>("Item deleted from cart.",HttpStatus.NOT_FOUND);
        }
    }
}
