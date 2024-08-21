package com.brightskies.biker_system.order.service;
import com.brightskies.biker_system.authentication.utility.SecurityUtils;
import com.brightskies.biker_system.customer.model.Customer;
import com.brightskies.biker_system.customer.repository.CustomerRepository;
import com.brightskies.biker_system.order.model.CartItem;
import com.brightskies.biker_system.order.repository.CartRepository;
import com.brightskies.biker_system.store.model.Product;
import com.brightskies.biker_system.store.repository.ProductRepository;
import com.brightskies.biker_system.store.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<CartItem> items = cartRepository.findAll(currentCustomerId);
        if (items != null) {
            return items;
        } else {
            throw new NullPointerException();
        }
    }

    public CartItem addCartItem(Long prodId, Long quantity) {

        Long currentCustomerId = SecurityUtils.getCurrentUserId();
        Customer customer = customerRepository.findByCustomerId(currentCustomerId);
        Product product = productRepository.selectById(prodId);
        if(product == null) {
            throw new NullPointerException();
        }
        CartItem cartItem = new CartItem(customer, product, quantity);
        cartItem = cartRepository.save(cartItem);
        return cartItem;
    }

    public String deleteCartitem(Long cartItemId) {
        try {
            cartRepository.deleteSpecific(cartItemId);
            return "Cart item deleted";
        }catch (Exception e) {
            throw new NullPointerException("Cart item could not be deleted");
        }
    }

    public CartItem updateCartItem (Long cartItemId, int incdec) {
       Optional <CartItem> cartItem = cartRepository.findById(cartItemId);
        if(cartItem.isPresent()) {
            int stockQuantity = stockService.getProductQuantity(cartItem.get());

            if(cartItem.get().getQuantity() == 1 && incdec == -1) {
                cartRepository.deleteSpecific(cartItemId);
                stockService.setProductQuantity(cartItem.get(), stockQuantity+1);
                return new CartItem();
            }else if(stockQuantity > cartItem.get().getQuantity()) {
                cartItem.get().setQuantity(cartItem.get().getQuantity() + incdec);
                cartRepository.save(cartItem.get());
                stockService.setProductQuantity(cartItem.get(), stockQuantity+incdec);
                return cartItem.get();
            }
        }
        return null;
    }
}