package com.example.QuickPasta.controller;

import com.example.QuickPasta.dto.request.CustomerRequest;
import com.example.QuickPasta.dto.request.RestaurantRequest;
import com.example.QuickPasta.dto.response.CustomerResponse;
import com.example.QuickPasta.dto.response.RestaurantResponse;
import com.example.QuickPasta.service.CustomerService;
import com.example.QuickPasta.serviceInterface.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home")
public class HomeController {

    public final CustomerService customerService;
    public final RestaurantService restaurantService;

    public HomeController(CustomerService customerService, RestaurantService restaurantService) {
        this.customerService = customerService;
        this.restaurantService = restaurantService;
    }

    @GetMapping("/")
    public String showHome() {
        return "general/home";
    }

    @RequestMapping(value = "/user/login", method = {RequestMethod.GET,RequestMethod.POST})
    public String showCustomerLogin(@RequestParam(value = "error", required= false) String error,@RequestParam(value = "logout", required= false) String logout, Model model) {
        String errorMessage = null;
        if(error!=null) {
            errorMessage = "Incorrect username or password";
        }
        if(logout!=null) {
            errorMessage = "Logout Successful";
        }
        model.addAttribute("customerDto", new CustomerRequest());
        model.addAttribute("errorMessage",errorMessage);
        return "general/login";
    }

//    @PostMapping("/user/login")
//    public String customerLogin(@ModelAttribute("customerDto") CustomerRequest customerRequest) throws CustomerNotFoundException {
//        try {
//            customerService.customerLogin(customerRequest);
//            return "customer/customer_home";
//        } catch (Exception e) {
//            return "general/credential_error";
//        }
//    }

    @GetMapping("/user/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("customerDto", new CustomerRequest());
        return "general/register";
    }
    @PostMapping("/user/register")
    public String addCustomer(@ModelAttribute("customerDto") CustomerRequest customerRequest) throws Exception {
        try {
            CustomerResponse customerResponse = customerService.addCustomer(customerRequest);
            return "general/success";
        } catch (Exception e) {
            return "customer/error";
        }
//        return new ResponseEntity(customerResponse, HttpStatus.CREATED);
    }

    @GetMapping("/rest/register")
    public String showRestaurantRegistration(Model model) {
        model.addAttribute("restaurantDto",new RestaurantRequest());
        return "general/rest_register";
    }

    @PostMapping("/rest/register")
    public String addRestaurant(@ModelAttribute("restaurantDto") RestaurantRequest restaurantRequest) throws Exception {
        try {
            RestaurantResponse restaurantResponse = restaurantService.addRestaurant(restaurantRequest);
            return "general/success";
        } catch (Exception e) {
            return "customer/error";
        }
//        return new ResponseEntity(customerResponse, HttpStatus.CREATED);
    }
}
