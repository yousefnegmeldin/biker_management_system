package com.brightskies.biker_system.customer.service;

import com.brightskies.biker_system.authentication.utility.SecurityUtils;
import com.brightskies.biker_system.customer.dto.AddressDTO;
import com.brightskies.biker_system.customer.dto.UpdateAddressDTO;
import com.brightskies.biker_system.customer.model.Address;
import com.brightskies.biker_system.customer.repository.AddressRepository;
import com.brightskies.biker_system.customer.repository.CustomerRepository;
import com.brightskies.biker_system.exception.model.AddressLabelRepeatedException;
import com.brightskies.biker_system.exception.model.AddressNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.List;

@Service
public class AddressService {
    private AddressRepository addressRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository, CustomerRepository customerRepository) {
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
    }

    public Address addAddress(AddressDTO addressDTO) throws AddressLabelRepeatedException{
        Long customerID = SecurityUtils.getCurrentUserId();
        List<String> labels = addressRepository.findAllLabelsByCustomer(customerID);
        if(labels.contains(addressDTO.label())) {
            throw new AddressLabelRepeatedException();
        }
        Address address = new Address(
                customerRepository.findById(customerID).get(),
                addressDTO.label(),
                addressDTO.zone(),
                addressDTO.city(),
                addressDTO.street(),
                addressDTO.apartment()
        );
        addressRepository.save(address);
        return address;
    }

    public void removeAddress(Long id) throws AddressNotFoundException {
        if(addressRepository.findById(id).isPresent()) {
            addressRepository.deleteById(id);
        }
        else {
            throw new AddressNotFoundException(id);
        }
    }

    public void updateAddressDetails(Long id, UpdateAddressDTO newAddressDTO) throws AddressNotFoundException {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id));
        address.setCity(newAddressDTO.city());
        address.setStreet(newAddressDTO.street());
        address.setApartment(newAddressDTO.apartment());
        addressRepository.save(address);
    }
}
