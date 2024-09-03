package com.brightskies.biker_system.admin.controller;

import com.brightskies.biker_system.order.enums.AssignmentStatus;
import com.brightskies.biker_system.order.service.CartService;
import com.brightskies.biker_system.order.service.DeliveryAssignmentService;
import com.brightskies.biker_system.order.service.OrderService;
import com.brightskies.biker_system.store.DTO.ProductDTO;
import com.brightskies.biker_system.store.DTO.StockDTO;
import com.brightskies.biker_system.store.DTO.StoreDTO;
import com.brightskies.biker_system.store.mapper.ProductConverter;
import com.brightskies.biker_system.store.mapper.StockConverter;
import com.brightskies.biker_system.store.mapper.StoreConverter;
import com.brightskies.biker_system.store.model.Product;
import com.brightskies.biker_system.store.model.Stock;
import com.brightskies.biker_system.store.model.Store;
import com.brightskies.biker_system.store.service.ProductService;
import com.brightskies.biker_system.store.service.StockService;
import com.brightskies.biker_system.store.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class AdminController
{
    private StoreService storeService;
    private StoreConverter storeConverter;
    private ProductService productService;
    private ProductConverter productConverter;
    private StockService stockService;
    private StockConverter stockConverter;
    private CartService cartService;
    private DeliveryAssignmentService deliveryAssignmentService;
    private OrderService orderService;

    @Autowired
    public AdminController(StoreService storeService, StoreConverter storeConverter, ProductService productService, ProductConverter productConverter, StockService stockService, StockConverter stockConverter, CartService cartService, DeliveryAssignmentService deliveryAssignmentService, OrderService orderService) {
        this.storeService = storeService;
        this.storeConverter= storeConverter;
        this.productService= productService;
        this.productConverter= productConverter;
        this.stockService= stockService;
        this.stockConverter = stockConverter;
        this.cartService = cartService;
        this.deliveryAssignmentService = deliveryAssignmentService;
        this.orderService= orderService;
    }

    @DeleteMapping("/deletecartitem/{cartItemId}")
    public ResponseEntity<?> deleteCartItem(@PathVariable Long cartItemId)
    {
       cartService.deleteCartitem(cartItemId);
       return new ResponseEntity<>("Cart Item is Deleted!", HttpStatus.OK);
    }

    //put role of admin on existing api
    @PatchMapping("/status/{id}/{status}")
    public ResponseEntity<?> updateDeliveryAssignmentStatus(@PathVariable Long id, @PathVariable AssignmentStatus status) throws Exception {
        deliveryAssignmentService.changeStatus(id,status);
        return ResponseEntity.ok("Delivery status is updated");
    }

    @DeleteMapping("/deleteorder/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId)
    {
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok("Order is Deleted");
    }

    @DeleteMapping("/deletedeliveryassignment")
    public ResponseEntity<?> deleteDeliveryAssignment(@PathVariable Long id)
    {
        deliveryAssignmentService.deleteDeliveryAssignment(id);
        return ResponseEntity.ok("Delivery Assignment is Deleted");
    }

}

