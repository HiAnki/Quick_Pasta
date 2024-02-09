package com.example.QuickPasta.transformer;

import com.example.QuickPasta.dto.response.FoodItemResponse;
import com.example.QuickPasta.model.FoodItem;

public class FoodItemTransformer {
    public static FoodItemResponse foodItemToFoodItemResponse(FoodItem foodItem) {
        return FoodItemResponse.builder()
                .totalPrice(foodItem.getTotalPrice())
                .totalQuantity(foodItem.getTotalQuantity())
                .dishName(foodItem.getMenuItem().getDishName())
                .restaurantName(foodItem.getMenuItem().getRestaurant().getName())
                .build();
    }
}
