package com.example.QuickPasta.service;

import com.example.QuickPasta.dto.request.MenuItemRequest;
import com.example.QuickPasta.dto.request.RestaurantRequest;
import com.example.QuickPasta.dto.response.RestaurantResponse;
import com.example.QuickPasta.exceptions.RestaurantNotFoundException;
import com.example.QuickPasta.model.MenuItem;
import com.example.QuickPasta.model.Restaurant;
import com.example.QuickPasta.repository.RestaurantRepository;
import com.example.QuickPasta.transformer.MenuItemTransformer;
import com.example.QuickPasta.transformer.RestaurantTransformer;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantService {

    final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public String changeOpenedStatus(int id) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        if(optionalRestaurant.isEmpty()) {
            throw new RestaurantNotFoundException("restaurant does not exit!");
        }

        Restaurant restaurant = optionalRestaurant.get();
        restaurant.setOpen(!restaurant.isOpen());
        restaurantRepository.save(restaurant);
        if(restaurant.isOpen()) return "Restaurant is open!";
        else return "restaurant is closed.";
    }

    public RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest) {
        // dto -> model
        Restaurant restaurant = RestaurantTransformer.RestaurantRequestToRestaurant(restaurantRequest);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return RestaurantTransformer.RestaurantToRestaurantResponse(savedRestaurant);
    }

    public RestaurantResponse addMenuItemToRestaurant(MenuItemRequest foodRequest) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(foodRequest.getRestaurantId());
        if(optionalRestaurant.isEmpty()) {
            throw new RestaurantNotFoundException("restaurant does not exit!");
        }

        Restaurant restaurant = optionalRestaurant.get();
        MenuItem foodItem = MenuItemTransformer.FoodRequestToFoodItem(foodRequest);
        foodItem.setRestaurant(restaurant);
        restaurant.getAvailableFoodItems().add(foodItem);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return RestaurantTransformer.RestaurantToRestaurantResponse(savedRestaurant);
    }
}
