package com.brightskies.biker_system.order.service;
import com.brightskies.biker_system.authentication.utility.SecurityUtils;
import com.brightskies.biker_system.customer.model.Customer;
import com.brightskies.biker_system.customer.repository.CustomerRepository;
import com.brightskies.biker_system.exception.model.*;
import com.brightskies.biker_system.order.dto.CartItemDto;
import com.brightskies.biker_system.order.dto.CartResultDto;
import com.brightskies.biker_system.order.model.CartItem;
import com.brightskies.biker_system.order.repository.CartRepository;
import com.brightskies.biker_system.store.model.Product;
import com.brightskies.biker_system.store.model.Store;
import com.brightskies.biker_system.store.repository.ProductRepository;
import com.brightskies.biker_system.store.repository.StoreRepository;
import com.brightskies.biker_system.store.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final StockService stockService;
    private final StoreRepository storeRepository;

    @Autowired
    public CartService(CartRepository cartRepository,
                       ProductRepository productRepository,
                       CustomerRepository customerRepository,
                       StockService stockService, StoreRepository storeRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.stockService = stockService;
        this.storeRepository = storeRepository;
    }

    public CartResultDto getAllCartItems() {

        Long currentCustomerId = SecurityUtils.getCurrentUserId();
        List<CartItem> items = cartRepository.findByCustomerId(currentCustomerId);
        if (items.isEmpty()) {
            throw new EmptyCartException();
        }
        List< CartItemDto> cartItemDtos = new ArrayList<>();
        double totalPrice = 0;
        for(CartItem item : items) {
            CartItemDto cartItemDto = new CartItemDto(item.getId(), item.getProduct().getId(), item.getQuantity(), item.getStore().getId());
            cartItemDtos.add(cartItemDto);
            totalPrice += item.getProduct().getPrice()*item.getQuantity();
        }
        return new CartResultDto(cartItemDtos, totalPrice);
    }

    public CartItem addCartItem(Long prodId, int quantity,Long storeId) {
        Long currentCustomerId = SecurityUtils.getCurrentUserId();
        Customer customer = customerRepository.findById(currentCustomerId).orElseThrow(() -> new CustomerNotFoundException(currentCustomerId));
        Product product = productRepository.findById(prodId).orElseThrow( () -> new ProductNotFoundException(prodId));
        Store store = storeRepository.findById(storeId).orElseThrow( () -> new StoreNotFoundException(storeId));
        int stockQuantity = stockService.getProductQuantity(product.getId(),storeId);
        if(stockQuantity - quantity >= 0) {
            stockService.setProductQuantity(product.getId(), stockQuantity - quantity,storeId);
            CartItem cartItem = new CartItem(customer, product, quantity,store);
            cartItem = cartRepository.save(cartItem);
            return cartItem;
        }else {
            throw new StockQuantityExceededException();
        }
    }

    public void deleteCartitem(Long cartItemId) {
        CartItem cartItem = cartRepository.findById(cartItemId).orElseThrow( () -> new CartItemNotFoundException(cartItemId));
        Store store = storeRepository.findById(cartItem.getStore().getId()).orElseThrow( () -> new StoreNotFoundException(cartItem.getStore().getId()));
        int stockQuantity = stockService.getProductQuantity(cartItem.getProduct().getId(),store.getId());
        stockService.setProductQuantity(cartItem.getProduct().getId(),stockQuantity+cartItem.getQuantity(),store.getId());
        cartRepository.deleteById(cartItemId);
    }

    public CartItem increaseCartItemAmount(Long cartItemId) {
        Optional <CartItem> updatableItem = cartRepository.findById(cartItemId);
        if(updatableItem.isPresent()) {
            int stockQuantity = stockService.getProductQuantity(updatableItem.get().getProduct().getId(),
                    updatableItem.get().getStore().getId());

            if(stockQuantity > 0) {
                updatableItem.get().setQuantity(updatableItem.get().getQuantity() + 1);
                cartRepository.save(updatableItem.get());
                stockService.setProductQuantity(
                        updatableItem.get().getProduct().getId(),
                        stockQuantity-1,
                        updatableItem.get().getStore().getId());

                return updatableItem.get();
            }else {
                throw new StockQuantityExceededException();
            }
        }

        throw new CartItemNotFoundException(cartItemId);
    }

    public CartItem decreaseCartItemAmount(Long cartItemId) {
        Optional <CartItem> updatableItem = cartRepository.findById(cartItemId);
        if(updatableItem.isPresent()) {
            int stockQuantity = stockService.getProductQuantity(updatableItem.get().getProduct().getId(),
                    updatableItem.get().getStore().getId());

            if(updatableItem.get().getQuantity() > 0) {
                updatableItem.get().setQuantity(updatableItem.get().getQuantity() - 1);
                if(updatableItem.get().getQuantity() == 0) {
                    cartRepository.deleteById(cartItemId);
                }else {
                    cartRepository.save(updatableItem.get());
                    stockService.setProductQuantity(
                            updatableItem.get().getProduct().getId(),
                            stockQuantity+1,
                            updatableItem.get().getStore().getId());
                }
            }
        }
        return updatableItem.orElseThrow(() -> new CartItemNotFoundException (cartItemId));
    }

    public void deleteAll() {
        Long currentCustomerId = SecurityUtils.getCurrentUserId();
        Customer customer = customerRepository.findById(currentCustomerId).
                orElseThrow(() -> new CustomerNotFoundException(currentCustomerId));

        List<CartItem> items = cartRepository.findByCustomerId(currentCustomerId);
        if(items.isEmpty()) {
            throw new EmptyCartException();
        }

        for(CartItem cartItem :items) {
            int stockQuantity = stockService.getProductQuantity
                    (cartItem.getProduct().getId(),
                    cartItem.getStore().getId());

            stockService.setProductQuantity(
                    cartItem.getProduct().getId(),
                    stockQuantity+cartItem.getQuantity(),
                    cartItem.getStore().getId());

            cartRepository.deleteById(cartItem.getId());
        }
    }

    public void deleteAllCartItemsAfterCreatingOrder(){
        Long currentCustomerId = SecurityUtils.getCurrentUserId();
        Customer customer = customerRepository.findById(currentCustomerId).
                orElseThrow(() -> new CustomerNotFoundException(currentCustomerId));

        for(CartItem cartItem : cartRepository.findByCustomerId(currentCustomerId)) {
            cartRepository.deleteById(cartItem.getId());
        }
    }
}