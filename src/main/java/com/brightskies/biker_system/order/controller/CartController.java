package com.brightskies.biker_system.order.controller;
import com.brightskies.biker_system.order.dto.CartItemDtoMapper;
import com.brightskies.biker_system.order.model.CartItem;
import com.brightskies.biker_system.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/showall")
    public ResponseEntity<?> showAll() {
        return new ResponseEntity<>(cartService.getAllCartItems(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAll() {
        cartService.deleteAll();
        return new ResponseEntity<>("All items have been removed from cart",HttpStatus.OK);
    }

    @PostMapping("/additem")
    public ResponseEntity<?> addCartItem(@RequestParam Long prodId, @RequestParam int quantity,@RequestParam Long storeId) {
        return new ResponseEntity<>(CartItemDtoMapper.map(cartService.addCartItem(prodId, quantity, storeId)), HttpStatus.OK);
    }

    @DeleteMapping("/deleteitem")
    public ResponseEntity<String> deleteCartItem(@RequestParam Long cartItemId) {
        cartService.deleteCartitem(cartItemId);
        return new ResponseEntity<>("Cart item "+ cartItemId +" has been Deleted!", HttpStatus.OK);
    }

    @PatchMapping("/inc")
    public ResponseEntity<?> increaseCartItemAmount(@RequestParam Long cartItemId) {
        return new ResponseEntity<>(CartItemDtoMapper.map(cartService.increaseCartItemAmount(cartItemId)),HttpStatus.OK);
    }

    @PatchMapping("/dec")
    public ResponseEntity<?> decreaseCartItemAmount(@RequestParam Long cartItemId) {
        CartItem item = cartService.decreaseCartItemAmount(cartItemId);
        if (item.getQuantity() > 0) {
            return new ResponseEntity<>(CartItemDtoMapper.map(item),HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Item deleted from cart.",HttpStatus.OK);
        }
    }

}
