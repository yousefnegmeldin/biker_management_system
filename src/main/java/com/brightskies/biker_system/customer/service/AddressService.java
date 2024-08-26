package com.brightskies.biker_system.customer.service;

import com.brightskies.biker_system.authentication.utility.SecurityUtils;
import com.brightskies.biker_system.customer.dto.AddressDTO;
import com.brightskies.biker_system.customer.dto.UpdateAddressDTO;
import com.brightskies.biker_system.customer.model.Address;
import com.brightskies.biker_system.customer.repository.AddressRepository;
import com.brightskies.biker_system.customer.repository.CustomerRepository;
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

    public AddressDTO addAddress(AddressDTO addressDTO){
        Long customerID = SecurityUtils.getCurrentUserId();
        Address address = new Address(
                customerRepository.findById(customerID).get(),
                addressDTO.label(),
                addressDTO.city(),
                addressDTO.street(),
                addressDTO.apartment()
        );
        addressRepository.save(address);
        return new AddressDTO(address.getLabel(), address.getCity(), address.getStreet(), address.getApartment());
    }

    /*public void removeAddress(String label) throws InstanceNotFoundException {
        Long customerID = SecurityUtils.getCurrentUserId();
        List<String> labels = addressRepository.findAllLabelsByCustomer(customerID);
        if(labels.contains(label)) {
            addressRepository.deleteByCustomerAndLabel(customerID, label);
        }
        else {
            throw new InstanceNotFoundException("Customer does not have an address with that label");
        }
    }*/

    public void removeAddress(Long id) throws InstanceNotFoundException {
        if(addressRepository.findById(id).isPresent()) {
            addressRepository.deleteById(id);
        }
        else {
            throw new InstanceNotFoundException("Address instance does not exist");
        }
    }

    /*public void updateAddressDetails(String label, UpdateAddressDTO newAddressDTO) throws InstanceNotFoundException {
        Long customerID = SecurityUtils.getCurrentUserId();
        List<String> labels = addressRepository.findAllLabelsByCustomer(customerID);
        if(labels.contains(label)) {
            Address address = addressRepository.findAddressByCustomerAndLabel(customerID, label).get();
            address.setCity(newAddressDTO.city());
            address.setStreet(newAddressDTO.street());
            address.setApartment(newAddressDTO.apartment());
            addressRepository.save(address);
        }
        else {
            throw new InstanceNotFoundException("Customer does not have an address with that label");
        }
    }*/

    public void updateAddressDetails(Long id, UpdateAddressDTO newAddressDTO) throws InstanceNotFoundException {
        if(addressRepository.findById(id).isPresent()) {
            Address address = addressRepository.findById(id).get();
            address.setCity(newAddressDTO.city());
            address.setStreet(newAddressDTO.street());
            address.setApartment(newAddressDTO.apartment());
            addressRepository.save(address);
        }
        else {
            throw new InstanceNotFoundException("Address instance does not exist");
        }
    }
}
