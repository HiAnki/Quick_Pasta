package com.example.QuickPasta.controller;

import com.example.QuickPasta.dto.request.CustomerRequest;
import com.example.QuickPasta.dto.response.CustomerResponse;
import com.example.QuickPasta.exceptions.CustomerNotFoundException;
import com.example.QuickPasta.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    // constructor injection
    final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

//    @GetMapping("/register")
//    public String showRegistrationForm(Model model) {
//        model.addAttribute("customerDto", new CustomerRequest());
//        return "customer/register";
//    }
//    @PostMapping("/register")
//    public String addCustomer(@ModelAttribute("customerDto") CustomerRequest customerRequest) throws Exception {
//         try {
//             CustomerResponse customerResponse = customerService.addCustomer(customerRequest);
//             return "general/success";
//         } catch (Exception e) {
//             return "customer/error";
//         }
////        return new ResponseEntity(customerResponse, HttpStatus.CREATED);
//    }

//    @GetMapping("/login")
//    public String showCustomerLogin(Model model) {
//        model.addAttribute("customerDto", new CustomerRequest());
//        return "customer/login";
//    }
//
//    @PostMapping("/login")
//    public String customerLogin(@ModelAttribute("customerDto") CustomerRequest customerRequest) throws CustomerNotFoundException {
//        try {
//            customerService.customerLogin(customerRequest);
//            return "customer/customer_home";
//        } catch (Exception e) {
//            return "customer/credentials_error";
//        }
//    }

    @GetMapping("/home")
    public String customerHome() {
        return "customer/customer_home";
    }
    @PutMapping("/update/{customerId}")
    public ResponseEntity updateCustomer(@PathVariable("customerId") int customerId, @RequestBody CustomerRequest customerRequest) {
        CustomerResponse updatedCustomer = customerService.updateCustomer(customerId,customerRequest);
        return new ResponseEntity(updatedCustomer, HttpStatus.OK);
    }



}
