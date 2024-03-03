package com.example.QuickPasta.service;

import com.example.QuickPasta.dto.request.CustomerRequest;
import com.example.QuickPasta.dto.response.CustomerResponse;
import com.example.QuickPasta.exceptions.CustomerNotFoundException;
import com.example.QuickPasta.model.Cart;
import com.example.QuickPasta.model.Customer;
import com.example.QuickPasta.repository.CustomerRepository;
import com.example.QuickPasta.transformer.CustomerTransformer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomerService {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponse addCustomer(CustomerRequest customerRequest) throws Exception {

        // dto -> customer
        Customer customer = CustomerTransformer.CustomerRequestToCustomer(customerRequest);
        Optional<Customer> customerOptional = customerRepository.findByEmail(customer.getEmail());
        if(customerOptional.isPresent()) throw new Exception("email already exists!");

        String password = passwordEncoder.encode(customerRequest.getPassword());
        customer.setPassword(password);
        customer.setRole("ROLE_CUSTOMER");
        customer.setUsername(customerRequest.getEmail());
        Cart cart = Cart.builder()
                .cartTotal(0)
                .customer(customer)
                .cartItemList(new ArrayList<>())
                .build();

        customer.setCart(cart);

        // save customer (parent) and cart will automatically be saved
        Customer savedCustomer = customerRepository.save(customer);

        return CustomerTransformer.CustomerToCustomerResponse(savedCustomer);
    }

    public CustomerResponse updateCustomer(CustomerRequest customerRequest) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(customerRequest.getEmail());
        if(optionalCustomer.isEmpty()) {
            throw new CustomerNotFoundException("Customer does not exist!!");
        }

//        Optional<Customer> optionalCustomer= customerRepository.findByEmail(customerRequest.getEmail());
        Customer existingCustomer = optionalCustomer.get();
//        Customer newCustomer = CustomerTransformer.CustomerRequestToCustomer(customerRequest);
//        newCustomer.setCart(existingCustomer.getCart());
//
//        BeanUtils.copyProperties(newCustomer, existingCustomer, "id");

        existingCustomer.setAddress(customerRequest.getAddress());
        existingCustomer.setName(customerRequest.getName());
        existingCustomer.setPhoneNo(customerRequest.getPhoneNo());

        Customer updatedCustomer = customerRepository.save(existingCustomer);

        return CustomerTransformer.CustomerToCustomerResponse(updatedCustomer);

    }

    public CustomerResponse getCustomerDetails(String email) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(email);
        if(customerOptional.isEmpty()) return null;
        return CustomerTransformer.CustomerToCustomerResponse(customerOptional.get());
    }

    public void customerLogin(CustomerRequest customerRequest) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(customerRequest.getEmail());
        if(customerOptional.isEmpty()) throw new CustomerNotFoundException("wrong credentials!");
        Customer customer = customerOptional.get();
        String password = passwordEncoder.encode(customerRequest.getPassword());
        if(!(customer.getPassword().equals(password))) throw new CustomerNotFoundException("wrong credentials!");

    }
}
