package com.brightskies.biker_system;

import com.brightskies.biker_system.authentication.service.AuthenticationService;
import com.brightskies.biker_system.customer.service.AddressService;
import com.brightskies.biker_system.store.service.ProductService;
import com.brightskies.biker_system.store.service.StockService;
import com.brightskies.biker_system.store.service.StoreService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final AuthenticationService authenticationService;
    private final AddressService addressService;
    private final StoreService storeService;
    private final StockService stockService;
    private final ProductService productService;


    @Override
    public void run(String... args) throws Exception {
        // Insert sample data here
        authenticationService.signupAdmin();
    }
}
