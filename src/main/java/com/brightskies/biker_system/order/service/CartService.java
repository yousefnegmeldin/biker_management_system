package com.brightskies.biker_system.order.service;

import com.brightskies.biker_system.generalmodels.User;
import com.brightskies.biker_system.order.model.Cart;
import com.brightskies.biker_system.order.model.CartItem;
import com.brightskies.biker_system.order.repository.CartItemRepository;
import com.brightskies.biker_system.order.repository.CartRepository;
import com.brightskies.biker_system.store.model.Product;
import com.brightskies.biker_system.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public Cart getCartByUser(User user) {
        return cartRepository.findByUser(user).orElseGet(() -> createCartForUser(user));
    }

    public Cart createCartForUser(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    public Cart addItemToCart(User user, Long productId, int quantity) {
        Cart cart = getCartByUser(user);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cart.getItems().add(cartItem);

        cartItemRepository.save(cartItem);
        return cartRepository.save(cart);
    }

    // Additional methods to remove items, update quantities, etc.
}
