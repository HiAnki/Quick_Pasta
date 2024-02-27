package com.example.QuickPasta.transformer;

import com.example.QuickPasta.dto.response.OrderResponse;
import com.example.QuickPasta.dto.response.RestaurantOrderItemResponse;
import com.example.QuickPasta.model.OrderEntity;
import com.example.QuickPasta.model.RestaurantOrderItem;
import com.example.QuickPasta.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderTransformer {
    public static OrderResponse orderToOrderResponse(OrderEntity order) {
        List<RestaurantOrderItem> restaurantOrderItemList = order.getOrderItemList();
        List<RestaurantOrderItemResponse> restaurantOrderItemTransformers = new ArrayList<>();
        for(RestaurantOrderItem restaurantOrderItem: restaurantOrderItemList) {
            restaurantOrderItemTransformers.add(RestaurantOrderItemTransformer.restaurantOrderItemToRestaurantOrderItemResponse(restaurantOrderItem));
        }
        return OrderResponse.builder()
                .orderId(order.getOrderId())
                .orderTime(order.getOrderTime())
                .totalPrice(order.getOrderTotal())
                .orderItems(restaurantOrderItemTransformers)
                .build();
    }
}
