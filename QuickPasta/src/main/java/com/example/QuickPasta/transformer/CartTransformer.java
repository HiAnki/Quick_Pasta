package com.example.QuickPasta.transformer;

import com.example.QuickPasta.dto.response.CartResponse;
import com.example.QuickPasta.dto.response.FoodItemResponse;
import com.example.QuickPasta.model.Cart;
import com.example.QuickPasta.model.FoodItem;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {

    public static CartResponse CartToCartResponse(Cart cart) {

        List<FoodItem> foodItemList = cart.getFoodItemList();
        List<FoodItemResponse> foodItemResponseList = new ArrayList<>();
        for(FoodItem foodItem: foodItemList) {
            foodItemResponseList.add(FoodItemTransformer.foodItemToFoodItemResponse(foodItem));
        }

        return CartResponse.builder()
                .cartTotal(cart.getCartTotal())
                .foodItems(foodItemResponseList)
                .build();
    }
}
