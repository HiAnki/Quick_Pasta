package com.example.QuickPasta.serviceInterface;

import com.example.QuickPasta.dto.request.FoodItemRequest;
import com.example.QuickPasta.dto.response.FoodItemResponse;

import java.util.List;

public interface FoodItemService {
    List<FoodItemResponse> findByKeyword(String keyword);

    String deleteFoodItem(int foodItemId);

    FoodItemResponse updateFoodItem(int id,FoodItemRequest foodItemRequest);

    Boolean changeFoodItemAvailability(int id);

    List<FoodItemResponse> searchFoodByDishName(FoodItemRequest foodItemRequest);


}
