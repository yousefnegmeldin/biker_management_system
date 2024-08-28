package com.brightskies.biker_system.admin.controller;

import com.brightskies.biker_system.order.controller.CartController;
import com.brightskies.biker_system.order.dto.CartItemDtoMapper;
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
import jakarta.persistence.EntityNotFoundException;
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

    //we can just put the role of admin on existing api
    @PostMapping("/addstore")
    public ResponseEntity<?> addStore(@RequestBody StoreDTO storeDTO)
    {
        Store newstoreDTO = storeConverter.toEntity(storeDTO);
        Store store = storeService.addStore(newstoreDTO);
        StoreDTO newDto = storeConverter.toDTO(store);
        return ResponseEntity.ok("Store added");
    }


    @DeleteMapping("/store/{id}")
    public ResponseEntity<?> deleteStore(@PathVariable Long id)
    {
        storeService.deleteStore(id);
        return ResponseEntity.ok("Store is deleted");
    }

    //we can just put the role of admin on existing api
    @PostMapping("/addproduct")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO)
    {
        Product newproductDTO = productConverter.toEntity(productDTO);
        Product product = productService.addProduct(newproductDTO);
        ProductDTO newDto = productConverter.toDTO(product);
        return ResponseEntity.ok("Product added");
    }

    @DeleteMapping("/deleteproduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id)
    {
        productService.deleteProductById(id);
        return ResponseEntity.ok("Product is deleted");
    }

    //we can just put the role of admin on existing api
    @PostMapping("/addstock")
    public ResponseEntity<?> addStock(@RequestBody StockDTO stockDTO)
    {
        Stock newstockDTO = stockConverter.toEntity(stockDTO);
        Stock stock = stockService.addStock(newstockDTO);
        StockDTO newDto = stockConverter.toDTO(stock);
        return ResponseEntity.ok("Stock added");
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


}

