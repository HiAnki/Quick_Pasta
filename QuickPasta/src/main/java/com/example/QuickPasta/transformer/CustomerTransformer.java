package com.example.QuickPasta.transformer;

import com.example.QuickPasta.dto.request.CustomerRequest;
import com.example.QuickPasta.dto.response.CartResponse;
import com.example.QuickPasta.dto.response.CustomerResponse;
import com.example.QuickPasta.model.Customer;

public class CustomerTransformer {
    public static Customer CustomerRequestToCustomer(CustomerRequest customerRequest) {
        return Customer.builder()
                .name(customerRequest.getName())
                .gender(customerRequest.getGender())
                .email(customerRequest.getEmail())
                .phoneNo(customerRequest.getPhoneNo())
                .password(customerRequest.getPassword())
                .address(customerRequest.getAddress())
                .build();
    }

    public static CustomerResponse CustomerToCustomerResponse(Customer customer) {

        CartResponse cartResponse = CartTransformer.CartToCartResponse(customer.getCart());

        return CustomerResponse.builder()
                .name(customer.getName())
                .address(customer.getAddress())
                .phoneNo(customer.getPhoneNo())
                .cart(cartResponse)
                .build();
    }
}
