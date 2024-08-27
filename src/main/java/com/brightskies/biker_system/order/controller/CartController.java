package com.brightskies.biker_system.order.controller;

import com.brightskies.biker_system.order.dto.CartItemDtoMapper;
import com.brightskies.biker_system.order.model.CartItem;
import com.brightskies.biker_system.order.service.CartService;
import com.brightskies.biker_system.store.repository.StoreRepository;
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
            return new ResponseEntity<>(items.stream().map(CartItemDtoMapper::map), HttpStatus.OK);
        }
        catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/additem")
    public ResponseEntity<?> addCartItem(@RequestParam Long prodId, @RequestParam int quantity,@RequestParam Long storeId) {
        try{
            return new ResponseEntity<>(CartItemDtoMapper.map(cartService.addCartItem(prodId, quantity, storeId)), HttpStatus.OK);
        }
        catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/deleteitem")
    public ResponseEntity<String> deleteCartItem(@RequestParam Long prodId) {
        try {
            cartService.deleteCartitem(prodId);
            return new ResponseEntity<>("Cart is Deleted!", HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @PatchMapping("/inc")
    public ResponseEntity<?> increaseCartItemAmount(@RequestParam Long cartItemId) {
        try{
            return new ResponseEntity<>(CartItemDtoMapper.map(cartService.increaseCartItemAmount(cartItemId)),HttpStatus.OK);
        }
        catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
        }
    }

    @PatchMapping("/dec")
    public ResponseEntity<?> decreaseCartItemAmount(@RequestParam Long cartItemId) {
        try{
            return new ResponseEntity<>(CartItemDtoMapper.map(cartService.decreaseCartItemAmount(cartItemId)),HttpStatus.OK);
        }
        catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
        }
        catch (NullPointerException e) {
            return new ResponseEntity<>("Item deleted from cart.",HttpStatus.OK);
        }
    }


}
