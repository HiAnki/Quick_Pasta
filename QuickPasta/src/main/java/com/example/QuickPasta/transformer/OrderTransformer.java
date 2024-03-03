package com.example.QuickPasta.transformer;

import com.example.QuickPasta.dto.response.CartItemResponse;
import com.example.QuickPasta.dto.response.OrderResponse;
import com.example.QuickPasta.dto.response.RestaurantOrderItemResponse;
import com.example.QuickPasta.model.CartItem;
import com.example.QuickPasta.model.OrderEntity;
import com.example.QuickPasta.model.RestaurantOrderItem;
import com.example.QuickPasta.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderTransformer {
    public static OrderResponse orderToOrderResponse(OrderEntity order) {
        List<CartItem> cartItemList = order.getCartItemList();
        List<CartItemResponse> cartItemResponseList = new ArrayList<>();
        for(CartItem cartItem: cartItemList) {
           cartItemResponseList.add(CartItemTransformer.cartItemToCartItemResponse(cartItem));
        }
        return OrderResponse.builder()
                .orderId(order.getOrderId())
                .orderTime(order.getOrderTime())
                .totalPrice(order.getOrderTotal())
                .orderItems(cartItemResponseList)
                .build();
    }
}
