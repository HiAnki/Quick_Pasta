package com.example.QuickPasta.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {
    int cartTotal;

    List<FoodItemResponse> foodItems;
}
