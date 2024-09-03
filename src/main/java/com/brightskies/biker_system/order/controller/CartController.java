package com.brightskies.biker_system.order.controller;
import com.brightskies.biker_system.order.dto.CartItemDto;
import com.brightskies.biker_system.order.dto.CartItemDtoMapper;
import com.brightskies.biker_system.order.dto.CartResultDto;
import com.brightskies.biker_system.order.dto.OrderDto;
import com.brightskies.biker_system.order.model.CartItem;
import com.brightskies.biker_system.order.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            summary = "Get all cart items",
            description = "Retrieve all cart items for current user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(schema = @Schema(type = "array", implementation = CartResultDto.class))),
                    @ApiResponse(
                            responseCode = "406",
                            description = "Cart is empty",
                            content = @Content(schema = @Schema(implementation = String.class))),
            }
    )
    public ResponseEntity<?> showAll() {
        return new ResponseEntity<>(cartService.getAllCartItems(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteall")
    @Operation(
            summary = "Delete all cart items",
            description = "Delete all cart items for current user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(
                            responseCode = "406",
                            description = "Cart is empty",
                            content = @Content(schema = @Schema(implementation = String.class))),
            }
    )
    public ResponseEntity<String> deleteAll() {
        cartService.deleteAll();
        return new ResponseEntity<>("All items have been removed from cart",HttpStatus.OK);
    }


    @PostMapping("/additem")
    @Operation(
            summary = "Add item to cart",
            description = "Adds a cart item to cart for current user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = CartItemDto.class))),
                    @ApiResponse(
                            responseCode = "406",
                            description = "Stock limit exceeded",
                            content = @Content(schema = @Schema(implementation = String.class))),
            }
    )
    public ResponseEntity<?> addCartItem(@RequestParam Long prodId, @RequestParam int quantity,@RequestParam Long storeId) {
        return new ResponseEntity<>(CartItemDtoMapper.map(cartService.addCartItem(prodId, quantity, storeId)), HttpStatus.OK);
    }

    @DeleteMapping("/deleteitem")
    @Operation(
            summary = "Deletes item from cart",
            description = "Delete an item present in cart for current user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Cart item not in cart or store doesn't exist",
                            content = @Content(schema = @Schema(implementation = String.class))),
            }
    )
    public ResponseEntity<String> deleteCartItem(@RequestParam Long cartItemId) {
        cartService.deleteCartitem(cartItemId);
        return new ResponseEntity<>("Cart item "+ cartItemId +" has been Deleted!", HttpStatus.OK);
    }

    @PatchMapping("/inc")
    @Operation(
            summary = "Increases item quantity by one",
            description = "Increases an item quantity by one for cart item present in cart for current user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(schema = @Schema(type = "array", implementation = CartItemDto.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Cart item not in cart or store doesn't exist",
                            content = @Content(schema = @Schema(implementation = String.class))),
            }
    )
    public ResponseEntity<?> increaseCartItemAmount(@RequestParam Long cartItemId) {
        return new ResponseEntity<>(CartItemDtoMapper.map(cartService.increaseCartItemAmount(cartItemId)),HttpStatus.OK);
    }

    @PatchMapping("/dec")
    @Operation(
            summary = "Decreases item quantity by one",
            description = "Decreases an item quantity by one for cart item present in cart for current user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(schema = @Schema(type = "array", implementation = CartItemDto.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Cart item not in cart or store doesn't exist",
                            content = @Content(schema = @Schema(implementation = String.class))),
            }
    )
    public ResponseEntity<?> decreaseCartItemAmount(@RequestParam Long cartItemId) {
        CartItem item = cartService.decreaseCartItemAmount(cartItemId);
        if (item.getQuantity() > 0) {
            return new ResponseEntity<>(CartItemDtoMapper.map(item),HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Item deleted from cart.",HttpStatus.OK);
        }
    }

}
