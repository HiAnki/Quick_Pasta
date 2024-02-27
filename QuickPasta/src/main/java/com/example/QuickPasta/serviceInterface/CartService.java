package com.example.QuickPasta.serviceInterface;


import com.example.QuickPasta.dto.response.CartItemResponse;
import com.example.QuickPasta.dto.response.CartResponse;

public interface CartService {
    String addCartItemToCart(int cartId, int foodItemId, int quantity);

    CartResponse viewCart(int customerId);

    CartItemResponse deleteFoodItem(int cartItemId, int customerId);
}
