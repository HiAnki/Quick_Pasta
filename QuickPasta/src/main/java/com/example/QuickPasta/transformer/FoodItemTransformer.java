package com.example.QuickPasta.transformer;

import com.example.QuickPasta.dto.request.FoodItemRequest;
import com.example.QuickPasta.dto.response.FoodItemResponse;
import com.example.QuickPasta.model.FoodItem;

public class FoodItemTransformer {
    public static FoodItemResponse foodItemToFoodItemResponse(FoodItem foodItem) {
        return FoodItemResponse.builder()
                .dishName(foodItem.getDishName())
                .restaurantName(foodItem.getRestaurant().getName())
                .foodCategory(foodItem.getFoodCategory())
                .veg(foodItem.isVeg())
                .price(foodItem.getPrice())
                .howManyTimesOrdered(foodItem.getHowManyTimesOrdered())
                .available(foodItem.isAvailable())
                .description(foodItem.getDescription())
                .build();
    }

    public static FoodItem foodItemRequestToFoodItem(FoodItemRequest foodItemRequest) {
        return FoodItem.builder()
                .dishName(foodItemRequest.getDishName())
                .veg(foodItemRequest.isVeg())
                .available(foodItemRequest.isAvailable())
                .description(foodItemRequest.getDescription())
                .price(foodItemRequest.getPrice())
                .foodCategory(foodItemRequest.getCategory())
                .build();
    }
}
