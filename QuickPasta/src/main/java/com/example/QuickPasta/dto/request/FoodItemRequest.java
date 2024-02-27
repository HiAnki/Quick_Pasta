package com.example.QuickPasta.dto.request;

import com.example.QuickPasta.Enum.FoodCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodItemRequest {
    String dishName;

    String description;

    FoodCategory category;

    int restaurantId;

    boolean veg;

    boolean available;

    int price;
}
