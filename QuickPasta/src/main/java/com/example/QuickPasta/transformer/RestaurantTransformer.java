package com.example.QuickPasta.transformer;

import com.example.QuickPasta.dto.request.RestaurantRequest;
import com.example.QuickPasta.dto.response.RestaurantResponse;
import com.example.QuickPasta.model.Restaurant;

import java.util.ArrayList;

public class RestaurantTransformer {

    public static Restaurant RestaurantRequestToRestaurant(RestaurantRequest restaurantRequest) {
        return Restaurant.builder()
                .name(restaurantRequest.getName())
                .address(restaurantRequest.getAddress())
                .email(restaurantRequest.getEmail())
                .takingOrder(true)
                .password(restaurantRequest.getPassword())
                .rating(restaurantRequest.getRating())
                .build();
    }

    public static RestaurantResponse RestaurantToRestaurantResponse(Restaurant restaurant) {
        return RestaurantResponse.builder()
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .takingOrder(restaurant.isTakingOrder())
                .build();
    }
}
