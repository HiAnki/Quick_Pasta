package com.example.QuickPasta.service;

import com.example.QuickPasta.dto.request.CustomerRequest;
import com.example.QuickPasta.dto.response.CustomerResponse;
import com.example.QuickPasta.model.Cart;
import com.example.QuickPasta.model.Customer;
import com.example.QuickPasta.repository.CustomerRepository;
import com.example.QuickPasta.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerService {

    final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponse addCustomer(CustomerRequest customerRequest) {

        // dto -> customer
        Customer customer = CustomerTransformer.CustomerRequestToCustomer(customerRequest);

        Cart cart = Cart.builder()
                .cartTotal(0)
                .customer(customer)
                .foodItemList(new ArrayList<>())
                .build();

        customer.setCart(cart);

        // save customer (parent) and cart will automatically be saved
        Customer savedCustomer = customerRepository.save(customer);

        return CustomerTransformer.CustomerToCustomerResponse(savedCustomer);
    }
}
