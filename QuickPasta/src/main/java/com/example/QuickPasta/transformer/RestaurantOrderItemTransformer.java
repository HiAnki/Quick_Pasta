package com.example.QuickPasta.transformer;

import com.example.QuickPasta.dto.response.CartItemResponse;
import com.example.QuickPasta.dto.response.RestaurantOrderItemResponse;
import com.example.QuickPasta.model.CartItem;
import com.example.QuickPasta.model.RestaurantOrderItem;

import java.util.ArrayList;
import java.util.List;

public class RestaurantOrderItemTransformer {
    public static RestaurantOrderItemResponse restaurantOrderItemToRestaurantOrderItemResponse(RestaurantOrderItem restaurantOrderItem) {
        List<CartItem> cartItemList = restaurantOrderItem.getCartItemList();
        List<CartItemResponse> cartItemResponseList = new ArrayList<>();
        for(CartItem cartItem: cartItemList) {
            cartItemResponseList.add(CartItemTransformer.cartItemToCartItemResponse(cartItem));
        }
        return RestaurantOrderItemResponse.builder()
                .totalPrice(restaurantOrderItem.getTotalPrice())
                .status(restaurantOrderItem.getStatus())
                .foodItems(cartItemResponseList)
                .build();
    }
}
