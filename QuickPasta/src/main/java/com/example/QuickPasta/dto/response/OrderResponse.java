package com.example.QuickPasta.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    String orderId; //uuid
    int totalPrice;
    Date orderTime;

    List<RestaurantOrderItemResponse> orderItems;
}
