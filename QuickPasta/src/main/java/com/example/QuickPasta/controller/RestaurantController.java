package com.example.QuickPasta.controller;

import com.example.QuickPasta.dto.request.CustomerRequest;
import com.example.QuickPasta.dto.request.FoodItemRequest;
import com.example.QuickPasta.dto.request.RestaurantRequest;
import com.example.QuickPasta.dto.response.RestaurantResponse;
import com.example.QuickPasta.exceptions.CustomerNotFoundException;
import com.example.QuickPasta.exceptions.RestaurantNotFoundException;
import com.example.QuickPasta.serviceInterface.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

    final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/register")
    public String showRestaurantSignIn(Model model) {
        model.addAttribute("restaurantDto",new RestaurantRequest());
        return "restaurant/register";
    }

    @PostMapping("/register")
    public String addRestaurant(@ModelAttribute("restaurantDto") RestaurantRequest restaurantRequest){
        RestaurantResponse restaurantResponse = restaurantService.addRestaurant(restaurantRequest);
//        return new ResponseEntity(restaurantResponse, HttpStatus.CREATED);
        return "restaurant/home";
    }

    @GetMapping("/home")
    public String restaurantHome() {
        return "restaurant/home";
    }

//    @GetMapping("/login")
//    public String showRestaurantLogin(Model model) {
//        model.addAttribute("restaurantDto", new CustomerRequest());
//        return "restaurant/login";
//    }
//
//    @PostMapping("/login")
//    public String restaurantLogin(@ModelAttribute("restaurantDto") RestaurantRequest restaurantRequest) throws RestaurantNotFoundException {
//        try {
//            restaurantService.restaurantLogin(restaurantRequest);
//            return "restaurant/home";
//        } catch (Exception e) {
//            return "restaurant/credential_error";
//        }
//    }

    @PutMapping("/update/takingOrder")
    public ResponseEntity changeTakingOrderStatus(@RequestParam int id) {
        String message = restaurantService.changeTakingOrderStatus(id);

        return new ResponseEntity(message,HttpStatus.ACCEPTED);
    }

    @PostMapping("/add/menu_item")
    public ResponseEntity addFoodItemToRestaurant(@RequestBody FoodItemRequest foodRequest){

        RestaurantResponse restaurantResponse = restaurantService.addFoodItemToRestaurant(foodRequest);
        return new ResponseEntity(restaurantResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update/{restaurantId}")
    public ResponseEntity updateRestaurantInfo(@PathVariable("restaurantId") int restaurantId, @RequestBody RestaurantRequest restaurantRequest) {
        RestaurantResponse restaurantResponse = restaurantService.updateRestaurantInfo(restaurantId, restaurantRequest);
        return new ResponseEntity(restaurantResponse, HttpStatus.OK);
    }



}
