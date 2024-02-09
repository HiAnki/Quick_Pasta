package com.example.QuickPasta.dto.response;

import com.example.QuickPasta.model.MenuItem;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodItemResponse {
    int totalQuantity;

    int totalPrice;

    String dishName;

    String restaurantName;
}
