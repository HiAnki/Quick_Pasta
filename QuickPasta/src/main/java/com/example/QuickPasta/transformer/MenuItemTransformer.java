package com.example.QuickPasta.transformer;

import com.example.QuickPasta.dto.request.MenuItemRequest;
import com.example.QuickPasta.dto.response.MenuItemResponse;
import com.example.QuickPasta.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuItemTransformer {

    public static MenuItem FoodRequestToFoodItem(MenuItemRequest menuItemRequest) {
        return MenuItem.builder()
                .dishName(menuItemRequest.getDishName())
                .price(menuItemRequest.getPrice())
                .available(menuItemRequest.isAvailable())
                .veg(menuItemRequest.isVeg())
                .category(menuItemRequest.getCategory())
                .build();
    }

    public static MenuItemResponse FoodItemToFoodResponse(MenuItem foodItem) {
        return MenuItemResponse.builder()
                .dishName(foodItem.getDishName())
                .price(foodItem.getPrice())
                .category(foodItem.getCategory())
                .veg(foodItem.isVeg())
                .available(foodItem.isAvailable())
                .build();
    }

    public static List<MenuItemResponse> FoodItemListToFoodResponseList(List<MenuItem> foodItemList) {
        List<MenuItemResponse> foodResponses = new ArrayList<>();
        for(MenuItem foodItem: foodItemList) {
            MenuItemResponse foodResponse = FoodItemToFoodResponse(foodItem);
            foodResponses.add(foodResponse);
        }
        return foodResponses;
    }
}
