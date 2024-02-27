package com.example.QuickPasta.serviceInterface;

import com.example.QuickPasta.dto.request.CustomerRequest;
import com.example.QuickPasta.dto.request.FoodItemRequest;
import com.example.QuickPasta.dto.request.RestaurantRequest;
import com.example.QuickPasta.dto.response.RestaurantResponse;

public interface RestaurantService {
    String changeTakingOrderStatus(int id);
    RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest);
    RestaurantResponse addFoodItemToRestaurant(FoodItemRequest foodRequest);

    RestaurantResponse updateRestaurantInfo(int restaurantId, RestaurantRequest restaurantRequest);

    void restaurantLogin(RestaurantRequest restaurantRequest);
}
