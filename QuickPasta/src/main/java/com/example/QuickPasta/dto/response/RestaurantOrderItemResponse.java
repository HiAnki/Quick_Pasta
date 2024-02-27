package com.example.QuickPasta.dto.response;

import com.example.QuickPasta.Enum.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantOrderItemResponse {
    int totalPrice;
    OrderStatus status;

    List<CartItemResponse> foodItems;
}
