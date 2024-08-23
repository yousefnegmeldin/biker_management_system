package com.brightskies.biker_system.order.service;
import com.brightskies.biker_system.authentication.utility.SecurityUtils;
import com.brightskies.biker_system.customer.model.Customer;
import com.brightskies.biker_system.customer.repository.CustomerRepository;
import com.brightskies.biker_system.order.model.CartItem;
import com.brightskies.biker_system.order.repository.CartRepository;
import com.brightskies.biker_system.store.model.Product;
import com.brightskies.biker_system.store.repository.ProductRepository;
import com.brightskies.biker_system.store.service.StockService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final StockService stockService;

    @Autowired
    public CartService(CartRepository cartRepository,
                       ProductRepository productRepository,
                       CustomerRepository customerRepository,
                       StockService stockService) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.stockService = stockService;
    }

    public List<CartItem> getAllCartItems() {
        Long currentCustomerId = SecurityUtils.getCurrentUserId();
        List<CartItem> items = cartRepository.findByCustomerId(currentCustomerId);
        if (items.isEmpty()) {
            throw new EntityNotFoundException("Cart is empty.");
        }
        return items;
    }

    public CartItem addCartItem(Long prodId, Long quantity) {
        Long currentCustomerId = SecurityUtils.getCurrentUserId();
        Customer customer = customerRepository.findById(currentCustomerId).orElse(null);
        Product product = productRepository.findById(prodId).orElseThrow( () -> new EntityNotFoundException("Product not found."));
        CartItem cartItem = new CartItem(customer, product, quantity);
        cartItem = cartRepository.save(cartItem);
        return cartItem;
    }

    public String deleteCartitem(Long cartItemId) {
        try {
            cartRepository.deleteById(cartItemId);
            return "Cart item deleted";
        }catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Cart item could not be deleted");
        }
    }


    public CartItem increaseCartItemAmount(Long cartItemId, int inc) {
        Optional <CartItem> updatableItem = cartRepository.findById(cartItemId);
        if(updatableItem.isPresent()) {
            int stockQuantity = stockService.getProductQuantity(updatableItem.get());

            if(stockQuantity > 0) {
                updatableItem.get().setQuantity(updatableItem.get().getQuantity() + inc);
                cartRepository.save(updatableItem.get());
                stockService.setProductQuantity(updatableItem.get(), stockQuantity-inc);
                return updatableItem.get();
            }
        }

        throw new EntityNotFoundException("Item not in cart or stock limit reached");
    }

    public CartItem decreaseCartItemAmount(Long cartItemId, int dec) {
        Optional <CartItem> updatableItem = cartRepository.findById(cartItemId);
        if(updatableItem.isPresent()) {
            int stockQuantity = stockService.getProductQuantity(updatableItem.get());

            if(updatableItem.get().getQuantity() > 0) {
                updatableItem.get().setQuantity(updatableItem.get().getQuantity() - dec);
                if(updatableItem.get().getQuantity() == 0) {
                    cartRepository.deleteById(cartItemId);
                    throw new NullPointerException();
                }else {
                    cartRepository.save(updatableItem.get());
                    stockService.setProductQuantity(updatableItem.get(), stockQuantity+dec);
                }
            }
        }
        return updatableItem.orElseThrow(() -> new EntityNotFoundException ("Item not in cart"));
    }
}