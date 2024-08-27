package com.brightskies.biker_system.customer.service;

import com.brightskies.biker_system.customer.model.Customer;
import com.brightskies.biker_system.customer.repository.CustomerRepository;
import com.brightskies.biker_system.general.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CustomerService {

    CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public void updateLastLogin(User user) {
        Customer customer = customerRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("User not found"));
        customer.setLastLogin(LocalDate.now());
        customerRepository.save(customer);
    }
}
