package com.example.QuickPasta.controller;

import com.example.QuickPasta.dto.request.CustomerRequest;
import com.example.QuickPasta.dto.request.FoodItemRequest;
import com.example.QuickPasta.dto.request.RestaurantRequest;
import com.example.QuickPasta.dto.response.FoodItemResponse;
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

import java.util.List;

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



    @PutMapping("/update/takingOrder")
    public ResponseEntity changeTakingOrderStatus(@RequestParam int id) {
        String message = restaurantService.changeTakingOrderStatus(id);

        return new ResponseEntity(message,HttpStatus.ACCEPTED);
    }

//    @PostMapping("/add/menu_item")
//    public ResponseEntity addFoodItemToRestaurant(@RequestBody FoodItemRequest foodRequest){
//
//        RestaurantResponse restaurantResponse = restaurantService.addFoodItemToRestaurant(foodRequest);
//        return new ResponseEntity(restaurantResponse, HttpStatus.CREATED);
//    }

    @PutMapping("/update/{restaurantId}")
    public ResponseEntity updateRestaurantInfo(@PathVariable("restaurantId") int restaurantId, @RequestBody RestaurantRequest restaurantRequest) {
        RestaurantResponse restaurantResponse = restaurantService.updateRestaurantInfo(restaurantId, restaurantRequest);
        return new ResponseEntity(restaurantResponse, HttpStatus.OK);
    }
    @GetMapping("/menu_item/add")
    public String showAddMenuItem(Model model) {
        model.addAttribute("foodItemDto", new FoodItemRequest());
        return "restaurant/add_menu_item";
    }

    @PostMapping("menu_item/add")
    public String addMenuItem(@ModelAttribute("foodItemDto") FoodItemRequest foodItemRequest) throws Exception {
        restaurantService.addFoodItemToRestaurant(foodItemRequest);
        return "restaurant/add_menu_item";
    }


    @GetMapping("/menu")
    public String getAllFoods(Model model) {
        List<FoodItemResponse> foods = restaurantService.getAllFoodItems();
        model.addAttribute("foods", foods);
        return "restaurant/show_menu_items"; // This will return the "foods.html" Thymeleaf template
    }

}
