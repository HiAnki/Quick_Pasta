package com.example.QuickPasta.serviceInterface;

import com.example.QuickPasta.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse placeOrder(int customerId);

    List<OrderResponse> viewAllOrders(int customerId);
}
