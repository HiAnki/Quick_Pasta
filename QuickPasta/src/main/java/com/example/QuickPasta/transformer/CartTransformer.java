package com.example.QuickPasta.transformer;

import com.example.QuickPasta.dto.response.CartItemResponse;
import com.example.QuickPasta.dto.response.CartResponse;
import com.example.QuickPasta.dto.response.FoodItemResponse;
import com.example.QuickPasta.model.Cart;
import com.example.QuickPasta.model.CartItem;
import com.example.QuickPasta.model.FoodItem;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {

    public static CartResponse CartToCartResponse(Cart cart) {

        List<CartItem> cartItemList = cart.getCartItemList();
        List<CartItemResponse> cartItemResponseList = new ArrayList<>();
        for(CartItem cartItem: cartItemList) {
            cartItemResponseList.add(CartItemTransformer.cartItemToCartItemResponse(cartItem));
        }

        return CartResponse.builder()
                .cartTotal(cart.getCartTotal())
                .foodItems(cartItemResponseList)
                .build();
    }
}
