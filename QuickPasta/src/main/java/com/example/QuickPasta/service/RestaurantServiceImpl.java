package com.example.QuickPasta.service;

import com.example.QuickPasta.dto.request.FoodItemRequest;
import com.example.QuickPasta.dto.request.RestaurantRequest;
import com.example.QuickPasta.dto.response.RestaurantResponse;
import com.example.QuickPasta.exceptions.RestaurantNotFoundException;
import com.example.QuickPasta.model.FoodItem;
import com.example.QuickPasta.model.Restaurant;
import com.example.QuickPasta.repository.RestaurantRepository;
import com.example.QuickPasta.serviceInterface.RestaurantService;
import com.example.QuickPasta.transformer.FoodItemTransformer;
import com.example.QuickPasta.transformer.RestaurantTransformer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    final RestaurantRepository restaurantRepository;
    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public String changeTakingOrderStatus(int id) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        if(optionalRestaurant.isEmpty()) {
            throw new RestaurantNotFoundException("restaurant does not exit!");
        }

        Restaurant restaurant = optionalRestaurant.get();
        restaurant.setTakingOrder(!restaurant.isTakingOrder());
        restaurantRepository.save(restaurant);
        if(restaurant.isTakingOrder()) return "Restaurant is open!";
        else return "restaurant is closed.";
    }

    @Override
    public RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest) {
        // dto -> model
        Restaurant restaurant = RestaurantTransformer.RestaurantRequestToRestaurant(restaurantRequest);
        restaurant.setUsername(restaurant.getEmail());
        restaurant.setRole("ROLE_RESTAURANT");
        restaurant.setPassword(passwordEncoder.encode(restaurantRequest.getPassword()));
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return RestaurantTransformer.RestaurantToRestaurantResponse(savedRestaurant);
    }

    @Override
    public RestaurantResponse addFoodItemToRestaurant(FoodItemRequest foodRequest) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(foodRequest.getRestaurantId());
        if(optionalRestaurant.isEmpty()) {
            throw new RestaurantNotFoundException("restaurant does not exit!");
        }

        Restaurant restaurant = optionalRestaurant.get();
        FoodItem foodItem = FoodItemTransformer.foodItemRequestToFoodItem(foodRequest);
        foodItem.setRestaurant(restaurant);
        restaurant.getAvailableFoodItems().add(foodItem);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return RestaurantTransformer.RestaurantToRestaurantResponse(savedRestaurant);
    }

    @Override
    public RestaurantResponse updateRestaurantInfo(int restaurantId, RestaurantRequest restaurantRequest) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        if(restaurantOptional.isEmpty()) {
            throw new RestaurantNotFoundException("Invalid restaurant id!");
        }
        Restaurant existingRestaurant = restaurantOptional.get();
        Restaurant newRestaurant = RestaurantTransformer.RestaurantRequestToRestaurant(restaurantRequest);
        BeanUtils.copyProperties(newRestaurant, existingRestaurant, "id");

        Restaurant updatedRestaurant = restaurantRepository.save(existingRestaurant);
        return RestaurantTransformer.RestaurantToRestaurantResponse(updatedRestaurant);
    }

    @Override
    public void restaurantLogin(RestaurantRequest restaurantRequest) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findByEmail(restaurantRequest.getEmail());
        if(restaurantOptional.isEmpty()) throw new RestaurantNotFoundException("wrong credentials!");

        Restaurant restaurant = restaurantOptional.get();
        if(!restaurant.getPassword().equals(restaurantRequest.getPassword())) throw new RestaurantNotFoundException("wrong credentials");
    }
}
