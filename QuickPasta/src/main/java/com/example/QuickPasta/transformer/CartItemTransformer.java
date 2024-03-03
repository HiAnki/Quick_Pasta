package com.example.QuickPasta.transformer;

import com.example.QuickPasta.dto.response.CartItemResponse;
import com.example.QuickPasta.model.CartItem;

public class CartItemTransformer {

    public static CartItemResponse cartItemToCartItemResponse(CartItem cartItem) {
        return CartItemResponse.builder()
                .foodItemName(cartItem.getFoodItem().getDishName())
                .quantity(cartItem.getQuantity())
                .totalPrice(cartItem.getQuantity()*cartItem.getFoodItem().getPrice())
                .howManyPeopleOrdered(cartItem.getFoodItem().getHowManyTimesOrdered())
                .available(cartItem.getFoodItem().isAvailable())
                .priceOfOne(cartItem.getFoodItem().getPrice())
                .build();
    }
}
