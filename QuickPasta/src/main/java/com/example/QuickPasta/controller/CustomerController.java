package com.example.QuickPasta.controller;

import com.example.QuickPasta.dto.request.CustomerRequest;
import com.example.QuickPasta.dto.response.CustomerResponse;
import com.example.QuickPasta.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    // constructor injection
    final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = customerService.addCustomer(customerRequest);
        return new ResponseEntity(customerResponse, HttpStatus.CREATED);
    }

}
